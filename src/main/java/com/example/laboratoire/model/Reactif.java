/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.laboratoire.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author CHRISTIAN
 */
@Entity
public class Reactif {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String name;
    
    public Reactif(){}
    public Reactif(int id, String name){
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public Reactif setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Reactif setName(String name) {
        this.name = name;
        return this;
    }
    
    
}
