/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.laboratoire.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author CHRISTIAN
 */
@Entity
@javax.persistence.Table(name="sigle")
public class Sigle {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private int id;
    private String name;
    
    @OneToMany(mappedBy = "sigle")
    @JsonIgnore
    private List<User> user;
    
    public Sigle(){}
    public Sigle(String s){
        this.id = Integer.parseInt(s);
        this.name = "";
        this.user = null;
    }
    public Sigle(int id){
        this.id = id;
        name = "";
        user = null;
    }

    public Sigle(int id, String name, List<User> user) {
        this.id = id;
        this.name = name;
        this.user = user;
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

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }
    
    
}
