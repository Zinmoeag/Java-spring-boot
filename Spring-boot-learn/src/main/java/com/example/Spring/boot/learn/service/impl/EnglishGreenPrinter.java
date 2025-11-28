package com.example.Spring.boot.learn.service.impl;

import com.example.Spring.boot.learn.service.GreenPrinter;

public class EnglishGreenPrinter implements GreenPrinter {
    @Override
    public String print() {
        return "Green Color";
    }
}
