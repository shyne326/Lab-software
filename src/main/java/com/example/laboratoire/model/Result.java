/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.laboratoire.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author CHRISTIAN
 */
@Entity
@Table(name="result")
public class Result {
    
    //////////////////////////////////////////////
@Transient
private int validatorId;

public int getValidatorId(){
    return validatorId;
}
public Result setValidatorId(int val){
    this.validatorId = val;
    return this;
}
    
    //////////////////////////////////////////////
    
    @Transient
    private int sampleId;
    
    public int getSampleId(){
        return this.sampleId;
    }
    public Result setSampleId(int s){
        this.sampleId = s;
        return this;
    }
    ///////////////////////////////////////////////
    @Transient
    private int testId;
    
    public int getTestId(){
        return this.testId;
    }
    public Result setTestId(int t){
        this.testId = t;
        return this;
    }
    
    
    
    
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String resultat;
    
    @Column(name="attached_file")
    private String attachedFile;
    private String inference; // Added by the Director
    private boolean validated;
    private String device;
    private String reactif;
    
    @ManyToOne
    private Employee validator;
    
    
    @ManyToOne
    @JsonIgnoreProperties({"results","createdOn","updatedOn"})
    private Sample sample;
    @ManyToOne
    @JsonIgnoreProperties({"validatorId","sampleId","testId","description","price","sampleTypes","samples","section","createdOn","updatedOn","active"})
    private Test test;
    
    @Column(name="created_on")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonIgnore
    private Date createdOn;
    @Column(name="updated_on")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonIgnore
    private Date updatedOn;
    
    @JsonIgnore
    @Column(name="statutVie")
    private boolean statutVie;
    
    public Result() {
    }
    
    public Result(int id){
        this.id = id;
        
    }

    public Result(int id, String inference, boolean validated, String attachedFile, Employee validatedBy, Sample sample) {
        this.id = id;
        this.inference = inference;
        this.attachedFile = attachedFile;
        this.validated = validated;
        this.validator = validatedBy;
        this.sample = sample;
    }


    public int getId() {
        return id;
    }

    public Result setId(int id) {
        this.id = id;
        return this;
    }

    public String getResultat() {
        return resultat;
    }

    public Result setResultat(String resultat) {
        this.resultat = resultat;
        return this;
    }

    public String getReactif() {
        return reactif;
    }

    public Result setReactif(String reactif) {
        this.reactif = reactif;
        return this;
    }

    
    public String getInference() {
        return inference;
    }

    public Result setInference(String notes) {
        this.inference = notes;
        return this;
    }

    public String getAttachedFile() {
        return attachedFile;
    }

    public Result setAttachedFile(String attachedFile) {
        this.attachedFile = attachedFile;
        return this;
    }

    public Sample getSample() {
        return this.sample;
    }

    public Result setSample(Sample s) {
        this.sample = s;
        return this;
    }

    public Test getTest() {
        return test;
    }

    public Result setTest(Test test) {
        this.test = test;
        return this;
    }

    public boolean isValidated() {
        return validated;
    }

    public Result setValidated(boolean validated) {
        this.validated = validated;
        return this;
    }

    public Employee getValidator() {
        return validator;
    }

    public Result setValidator(Employee validator) {
        this.validator = validator;
        return this;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public Result setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public Result setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
        return this;
    }

    public boolean isAlive() {
        return statutVie;
    }

    public Result setStatutVie(boolean statutVie) {
        this.statutVie = statutVie;
        return this;
    }

    public String getDevice() {
        return device;
    }

    public Result setDevice(String device) {
        this.device = device;
        return this;
    }
      
    
}