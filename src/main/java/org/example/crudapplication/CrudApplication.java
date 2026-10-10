package org.example.crudapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;

@SpringBootApplication
public class CrudApplication {

    public static void main(String[] args) {

        SpringApplication.run(CrudApplication.class, args);
        System.out.println("Gopal is here..........");

    }
}
