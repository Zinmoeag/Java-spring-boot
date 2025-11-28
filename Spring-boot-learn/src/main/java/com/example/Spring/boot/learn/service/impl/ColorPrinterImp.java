package com.example.Spring.boot.learn.service.impl;

import com.example.Spring.boot.learn.service.GreenPrinter;
import com.example.Spring.boot.learn.service.BluePrinter;
import com.example.Spring.boot.learn.service.ColorPrinter;
import org.springframework.stereotype.Component;

@Component
public class ColorPrinterImp implements ColorPrinter {

    private  GreenPrinter greenPrinter;
    private  BluePrinter bluePrinter;

    public ColorPrinterImp(GreenPrinter greenPrinter, BluePrinter bluePrinter) {
        this.greenPrinter = greenPrinter;
        this.bluePrinter = bluePrinter;
    }

    public String print() {
        return String.join(", ", bluePrinter.print(), greenPrinter.print());
    }
}
