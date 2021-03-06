/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.laboratoire.controller;

import com.example.laboratoire.model.Employee;
import com.example.laboratoire.model.User;
import com.example.laboratoire.repository.UserRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
public class UserController {
    
    @Autowired
    UserRepository userRepo;
    
   @RequestMapping("/users")
   public List<User> index(){
       
       List<User> list = new ArrayList();
       userRepo.findByStatutVie(true).forEach(list::add);
       
       return list;
   }
   
   @RequestMapping("/users/{id}")
   public User show(@PathVariable int id){
       
       return userRepo.findById(id).get();
   }
   
   @RequestMapping(value="/users", method = RequestMethod.POST)
   public User store(@RequestBody User usr){
       
       User u = null;
       u = userRepo.save(
               usr.setStatutVie(true)
                   .setCreatedOn(new Date())
                       .setUpdatedOn(new Date())
       );
       
       return userRepo.save(
               u.setCodeUtilisateur("MJ" + u.getId())
       );
   }
   
   @RequestMapping(value="/users/{id}", method = RequestMethod.PATCH)
   public User update(@RequestBody User user, @PathVariable("sexId") int sexId, @PathVariable("sigleId") int sigleId){
       
       return userRepo.save(
               user.setUpdatedOn(new Date())
       );
   }
   

   @RequestMapping(value="/users/{id}", method = RequestMethod.DELETE)
   public User delete(@PathVariable int id){
       
       return userRepo.findById(id).get()
               .setStatutVie(false);
       
   }
   
}
