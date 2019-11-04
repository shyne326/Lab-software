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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
    
    @Column(name = "value_type")
    private String valueType;
    private String gender;
    @Column(name="min_age")
    private double minAge;
    @Column(name="max_age")
    private double maxAge;
    @Column(name="normal_low")
    private int normalLow;
    @Column(name="normal_high")
    private int normalHigh;
    @Column(name="valid_low")
    private int validLow;
    @Column(name="valid_high")
    private int validHigh;
   
    @ManyToOne
    @JoinColumn(name="test_id") //  This line is optional since test_id is the default
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
    
    public ValeurDeReference(int id, String valueType, String gender, double minAge, double maxAge, int normalLow, int normalHigh, int validLow, int validHigh, Test test, Date createdOn, Date updatedOn, boolean statutVie) {
        this.id = id;
        this.valueType = valueType;
        this.gender = gender;
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.normalLow = normalLow;
        this.normalHigh = normalHigh;
        this.validLow = validLow;
        this.validHigh = validHigh;
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

    public String getValueType() {
        return valueType;
    }

    public ValeurDeReference setValueType(String valueType) {
        this.valueType = valueType;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public ValeurDeReference setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public double getMinAge() {
        return minAge;
    }

    public ValeurDeReference setMinAge(double minAge) {
        this.minAge = minAge;
        return this;
    }

    public double getMaxAge() {
        return maxAge;
    }

    public ValeurDeReference setMaxAge(double maxAge) {
        this.maxAge = maxAge;
        return this;
    }

    public int getNormalLow() {
        return normalLow;
    }

    public ValeurDeReference setNormalLow(int normalLow) {
        this.normalLow = normalLow;
        return this;
    }

    public int getNormalHigh() {
        return normalHigh;
    }

    public ValeurDeReference setNormalHigh(int normalHigh) {
        this.normalHigh = normalHigh;
        return this;
    }

    public int getValidLow() {
        return validLow;
    }

    public ValeurDeReference setValidLow(int validLow) {
        this.validLow = validLow;
        return this;
    }

    public int getValidHigh() {
        return validHigh;
    }

    public ValeurDeReference setValidHigh(int validHigh) {
        this.validHigh = validHigh;
        return this;
    }

    public Test getTest() {
        return test;
    }

    public ValeurDeReference setTest(Test test) {
        this.test = test;
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
