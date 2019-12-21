/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.laboratoire.controller;

import com.example.laboratoire.model.Employee;
import com.example.laboratoire.model.Patient;
import com.example.laboratoire.model.Result;
import com.example.laboratoire.model.Sample;
import com.example.laboratoire.model.SampleType;
import com.example.laboratoire.model.Test;
import com.example.laboratoire.repository.SampleRepository;
import com.example.laboratoire.repository.TestRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author CHRISTIAN
 */

@RestController
public class SampleController {
    
    @Autowired
    SampleRepository sampleRepo;
    @Autowired
    TestRepository testRepo;
    
   @RequestMapping("/samples")
   public List<Sample> index(){
       
       List<Sample> list = new ArrayList();
       sampleRepo.findByStatutVie(true).forEach(list::add);
       
       return list;
   }
   
   @RequestMapping("/samples/{id}")
   public Sample show(@PathVariable int id){
       
       return sampleRepo.findById(id).get();
   }
   
   @RequestMapping(value="/samples", method = RequestMethod.POST)
   public Sample store(@RequestBody Sample sample){
       

        
        
        // This block of code here below will be handled with care
        List<Test> tests = new ArrayList();
        for(int i: sample.getTestIds()){
            tests.add(new Test(i));
        }
       
       return sampleRepo.save(
               sample.setStatutVie(true)
                       .setSampleType(new SampleType(sample.getSampleTypeId()))
                       .setPatient(new Patient(sample.getPatientId()))
                       .setLabTechnician(new Employee(sample.getLabTechnicianId()))
                       .setTests(tests)
                  .setCreatedOn(new Date())
                  .setUpdatedOn(new Date())
       );
       
       /*********************/
   }
   
   @RequestMapping(value="/samples/{id}", method = RequestMethod.PUT)
   public Sample update(@RequestBody Sample sample){
       
       return sampleRepo.save(sample.setUpdatedOn(new Date()));
   }
   

   @RequestMapping(value="/samples/{id}", method = RequestMethod.DELETE)
   public Sample delete(@PathVariable int id){
       
       return sampleRepo.findById(id).get()
               .setStatutVie(false);
       
   }
   
   
   
   @RequestMapping(value="/samples/{id}/results", method = RequestMethod.PUT)
   public Sample updateResults(@RequestBody Result[] results, @PathVariable int id){
       
       Sample s = sampleRepo.findById(id).get();
       
       s.setResults(Arrays.asList(results));
       
       return sampleRepo.save(s);
   }



   
}

















