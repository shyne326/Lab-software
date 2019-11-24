/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.laboratoire.controller;

import com.example.laboratoire.model.Test;
import com.example.laboratoire.model.ValeurDeReference;
import com.example.laboratoire.repository.ValeurDeReferenceRepository;
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
public class ValeurDeReferenceController {
    
    @Autowired
    ValeurDeReferenceRepository referenceRepo;
    
   @RequestMapping("/references")
   public List<ValeurDeReference> index(){
       
       List<ValeurDeReference> list = new ArrayList();
       referenceRepo.findByStatutVie(true).forEach(list::add);
       
       return list;
   }
   
   @RequestMapping("/references/{id}")
   public ValeurDeReference show(@PathVariable int id){
       
       return referenceRepo.findById(id).get();
   }
   
   @RequestMapping(value="tests/{testId}/references", method = RequestMethod.POST)
   public ValeurDeReference store(@RequestBody ValeurDeReference pan, @PathVariable("testId") int testId){
       
       return referenceRepo.save(
               pan.setStatutVie(true)
                  .setTest(new Test(testId))
                  .setCreatedOn(new Date())
                  .setUpdatedOn(new Date())
       );
   }
   
   @RequestMapping(value="/references/{id}", method = RequestMethod.PATCH)
   public ValeurDeReference update(@RequestBody ValeurDeReference reference){
       
       return referenceRepo.save(reference.setUpdatedOn(new Date()));
   }
   

   @RequestMapping(value="/references/{id}", method = RequestMethod.DELETE)
   public ValeurDeReference delete(@PathVariable int id){
       
       return referenceRepo.findById(id).get()
               .setStatutVie(false);
       
   }
}
