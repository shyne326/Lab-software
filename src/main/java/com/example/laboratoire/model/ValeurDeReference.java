/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.laboratoire.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author CHRISTIAN
 */
@javax.persistence.Entity
@javax.persistence.Table(name= "valeurs_de_reference")
public class ValeurDeReference {
    
    @javax.persistence.Id
    @javax.persistence.GeneratedValue( strategy = GenerationType.IDENTITY)
    private int id;
    
    private String category;
    private String value;
    private String unit;
    
    @ManyToOne
    @JsonIgnore
    private Test test;
    
    @Column(name="created_on")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;
    @Column(name="updated_on")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedOn;
    @Column(name="statut_vie")
    private boolean statutVie;

    public ValeurDeReference() {
    }

    public ValeurDeReference(int id){
        this.id = id;
    }
    
    public ValeurDeReference(int id, String cat, String unit, String value, Test test, Date createdOn, Date updatedOn, boolean statutVie) {
        this.id = id;
        this.category = cat;
        this.unit = unit;
        this.value = value;
        this.test = test;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
        this.statutVie = statutVie;
    }



    public int getId() {
        return id;
    }

    public ValeurDeReference setId(int id) {
        this.id = id;
        return this;
    }


    public Test getTest() {
        return test;
    }

    public ValeurDeReference setTest(Test test) {
        this.test = test;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public ValeurDeReference setCategory(String category) {
        this.category = category;
        return this;
    }

    public String getValue() {
        return value;
    }

    public ValeurDeReference setValue(String value) {
        this.value = value;
        return this;
    }

    public String getUnit() {
        return unit;
    }

    public ValeurDeReference setUnit(String unit) {
        this.unit = unit;
        return this;
    }
    
    

    public Date getCreatedOn() {
        return createdOn;
    }

    public ValeurDeReference setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public ValeurDeReference setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
        return this;
    }

    public boolean isAlive() {
        return statutVie;
    }

    public ValeurDeReference setStatutVie(boolean statutVie) {
        this.statutVie = statutVie;
        return this;
    }
    
    
}
