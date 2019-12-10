/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.laboratoire.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author CHRISTIAN
 */

@Entity
@Table(name="sample_type")
public class SampleType {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String name;
    
    @ManyToMany(mappedBy = "sampleTypes")
    @JsonIgnoreProperties({"sampleTypes"})
    private java.util.List<Test> testsThatCanBeConducted;
    
    @OneToMany(mappedBy = "sampleType")
    @JsonIgnore
    private List<Sample> samples;
    
    @Column(name="created_on")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;
    @Column(name="updated_on")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedOn;
    @Column(name="statut_vie")
    private boolean statutVie;
    
    public SampleType(){}
    
    public SampleType(int id){
        this.id = id;
    }

    public SampleType(int id, String name, List<Test> testsThatCanBeConducted, List<Sample> samples) {
        this.id = id;
        this.name = name;
        this.testsThatCanBeConducted = testsThatCanBeConducted;
        this.samples = samples;
    }
    

    

    public int getId() {
        return id;
    }

    public SampleType setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public SampleType setName(String name) {
        this.name = name;
        return this;
    }

    public List<Test> getTestsThatCanBeConducted() {
        return testsThatCanBeConducted;
    }

    public SampleType setTestsThatCanBeConducted(List<Test> testsThatCanBeConducted) {
        this.testsThatCanBeConducted = testsThatCanBeConducted;
        return this;
    }

    public List<Sample> getSamples() {
        return samples;
    }

    public SampleType setSamples(List<Sample> samples) {
        this.samples = samples;
        return this;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public SampleType setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public SampleType setUpdatedOn(Date updateOn) {
        this.updatedOn = updateOn;
        return this;
    }

    public boolean isAlive() {
        return statutVie;
    }

    public SampleType setStatutVie(boolean statutVie) {
        this.statutVie = statutVie;
        return this;
    }
    
    
    
}
