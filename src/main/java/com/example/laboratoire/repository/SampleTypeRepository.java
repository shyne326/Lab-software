/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.laboratoire.repository;

import com.example.laboratoire.model.SampleType;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author CHRISTIAN
 */
public interface SampleTypeRepository extends CrudRepository<SampleType, Integer>{
    
    public Iterable<SampleType> findByStatutVie(boolean statutVie);
    
}
