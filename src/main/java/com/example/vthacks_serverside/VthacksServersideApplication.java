package com.example.vthacks_serverside;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class VthacksServersideApplication {

    public static void main(String[] args) {
        SpringApplication.run(VthacksServersideApplication.class, args);
    }

}
