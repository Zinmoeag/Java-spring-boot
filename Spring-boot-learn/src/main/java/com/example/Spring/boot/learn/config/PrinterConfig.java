package com.example.Spring.boot.learn.config;


import com.example.Spring.boot.learn.service.BluePrinter;
import com.example.Spring.boot.learn.service.ColorPrinter;
import com.example.Spring.boot.learn.service.GreenPrinter;
import com.example.Spring.boot.learn.service.impl.ColorPrinterImp;
import com.example.Spring.boot.learn.service.impl.EnglishBluePrinter;
import com.example.Spring.boot.learn.service.impl.EnglishGreenPrinter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.Printer;

@Configuration
public class PrinterConfig {

    @Bean
    public BluePrinter bluePrinter() {
        return new EnglishBluePrinter();
    }

    @Bean
    public GreenPrinter greenPrinter() {
        return new EnglishGreenPrinter();
    }

    @Bean
    public ColorPrinter colorPrinter(
            BluePrinter bluePrinter,
            GreenPrinter greenPrinter
    ) {
        return new ColorPrinterImp(greenPrinter, bluePrinter);
    }
}
