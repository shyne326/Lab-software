/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.laboratoire.controller;

import com.example.laboratoire.model.Employee;
import com.example.laboratoire.model.Result;
import com.example.laboratoire.model.Sample;
import com.example.laboratoire.model.Test;
import com.example.laboratoire.repository.ResultRepository;
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
public class ResultController {
    
    @Autowired
    ResultRepository resultRepo;
    
   @RequestMapping("/results")
   public List<Result> index(){
       
       List<Result> list = new ArrayList();
       resultRepo.findByStatutVie(true).forEach(list::add);
       
       return list;
   }
   
   @RequestMapping("/results/{id}")
   public Result show(@PathVariable int id){
       
       return resultRepo.findById(id).get();
   }
   
   @RequestMapping(value="/results", method = RequestMethod.POST)
   public boolean store(@RequestBody Result[] res){
       
       boolean ok = false;
       
       for(Result result: res){
           
           resultRepo.save(
               result
                  .setSample(new Sample(result.getSampleId()))
                  .setTest(new Test(result.getTestId()))
                  .setValidator(new Employee(result.getValidatorId()))
                       
                  .setStatutVie(true)
                  .setCreatedOn(new Date())
                  .setUpdatedOn(new Date())
       );
           
       }
       ok = true;
       
       return ok;
   }
   
   @RequestMapping(value="/results/{id}", method = RequestMethod.PATCH)
   public Result update(@RequestBody Result result){
       
       return resultRepo.save(result.setUpdatedOn(new Date()));
   }
   
   @RequestMapping(value="/results", method = RequestMethod.PUT)
   public void updateResults(@RequestBody Result[] results){
       
       for(Result result: results){
           
           resultRepo.save(
               result
                  .setSample(new Sample(result.getSampleId()))
                  .setTest(new Test(result.getTestId()))
                  .setValidator(new Employee(result.getValidatorId()))
                       
                  .setUpdatedOn(new Date())
            );
           
        }
   }
   

   @RequestMapping(value="/results/{id}", method = RequestMethod.DELETE)
   public Result delete(@PathVariable int id){
       
       return resultRepo.findById(id).get()
               .setStatutVie(false);
       
   }
   
   
}
