/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.laboratoire.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import java.sql.*;

/**
 *
 * @author CHRISTIAN
 */
@Service
public class ReportService {
    
    //@Autowired
   // DataSource datasource;
    
    Connection conn;

    public boolean generateReport(int userId) throws JRException, FileNotFoundException, SQLException, ClassNotFoundException{
        
        String destinationPath = "C:\\Users\\CHRISTIAN\\Documents\\NetBeansProjects\\LaboTeste\\reports";
      
      //  File file = ResourceUtils.getFile("C:\\Users\\CHRISTIAN\\Documents\\NetBeansProjects\\LaboTeste\\src\\main\\resources\\patientReport.jrxml");
      File file = ResourceUtils.getFile("classpath:patientReport.jasper");
      
      Class.forName("com.mysql.jdbc.Driver");
      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/labotest", "root", "");
      //  JasperDesign jd = JRXmlLoader.load("C:\\Users\\CHRISTIAN\\Documents\\NetBeansProjects\\LaboTeste\\reports\\report2.jrxml");
     // JasperDesign jd = JRXmlLoader.load(file.getAbsolutePath());
    //    JRDesignQuery query = new JRDesignQuery();
    //    query.setText(sql);
     //   jd.setQuery(query);
        
        //JasperReport jr = JasperCompileManager.compileReport("C:\\Users\\CHRISTIAN\\Documents\\NetBeansProjects\\LaboTeste\\src\\main\\resources\\patientReport.jrxml");
     //   JasperReport jr = JasperCompileManager.compileReport(file.getAbsolutePath());
        
        Map<String, Object> params = new HashMap();
        params.put("userId", new Integer(userId));
        
        JRDataSource ds = new JREmptyDataSource();
      //  JasperPrint jp = JasperFillManager.fillReport(jr, params, datasource);
        JasperPrint jpr = JasperFillManager.fillReport(file.getAbsolutePath(), params, conn);
        
        JasperExportManager.exportReportToPdfFile(jpr, destinationPath + "\\resultat-" +userId+ ".pdf");
        
        return true;
        
    }
}
