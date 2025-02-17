package org.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.web.jpa.RolEntityJPA;
import org.web.repository.RolJpaRepository;

@SpringBootApplication(scanBasePackages
        =  {"org.web",
   "org.web.controller"
       })
@ComponentScan(basePackageClasses =
        {RolJpaRepository.class, RolEntityJPA.class


                /* Add other classes in packages to scan */}) // Add all necessary packages

public class Main {
    public static void main(String[] args) {

        SpringApplication.run(Main.class, args);
    }
}