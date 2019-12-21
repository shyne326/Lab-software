package com.example.laboratoire;

import com.example.laboratoire.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class LaboratoireApplication {

	public static void main(String[] args) {
		SpringApplication.run(LaboratoireApplication.class, args);
	}
        
@Bean
public WebMvcConfigurer corsConfigurer() {

    return new WebMvcConfigurerAdapter() {
        
        @Override
        public void addCorsMappings(CorsRegistry registry) {
          
           // registry.addMapping("/**").allowedOrigins("http://localhost:4200");
            registry.addMapping("/**")
                    .allowedOrigins("*")
                    .allowedMethods("HEAD", "GET", "POST", "PUT", "PATCH", "OPTIONS", "DELETE");
        //    registry.addMapping("/users").allowedOrigins("http://localhost:4200");
        }
};
}

}
