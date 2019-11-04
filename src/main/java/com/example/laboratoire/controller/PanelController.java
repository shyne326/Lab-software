/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.laboratoire.controller;

import com.example.laboratoire.model.Panel;
import com.example.laboratoire.repository.PanelRepository;
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
public class PanelController {
    
   @Autowired
    PanelRepository panelRepo;
    
   @RequestMapping("/panels")
   public List<Panel> index(){
       
       List<Panel> list = new ArrayList();
       panelRepo.findByStatutVie(true).forEach(list::add);
       
       return list;
   }
   
   @RequestMapping("/panels/{id}")
   public Panel show(@PathVariable int id){
       
       return panelRepo.findById(id).get();
   }
   
   @RequestMapping(value="/panels", method = RequestMethod.POST)
   public Panel store(@RequestBody Panel pan){
       
       return panelRepo.save(
               pan.setStatutVie(true)
                  .setCreatedOn(new Date())
                  .setUpdatedOn(new Date())
       );
   }
   
   @RequestMapping(value="/panels/{id}", method = RequestMethod.PATCH)
   public Panel update(@RequestBody Panel panel){
       
       return panelRepo.save(panel.setUpdatedOn(new Date()));
   }
   

   @RequestMapping(value="/panels/{id}", method = RequestMethod.DELETE)
   public Panel delete(@PathVariable int id){
       
       return panelRepo.findById(id).get()
               .setStatutVie(false);
       
   }
}
