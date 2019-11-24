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
import javax.persistence.DiscriminatorValue;
import javax.persistence.OneToMany;

/**
 *
 * @author CHRISTIAN
 */
@javax.persistence.Entity
@DiscriminatorValue("employee")
public class Employee extends User{
    @JsonIgnore
    private double salary;
    
    // Might be modified later by creating a role entity
    private String roles;
    
    
    @OneToMany(mappedBy = "labTechnician")
    @JsonIgnore
    private java.util.List<Sample> conductedSamples;
    
  //  This is only for the Engineer or Director of the lab
  //    Not all lab technicians will make use of this
  //      attribute
    @OneToMany(mappedBy = "validator")
    @JsonIgnore
    private java.util.List<Result> validatedResults;
    
    public Employee() {
        super();
    }
    
    public Employee(int id){
        super(id);
    }

    public Employee(double salary, String roles, List<Sample> conductedSamples, List<Result> validatedResults, int id, String firstName, String lastName, String sigle, String sex, Date dob, String nationality, String cni, String picture, String address, String phone, String codeUtilisateur, String email, String password, Date created_on, Date updated_on, boolean statutVie) {
        super(id, firstName, lastName, sigle, sex, dob, roles, nationality, cni, picture, address, phone, codeUtilisateur, email, password, created_on, updated_on, statutVie);
        this.salary = salary;
        this.roles = roles;
        this.conductedSamples = conductedSamples;
        this.validatedResults = validatedResults;
    }
    
        
    public Employee(User user){
        super.setId(user.getId());
        super.setFirstName(user.getFirstName());
        super.setLastName(user.getLastName());
        super.setDob(user.getDob());
        super.setNationality(user.getNationality());
        super.setCni(user.getCni());
        super.setPhoto(user.getPhoto());
        super.setAddress(user.getAddress());
        super.setPhone(user.getPhone());
        super.setCodeUtilisateur(user.getCodeUtilisateur());
        super.setEmail(user.getEmail());
        super.setPassword(user.getPassword());
        super.setCreatedOn(user.getCreatedOn());
        super.setUpdatedOn(user.getUpdatedOn());
        super.setStatutVie(user.isAlive());
        super.setSex(user.getSex());
        super.setSigle(user.getSigle());
    }

    public double getSalary() {
        return salary;
    }

    public Employee setSalary(double salary) {
        this.salary = salary;
        return this;
    }

    public String getRoles() {
        return roles;
    }

    public Employee setRoles(String roles) {
        this.roles = roles;
        return this;
    }

    public List<Result> getValidatedResults() {
        return validatedResults;
    }
    

    public Employee setValidatedResults(List<Result> validatedResults) {
        this.validatedResults = validatedResults;
        return this;
    }

    public List<Sample> getConductedSamples() {
        return conductedSamples;
    }

    public Employee setConductedSamples(List<Sample> conductedSamples) {
        this.conductedSamples = conductedSamples;
        return this;
    }
     
    
}
