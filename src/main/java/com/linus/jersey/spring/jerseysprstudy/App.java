package com.linus.jersey.spring.jerseysprstudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * Hello world!
 *
 */
@SpringBootApplication
//@EnableGlobalMethodSecurity(prePostEnabled = true, proxyTargetClass = true)
public class App {
    public static void main( String[] args ) {
        SpringApplication.run(App.class, args);
        System.out.println( "Hello World!" );
    }
}
