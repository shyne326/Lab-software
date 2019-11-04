/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.laboratoire.controller;

import com.example.laboratoire.model.TestEffectue;
import com.example.laboratoire.repository.TestEffectueRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author CHRISTIAN
 */


/******** This controller may be deleted and 
 * if kept its probably just to return all test done ever done.
 *             
 *                 @author CHRISTIAN
 */
@RestController
public class TestEffectueController {
    
    @Autowired
    TestEffectueRepository testEffectueRepo;
    
   @RequestMapping("/testEffectues")
   public List<TestEffectue> index(){
       
       List<TestEffectue> list = new ArrayList();
       //testEffectueRepo.findByStatutVie(true).forEach(list::add);
         testEffectueRepo.findAll().forEach(list::add);
       return list;
   }
   

}
