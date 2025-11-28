package com.example.Spring.boot.learn.service.impl;

import com.example.Spring.boot.learn.service.BluePrinter;
import org.springframework.stereotype.Component;

public class MmBluePrinter implements BluePrinter {
    public String print() {
        return "a pyar";
    }
}
