package com.example.Spring.boot.learn.service.impl;

import com.example.Spring.boot.learn.service.GreenPrinter;
import org.springframework.stereotype.Component;

@Component
public class MmGreenPrinter implements GreenPrinter {
    public String print() {
        return "a sein";
    }
}