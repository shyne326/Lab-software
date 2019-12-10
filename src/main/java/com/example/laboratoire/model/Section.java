/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.laboratoire.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author CHRISTIAN
 */

@Entity
@Table(name="section")
public class Section {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String name;
    private String description;
    
    @OneToMany(mappedBy = "section")
    @JsonIgnoreProperties({"createdOn", "updatedOn", "samples","section","sampleTypes","valeurDereference","description"})
    private java.util.List<Test> tests;
    
    @Column(name="created_on")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;
    
    @Column(name="updated_on")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedOn;
    @Column(name="statut_vie")
    private boolean statutVie;

    public Section() {}
    
    public Section(int id){
        this.id = id;
    }

    public Section(int id, String name, String description, List<Test> tests, Date createdOn, Date updatedOn, boolean statutVie) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.tests = tests;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
        this.statutVie = statutVie;
    }
    
    
    public int getId() {
        return id;
    }

    public Section setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Section setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Section setDescription(String description) {
        this.description = description;
        return this;
    }

    public List<Test> getTests() {
        return tests;
    }

    public Section setTests(List<Test> tests) {
        this.tests = tests;
        return this;
    }
    
    
    public Date getCreatedOn() {
        return createdOn;
    }

    public Section setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public Section setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
        return this;
    }

    public boolean isAlive() {
        return statutVie;
    }

    public Section setStatutVie(boolean statutVie) {
        this.statutVie = statutVie;
        return this;
    }
    
    
}
