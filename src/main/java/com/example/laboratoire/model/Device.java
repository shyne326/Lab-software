/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.laboratoire.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "device")
public class Device {  // Device or Method, this will also serve as name of method used.
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String name;
    private String description;
    
    @OneToMany(mappedBy =  "deviceUsed", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<TestEffectue> testsEffectues;
    @Column(name="created_on")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;
    @Column(name="updated_on")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedOn;
    @Column(name="statut_vie")
    private boolean statutVie;
    
    public Device(){}

    public Device(int id) {
        this.id = id;
        this.name = "";
        this.description = "";
        this.testsEffectues = null;
    }
    
    

    public Device(int id, String name, String description, List<TestEffectue> testsEffectues, Date createdOn, Date updatedOn, boolean statutVie) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.testsEffectues = testsEffectues;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
        this.statutVie = statutVie;
    }
    

    public int getId() {
        return id;
    }

    public Device setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Device setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Device setDescription(String description) {
        this.description = description;
        return this;
    }

    public List<TestEffectue> getTestsEffectues() {
        return testsEffectues;
    }

    public Device setTestsEffectues(List<TestEffectue> testsEffectues) {
        this.testsEffectues = testsEffectues;
        return this;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public Device setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public Device setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
        return this;
    }

    public boolean isAlive() {
        return statutVie;
    }

    public Device setStatutVie(boolean statutVie) {
        this.statutVie = statutVie;
        return this;
    }
     
}
