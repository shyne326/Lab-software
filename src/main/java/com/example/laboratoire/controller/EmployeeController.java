/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.laboratoire.controller;

import com.example.laboratoire.model.Employee;
import com.example.laboratoire.repository.EmployeeRepository;
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
public class EmployeeController {
    
    @Autowired
    EmployeeRepository employeeRepo;
    
   @RequestMapping("/employees")
   public List<Employee> index(){
       
       List<Employee> list = new ArrayList();
       employeeRepo.findByStatutVie(true).forEach(list::add);
       
       return list;
   }
   
   @RequestMapping("/employees/{id}")
   public Employee show(@PathVariable int id){
       
       return employeeRepo.findById(id).get();
   }
   
   @RequestMapping(value="/employees", method = RequestMethod.POST)
   public Employee store(@RequestBody Employee emp){
       
              Employee e = employeeRepo.save(
              (Employee) emp.setStatutVie(true)
                               .setCreatedOn(new Date())
                               .setUpdatedOn(new Date())
       );
       
      return employeeRepo.save(
              (Employee) e.setCodeUtilisateur("MJ"+ e.getId())
      );
   }
   
   @RequestMapping(value="/employees/{id}", method = RequestMethod.PATCH)
   public Employee update(@RequestBody Employee emp){
       
            return employeeRepo.save(
              (Employee) emp.setUpdatedOn(new Date())
       );
   }
   

   @RequestMapping(value="/employees/{id}", method = RequestMethod.DELETE)
   public Employee delete(@PathVariable int id){
       
       return (Employee)employeeRepo.findById(id).get()
               .setStatutVie(false);
       
   }
   
      //@GetMapping(produces = "application/json")
	@RequestMapping({ "validate-user/{username}" })
	public Employee validateLogin(@PathVariable String username) {
            
            return employeeRepo.findByFirstName(username).get();
         //  return "Logged in successfully";
            
	}
}
