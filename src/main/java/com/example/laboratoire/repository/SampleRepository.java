/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.laboratoire.repository;

import com.example.laboratoire.model.Sample;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author CHRISTIAN
 */
public interface SampleRepository extends CrudRepository<Sample, Integer>{
    
    public Iterable<Sample> findByStatutVie(boolean statutVie);
}
