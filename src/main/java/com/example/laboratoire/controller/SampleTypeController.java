/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.laboratoire.controller;

import com.example.laboratoire.model.SampleType;
import com.example.laboratoire.repository.SampleTypeRepository;
import java.util.ArrayList;
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
public class SampleTypeController {
    
    @Autowired
    SampleTypeRepository sampleTypeRepo;
    
   @RequestMapping("/sampleTypes")
   public List<SampleType> index(){
       
       List<SampleType> list = new ArrayList();
       sampleTypeRepo.findByStatutVie(true).forEach(list::add);
       
       return list;
   }
   
   @RequestMapping("/sampleTypes/{id}")
   public SampleType show(@PathVariable int id){
       
       return sampleTypeRepo.findById(id).get();
   }
   
   @RequestMapping(value="/sampleTypes", method = RequestMethod.POST)
   public SampleType store(@RequestBody SampleType pan){
       
       return sampleTypeRepo.save(
               pan.setStatutVie(true)
                  .setCreatedOn(new Date())
                  .setUpdatedOn(new Date())
       );
   }
   
   @RequestMapping(value="/sampleTypes/{id}", method = RequestMethod.PATCH)
   public SampleType update(@RequestBody SampleType sampleType){
       
       return sampleTypeRepo.save(
               sampleType.setUpdatedOn(new Date())
            );
   }
   

   @RequestMapping(value="/sampleTypes/{id}", method = RequestMethod.DELETE)
   public SampleType delete(@PathVariable int id){
       
       return sampleTypeRepo.findById(id).get()
               .setStatutVie(false);
       
   }
}
