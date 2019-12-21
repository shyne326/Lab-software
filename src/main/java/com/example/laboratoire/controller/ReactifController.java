/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.laboratoire.controller;

import com.example.laboratoire.model.Reactif;
import com.example.laboratoire.repository.ReactifRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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
public class ReactifController {
   
    
    @Autowired
    ReactifRepository reactifRepo;
    
   @RequestMapping("/reactifs")
   public List<Reactif> index(){
       
       List<Reactif> list = new ArrayList();
       reactifRepo.findAll().forEach(list::add);
       
       return list;
   }
   
   @RequestMapping("/reactifs/{id}")
   public Reactif show(@PathVariable int id){
       
       Optional<Reactif> optionalReactif = reactifRepo.findById(id);
       Reactif reactif = optionalReactif.get();

       return reactif;
   }
   
   @RequestMapping(value="/reactifs", method = RequestMethod.POST)
   public Reactif store(@RequestBody Reactif dev){
       
       return reactifRepo.save(dev);
   }
   
   @RequestMapping(value="/reactifs/{id}", method = RequestMethod.PATCH)
   public Reactif update(@RequestBody Reactif reactif){
       
       return reactifRepo.save(reactif);
   }
   

   @RequestMapping(value="/reactifs/{id}", method = RequestMethod.DELETE)
   public Reactif delete(@PathVariable int id){
       
       return reactifRepo.findById(id).get();
       
   }
    
    
}
