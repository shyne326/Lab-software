/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.laboratoire.controller;

import com.example.laboratoire.model.Device;
import com.example.laboratoire.repository.DeviceRepository;
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
public class DeviceController {
    
    @Autowired
    DeviceRepository deviceRepo;
    
   @RequestMapping("/devices")
   public List<Device> index(){
       
       List<Device> list = new ArrayList();
       deviceRepo.findByStatutVie(true).forEach(list::add);
       
       return list;
   }
   
   @RequestMapping("/devices/{id}")
   public Device show(@PathVariable int id){
       
       Optional<Device> optionalDevice = deviceRepo.findById(id);
       Device device = optionalDevice.get();

       return device;
   }
   
   @RequestMapping(value="/devices", method = RequestMethod.POST)
   public Device store(@RequestBody Device dev){
       
       return deviceRepo.save(dev.setStatutVie(true)
               .setCreatedOn(new Date())
               .setUpdatedOn(new Date())
       );
   }
   
   @RequestMapping(value="/devices/{id}", method = RequestMethod.PATCH)
   public Device update(@RequestBody Device device){
       
       return deviceRepo.save(device.setUpdatedOn(new Date()));
   }
   

   @RequestMapping(value="/devices/{id}", method = RequestMethod.DELETE)
   public Device delete(@PathVariable int id){
       
       return deviceRepo.findById(id).get()
               .setStatutVie(false);
       
   }
}
