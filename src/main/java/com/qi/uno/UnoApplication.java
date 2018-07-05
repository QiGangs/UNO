package com.qi.uno;

import com.qi.uno.utils.inital.ApplicationStartup;
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
