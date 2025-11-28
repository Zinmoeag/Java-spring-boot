package com.example.Spring.boot.learn.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "pizza")
public class PizzaConfig {
    private String name;
    private Double price;
    private String color;

    // setter
    public void setName(String name) {this.name = name;}
    public void setPrice(Double price) {this.price = price;}
    public void setColor(String color) {this.color = color;}

    // getter
    public String getName() {
        return name;
    }
    public Double getPrice() {
        return price;
    }
    public String getColor() {
        return color;
    }
}
