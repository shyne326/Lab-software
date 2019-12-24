/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.laboratoire.controller;

import com.example.laboratoire.model.Employee;
import com.example.laboratoire.model.Result;
import com.example.laboratoire.model.Sample;
import com.example.laboratoire.repository.ResultRepository;
import com.example.laboratoire.repository.SampleRepository;
import com.example.laboratoire.service.MediaTypeUtils;
import com.example.laboratoire.service.ReportService;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author CHRISTIAN
 */
@RestController
public class ReportController  {
    
    @Autowired
    ReportService reportService;
    
    // This is used to store the path of the result file in the result's table in the database
    @Autowired
    ResultRepository resultRepo;  
    
    @Autowired
    SampleRepository sampleRepo;
    
    @Autowired
    ServletContext servletContext;
     String fileBasePath = "C:\\Users\\CHRISTIAN\\Documents\\NetBeansProjects\\LaboTeste\\reports";
    //  String baseDir = "classpath:";
    
    
    public ReportController() {
        try {
            this.fileBasePath = ResourceUtils.getFile("classpath:").getPath() + "\\reports\\";
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReportController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @RequestMapping("reports/{codeUtilisateur}")
    public ResponseEntity<ByteArrayResource> generateReport(@PathVariable String codeUtilisateur) throws IOException{

       // We previously downloaded the result file produced by the application via Jasper.
       //  Now we simply download an uploaded result file but the later way will come
       //    back later
       
        //String fileName = "\\resultat-" + codeUtilisateur + ".pdf";
        String fileName = codeUtilisateur + ".docx";
        
        try {
           // if( reportService.generateReport(userId) ){
               // return "Report generated Successfully !";
               
               
               Path path = Paths.get(fileBasePath + fileName);       
               MediaType mediaType = MediaTypeUtils.getMediaTypeForFileName(this.servletContext, fileName);
               byte[] data = Files.readAllBytes(path);
               
               ByteArrayResource resource = new ByteArrayResource(data);
              
               return ResponseEntity.ok()
                // Content-Disposition
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + path.getFileName().toString())
                // Content-Type
                .contentType(mediaType) //
                // Content-Lengh
                .contentLength(data.length) //
                .body(resource);
               
           // } end of if statement

        } //catch (JRException | SQLException | ClassNotFoundException | MalformedURLException | FileNotFoundException ex) {  These exceptions were
        catch(MalformedURLException | FileNotFoundException ex){
            Logger.getLogger(ReportController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        ///////////////////////////////////
        // Here we return the file as a REST Resource to be consumed with Angular
        ////////////////////////        
              //  return (ResponseEntity<ByteArrayResource>) ResponseEntity.notFound();
                return null;
    }
    
    
    
    @RequestMapping(value="/upload", method = RequestMethod.POST)
    public void uploadResult( @RequestParam("avatar") MultipartFile file,
                               @RequestParam("codeUtilisateurPatient") String codeUtilisateurPatient, @RequestParam("sampleId") int sampleId, 
                                   @RequestParam("labTechnicianId") int labTechnicianId, RedirectAttributes redirectAttributes )
    {
        
        
        if(file.isEmpty()){
            redirectAttributes.addFlashAttribute("message", "Please select a file");
        }
        try{
            
           
            // Get the file and save it in a defined directory
            byte[] bytes = file.getBytes();
            String extension = getExtension(file.getOriginalFilename());
            Path path = Paths.get(fileBasePath + "\\" + codeUtilisateurPatient + extension);
          //  Path path = Paths.get(fileBasePath + "\\" + file.getOriginalFilename());
            System.out.println(ResourceUtils.getFile("classpath:").getPath() );  //Does not influence in anyway execution
            Files.write(path, bytes);
            
            redirectAttributes.addFlashAttribute("message", "You successfully uploaded " + file.getOriginalFilename());
            
            Result newResult = new Result();
            Result possibleExistingResult = sampleRepo.findById(sampleId).get().getResults();
            
            if(possibleExistingResult != null){  // Remember that getResults() returns a single Result
                newResult.setId(possibleExistingResult.getId());
            }
           
            resultRepo.save(
                            newResult.setAttachedFile(fileBasePath + "\\" + codeUtilisateurPatient + extension)
                                     .setSample(new Sample(sampleId))
                                     .setLabTechnician(new Employee(labTechnicianId))
                                     .setValidated(false)
                                     .setStatutVie(true)
                                     .setCreatedOn(new Date())
                                     .setUpdatedOn(new Date())
            );
            
          } catch (IOException ex) {
            Logger.getLogger(ReportController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public  String getExtension(String filename){
        
        return filename.substring(filename.lastIndexOf("."));
    }
    
    
       
    
}