package com.tripsplit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.tripsplit.repository")
@ComponentScan("com.tripsplit.entity")
@ComponentScan("com.tripsplit.model")
@ComponentScan("com.tripsplit.controller")
@ComponentScan("com.tripsplit.service")
@ComponentScan("com.tripsplit.config")
@ComponentScan("com.tripsplit.event")
public class SplitWiseApplication
{

    public static void main(String[] args) {
        SpringApplication.run(SplitWiseApplication.class, args);
    }
    }
