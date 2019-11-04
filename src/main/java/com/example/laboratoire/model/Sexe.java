/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.laboratoire.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author CHRISTIAN
 */
@Entity
@Table(name="sexe")
public class Sexe {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private int id;
    private String name;
    
    @OneToMany(mappedBy = "sex", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<User> users;
    
    public Sexe(){}
    
    public Sexe(String s){
        this.id = Integer.parseInt(s);
        this.users = null;
        this.name = "";
    }
    
    public Sexe(int id){
     
        this.id = id;
        this.users = null;
        this.name  = "";
    }

    public Sexe(int id, String name, List<User> users) {
        this.id = id;
        this.name = name;
        this.users = users;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUser(List<User> user) {
        this.users = users;
    }
    
}
