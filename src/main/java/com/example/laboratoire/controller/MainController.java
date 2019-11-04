/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.laboratoire.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author CHRISTIAN
 */

@Controller
public class MainController {
    
    @RequestMapping({"/home", "/index", "/"})
    public ModelAndView index(){
        return new ModelAndView("page");
    }
}
