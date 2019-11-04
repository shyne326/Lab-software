/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.laboratoire.repository;

import com.example.laboratoire.model.Sample;
import com.example.laboratoire.model.Test;
import com.example.laboratoire.model.TestEffectue;
import com.example.laboratoire.model.TestEffectueId;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author CHRISTIAN
 */
public interface TestEffectueRepository extends CrudRepository<TestEffectue, TestEffectueId>{
    
    public Optional<TestEffectue> findBySampleIdAndTestId(Long sampleId, Long testId);
    public Optional<TestEffectue> findBySampleAndTest(Sample s, Test t);
    
    public Iterable<TestEffectue> findByStatutVie(boolean statutVie);
    
}
