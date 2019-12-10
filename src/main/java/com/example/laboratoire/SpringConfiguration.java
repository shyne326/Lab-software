/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.laboratoire;

import com.example.laboratoire.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author CHRISTIAN
 */
@EnableWebSecurity
public class SpringConfiguration extends WebSecurityConfigurerAdapter{
    
    @Autowired
    CustomUserDetailsService userDetailsService;
    
    @Override
    public void configure(AuthenticationManagerBuilder auth)throws Exception{
        auth.userDetailsService(userDetailsService);
    }
    
    @Override
    public void configure(HttpSecurity http) throws Exception{
        
        http.csrf().disable()
                .authorizeRequests()
                   .antMatchers(HttpMethod.OPTIONS, "/**").permitAll().anyRequest().authenticated()
                   .and()
                   .httpBasic();
//        http.csrf().disable()
//                
//                .authorizeRequests()
//                  .antMatchers("/admin").hasRole("DT")
//                  .antMatchers("/**").hasAnyRole("ADMIN", "LABORANTIN","DT")
//                  .and()
//                  .formLogin();
    }
    
    @Bean
    public PasswordEncoder getPasswordEncoder(){
       return NoOpPasswordEncoder.getInstance();
    }
}
