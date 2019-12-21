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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;


/**
 *
 * @author CHRISTIAN
 */

@Entity
@Table(name="test")
public class Test {
    
    ///////////////////////
    @Transient
    private int sectionId;
    
    public int getSectionId(){
        return sectionId;
    }
    public Test setSectionId(int s){
        this.sectionId = s;
        return this;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
   
    private String name;
    private String description;
    private String unitOfMeasurement; // May be changed later to separate table
    private long price;
    
    @Column(name="prix_St")
    private Integer prixSt;
    
    @OneToMany(mappedBy = "test")
    @JsonIgnoreProperties({"test"})
    List<ValeurDeReference> valeurDereferences;
    
    @ManyToMany
    @JsonIgnoreProperties({"testsThatCanBeConducted","createdOn", "updatedOn","alive"})
    private List<SampleType> sampleTypes; // May not be used now
    
    @ManyToMany(mappedBy="tests")
    @JsonIgnoreProperties({"tests"})
    private List<Sample> samples;
    
    @ManyToOne
    @JsonIgnoreProperties({"createdOn", "updatedOn", "tests"})
    private Section section;
    
    @Column(name="created_on")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;
    
    @Column(name="updated_on")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedOn;
    
    @Column(name="statut_vie")
    @JsonIgnore
    private boolean statutVie;
     
    public Test() {
    }

    public Test(String name){
        this.name = name;
    }
    public Test(int testId){
        this.id = testId;
    }
    
    public Test(int id, String name, String description, String unitOfMeasurement, long price, int prixST, List<ValeurDeReference> valeurDereferences, List<SampleType> sampleTypes, Section section, Date createdOn, Date updatedOn, boolean statutVie) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.unitOfMeasurement = unitOfMeasurement;
        this.price = price;
        this.prixSt = prixST;
        this.valeurDereferences = valeurDereferences;
        this.sampleTypes = sampleTypes;
        this.section = section;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
        this.statutVie = statutVie;
    } 

    public int getId() {
        return id;
    }

    public Test setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Test setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Test setDescription(String description) {
        this.description = description;
        return this;
    }


    public String getUnitOfMeasurement() {
        return unitOfMeasurement;
    }

    public Test setUnitOfMeasurement(String unitOfMeasurement) {
        this.unitOfMeasurement = unitOfMeasurement;
        return this;
    }

    public long getPrice() {
        return price;
    }

    public Test setPrice(long price) {
        this.price = price;
        return this;
    }
    
        public long getPrixSt() {
        return prixSt;
    }

    public Test setPrixSt(int prixST) {
        this.prixSt = prixST;
        return this;
    }

    public List<ValeurDeReference> getValeurDereferences() {
        return valeurDereferences;
    }

    public Test setValeurDereferences(List<ValeurDeReference> valeurDereferences) {
        this.valeurDereferences = valeurDereferences;
        return this;
    }

    public Section getSection() {
        return section;
    }

    public Test setSection(Section section) {
       this.section = section;
        return this;
    }
    
    public Date getCreatedOn() {
        return createdOn;
    }

    public Test setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public Test setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
        return this;
    }

    public List<SampleType> getSampleTypes() {
        return sampleTypes;
    }

    public Test setSampleTypes(List<SampleType> sampleTypes) {
        this.sampleTypes = sampleTypes;
        return this;
    }
    
    
    public boolean isAlive() {
        return statutVie;
    }

    public Test setStatutVie(boolean statutVie) {
        this.statutVie = statutVie;
        return this;
    }

    public List<Sample> getSamples() {
        return samples;
    }

    public Test setSamples(List<Sample> samples) {
        this.samples = samples;
        return this;
    }
    
    
}
