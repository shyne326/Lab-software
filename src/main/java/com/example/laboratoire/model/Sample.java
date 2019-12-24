/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.laboratoire.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

/**
 *
 * @author CHRISTIAN
 */

@Entity
@Table(name="sample")
public class Sample {
    
    
    //////////////////////////////////////////////////
    @javax.persistence.Transient
    private int[] testIds;

    public int[] getTestIds() {
        return testIds;
    }

    public Sample setTestIds(int[] testIds) {
        this.testIds = testIds;
        return this;
    }
    
    ////////////////////////////////////////
    @Transient
    private int sampleTypeId;

    public int getSampleTypeId() {
        return sampleTypeId;
    }

    public Sample setSampleTypeId(int sampleTypeId) {
        this.sampleTypeId = sampleTypeId;
        return this;
    }
    ////////////////////////////////////////////////
    
    @Transient
    private int patientId;
    
    public int getPatientId(){
        return patientId;
    }
    public Sample setPatientId(int p){
        this.patientId = p;
        return this;
    }
    
   /////////////////////////////////////////////////////

     @Transient
    private int labTechnicianId;
    
    public int getLabTechnicianId(){
        return labTechnicianId;
    }
    public Sample setLabTechnicianId(int p){
        this.labTechnicianId = p;
        return this;
    }    
    /************************************************************************
     *  
     */
    

      
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @ManyToOne
    @NotNull
    private Patient patient;
    
    @ManyToOne
    @JsonIgnoreProperties({"cni","photo","address","createdOn","updatedOn","password", "email","alive", "samples", "results"})
    private Employee labTechnician;
    
    @ManyToOne
    @NotNull
    @JsonIgnoreProperties({"testsThatCanBeConducted", "createdOn", "updatedOn"})
    private SampleType sampleType;
    
    private String requester;
  //  private String requesterAddress; // Or Institution
    private String note;
    
    @OneToOne(mappedBy = "sample")  // Changed from @OneToMany to many because 
    @JsonIgnoreProperties({"sample", "createdOn", "updatedOn", "labTechnician"})
    private Result results;
    
    @ManyToMany
    @JsonIgnoreProperties({"samples","sectionId","description","price","createdOn","updatedOn","alive","sampleTypes", "prixSt"})
    private List<Test> tests = new ArrayList();
    
    @Column(name="created_on")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonIgnore
    private java.util.Date createdOn;
    
    @Column(name="updated_on")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonIgnore
    private java.util.Date updatedOn;
    
    @Column(name="statut_vie")
    @JsonIgnore
    private boolean statutVie;

    public Sample() {
    }

    public Sample(int id){
        this.id = id;
        this.patient = null;
        this.labTechnician = null;
        this.sampleType = null;
    }
    public Sample(int id, Patient patient, Employee labTechnician, SampleType sampleType, Date createdOn, Date updatedOn, boolean statutVie) {
        this.id = id;
        this.patient = patient;
        this.labTechnician = labTechnician;
        this.sampleType = sampleType;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
        this.statutVie = statutVie;
    }
    
    public int getId() {
        return id;
    }

    public Sample setId(int id) {
        this.id = id;
        return this;
    }

    public Patient getPatient() {
        return this.patient;
    }

    public Sample setPatient(Patient patient) {
        this.patient = patient;
        return this;
    }

    public SampleType getSampleType() {
        return sampleType;
    }

    public Sample setSampleType(SampleType sampleType) {
        this.sampleType = sampleType;
        return this;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public Sample setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public Sample setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
        return this;
    }

    public Employee getLabTechnician() {
        return labTechnician;
    }

    public Sample setLabTechnician(Employee labTechnician) {
        this.labTechnician = labTechnician;
        return this;
    }

    public boolean isAlive() {
        return statutVie;
    }

    public Sample setStatutVie(boolean statutVie) {
        this.statutVie = statutVie;
        return this;
    }

    public String getRequester() {
        return requester;
    }

    public Sample setRequester(String requester) {
        this.requester = requester;
        return this;
    }

    public String getNote() {
        return note;
    }

    public Sample setNote(String note) {
        this.note = note;
        return this;
    }

    public Result getResults() {
        return results;
    }

    public Sample setResults(Result result) {
        this.results = result;
        return this;
    }

    public List<Test> getTests() {
        return tests;
    }

    public Sample setTests(List<Test> tests) {
        this.tests = tests;
        return this;
    }

       
}
