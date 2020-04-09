package com.pi;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Главный класс Spring Boot приложения
 */
@SpringBootApplication
public class Application {

    /**
     * Точка входа
     * @param args массив аргуметов приложения
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
