/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.laboratoire.controller;

import com.example.laboratoire.service.MediaTypeUtils;
import com.example.laboratoire.service.ReportService;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author CHRISTIAN
 */
@RestController
public class ReportController {
    
    @Autowired
    ReportService reportService;
    
    @Autowired
    ServletContext servletContext;
    
    String fileBasePath = "C:\\Users\\CHRISTIAN\\Documents\\NetBeansProjects\\LaboTeste\\reports";
    
    
    @RequestMapping("reports/{userId}")
    public ResponseEntity<ByteArrayResource> generateReport(@PathVariable int userId) throws IOException{

        String fileName = "\\resultat-" + userId + ".pdf";
        
        try {
            if( reportService.generateReport(userId) ){
               // return "Report generated Successfully !";
               
                      
               Path path = Paths.get(fileBasePath + fileName);
        
               MediaType mediaType = MediaTypeUtils.getMediaTypeForFileName(this.servletContext, fileName);
               
                System.out.println("fileName: " + fileBasePath + "/resultat-" + userId + ".pdf");
                System.out.println("mediaType: " + mediaType);
                
                byte[] data = Files.readAllBytes(path);
                ByteArrayResource resource = new ByteArrayResource(data);
                
              // resource = new UrlResource(path.toUri());
              
               return ResponseEntity.ok()
                // Content-Disposition
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + path.getFileName().toString())
                // Content-Type
                .contentType(mediaType) //
                // Content-Lengh
                .contentLength(data.length) //
                .body(resource);
               
            }

        } catch (JRException | SQLException | ClassNotFoundException | MalformedURLException | FileNotFoundException ex) {
            Logger.getLogger(ReportController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        ///////////////////////////////////
        // Here we return the file as a REST Resource to be consumed with Angular
        ////////////////////////
        ///////////////////////////////////              
                
    
                
              //  return (ResponseEntity<ByteArrayResource>) ResponseEntity.notFound();
                return null;
    }
}