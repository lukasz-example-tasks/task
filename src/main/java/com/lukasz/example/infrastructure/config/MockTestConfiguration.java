package com.lukasz.example.infrastructure.config;


import com.lukasz.example.Application;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.ComponentScan;

@TestConfiguration
@ComponentScan(basePackageClasses = Application.class)
public class MockTestConfiguration {
}
