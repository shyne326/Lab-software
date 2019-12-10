/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.laboratoire.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author CHRISTIAN
 */
@Entity
@Table(name="user")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="user_type")
public class User {
    
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name="first_name")
    @Size(min=2, max=50, message="First name should be between 2 and 50 characters")
    private String firstName;
    
    @Size(min=2, max=50, message="Last name should be between 2 and 50 characters")
    @Column(name="last_name")
    private String lastName;

    private String sigle; //title
    
    private String sex;
    private String roles;
    
    @Temporal(TemporalType.DATE)
    private Date dob;
    
    @Size(max=50, message="Too long, should be less than 50 characters")
    private String nationality;
    
    @Column(name="cni")
    @Size(max=50, message="ID card number cannot be more than 50 characters")
    private String cni;
    
    private String photo;
    private String address;   // May have an address Table later so address will be of type Address
    private String phone;
    
    @Column(name="code_utilisateur")
    private String codeUtilisateur;
    
  //  private String role;  Role will be added to the the table to which the user belongs e.g. Employee
    private String email;
    private String password;
    
    @Column(name="created_on")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;
    
    @Column(name="updated_on")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedOn;
    
    @Column(name="statut_vie")
    @JsonIgnore
    private boolean statutVie;
    

    public User() {
    }
    
    public User(int id){
        this.id = id;
    }

    public User(int id, String firstName, String lastName, String sigle, String sex, Date dob, String roles, String nationality, String cni, String photo, String address, String phone, String codeUtilisateur, String email, String password, Date createdOn, Date updatedOn, boolean statutVie) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.sigle = sigle;
        this.sex = sex;
        this.dob = dob;
        this.roles = roles;
        this.nationality = nationality;
        this.cni = cni;
        this.photo = photo;
        this.address = address;
        this.phone = phone;
        this.codeUtilisateur = codeUtilisateur;
        this.email = email;
        this.password = password;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
        this.statutVie = statutVie;
    }

    // This constructor is useful to me in the UserDetais class for authentication
    public User(User user){
        
        this.id = user.getId();
        this.firstName = user.getFirstName();
         this.lastName = user.getLastName();
        this.sigle = user.getSigle();
        this.sex = user.getSex();
        this.dob = user.getDob();
        this.roles = user.getRoles();
        this.nationality = user.getNationality();
        this.cni = user.getCni();
        this.photo = user.getPhoto();
        this.address = user.getAddress();
        this.phone = user.getPhone();
        this.codeUtilisateur = user.getCodeUtilisateur();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.createdOn = user.getCreatedOn();
        this.updatedOn = user.getUpdatedOn();
        this.statutVie = user.isAlive();
    }

    public int getId() {
        return id;
    }

    public User setId(int id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public User setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public User setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getNationality() {
        return nationality;
    }

    public User setNationality(String nationality) {
        this.nationality = nationality;
        return this;
    }

    public String getPhoto() {
        return photo;
    }

    public User setPhoto(String picture) {
        this.photo = picture;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public User setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public User setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getCodeUtilisateur() {
        return codeUtilisateur;
    }

    public User setCodeUtilisateur(String codeUtilisateur) {
        this.codeUtilisateur = codeUtilisateur;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public Date getCreatedOn() {
        return this.createdOn;
    }

    public User setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public Date getUpdatedOn() {
        return this.updatedOn;
    }

    public User setUpdatedOn(Date updated_on) {
        this.updatedOn = updated_on;
        return this;
    }
    
    public boolean isAlive() {
        return this.statutVie;
    }

    public User setStatutVie(boolean statutVie) {
        this.statutVie = statutVie;
        return this;
    }

    public String getSigle() {
        return sigle;
    }

    public User setSigle(String sigle) {
        this.sigle = sigle;
        return this;
    }

    public String getSex() {
        return sex;
    }

    public User setSex(String sex) {
        this.sex = sex;
        return this;
    }
   
        public String getRoles() {
        return roles;
    }

    public User setRoles(String role) {
        this.roles = role;
        return this;
    }

    public Date getDob() {
        return dob;
    }

    public User setDob(Date dob) {
        this.dob = dob;
        return this;
    }

    public String getCni() {
        return cni;
    }

    public User setCni(String cni) {
        this.cni = cni;
        return this;
    }
}
