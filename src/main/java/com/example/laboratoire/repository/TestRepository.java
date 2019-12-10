/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.laboratoire.repository;

import com.example.laboratoire.model.Test;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author CHRISTIAN
 */
public interface TestRepository extends CrudRepository<Test, Integer>{
    
    
    public Iterable<Test> findByStatutVie(boolean statutVie);
   // public Iterable<Test> findBySampleTypeId(int id);
}
