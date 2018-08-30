package com.qi;

import com.qi.util.inital.ApplicationStartup;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UnoApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(UnoApplication.class);
        springApplication.addListeners(new ApplicationStartup());
        springApplication.run(args);
    }
}
