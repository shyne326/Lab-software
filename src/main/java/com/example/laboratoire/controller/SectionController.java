/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.laboratoire.controller;

import com.example.laboratoire.model.Section;
import com.example.laboratoire.repository.SectionRepository;
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
public class SectionController {
    
    @Autowired
    SectionRepository sectionRepo;
    
   @RequestMapping("/sections")
   public List<Section> index(){
       
       List<Section> list = new ArrayList();
       sectionRepo.findByStatutVie(true).forEach(list::add);
       
       return list;
   }
   
   @RequestMapping("/sections/{id}")
   public Section show(@PathVariable int id){
       
       return sectionRepo.findById(id).get();
   }
   
   @RequestMapping(value="/sections", method = RequestMethod.POST)
   public Section store(@RequestBody Section pan){
       
       return sectionRepo.save(
               pan.setStatutVie(true)
                  .setCreatedOn(new Date())
                  .setUpdatedOn(new Date())
       );
   }
   
   @RequestMapping(value="/sections/{id}", method = RequestMethod.PATCH)
   public Section update(@RequestBody Section section){
       
       return sectionRepo.save(section.setUpdatedOn(new Date()));
   }
   

   @RequestMapping(value="/sections/{id}", method = RequestMethod.DELETE)
   public Section delete(@PathVariable int id){
       
       return sectionRepo.findById(id).get()
               .setStatutVie(false);
       
   }
}
