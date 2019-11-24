/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.laboratoire.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import java.util.List;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 *
 * @author CHRISTIAN
 */
@Entity
@DiscriminatorValue("patient")
public class Patient extends User{
 
    @OneToMany(mappedBy = "patient")
    @JsonIgnore
    private java.util.List<Sample> samples;
     
    public Patient(){
        super();
    }
    
    public Patient(int id){
        super(id);
    }

    public Patient(List<Sample> samples, int id, String firstName, String lastName, String sex, String sigle, Date dob, String roles, String nationality, String idCardNumber, String picture, String address, String phone, String codeUtilisateur, String email, String password, Date created_on, Date updated_on, boolean statutVie) {
        super(id, firstName, lastName, sigle, sex, dob, roles, nationality, idCardNumber, picture, address, phone, codeUtilisateur, email, password, created_on, updated_on, statutVie);
        this.samples = samples;
    }

    
    public Patient(User user){
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
        super.setSigle(getSigle());
    }

    public List<Sample> getSamples() {
        return samples;
    }

    public Patient setSamples(List<Sample> samples) {
        this.samples = samples;
        return this;
    }
    
    
}
