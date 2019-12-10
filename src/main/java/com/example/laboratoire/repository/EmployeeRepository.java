/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.laboratoire.repository;

import com.example.laboratoire.model.Employee;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author CHRISTIAN
 */
public interface EmployeeRepository extends CrudRepository<Employee, Integer>{
    
    public Iterable<Employee> findByStatutVie(boolean statutVie);

    public Optional<Employee> findByFirstName(String username);
}
