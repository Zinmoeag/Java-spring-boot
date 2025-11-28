package com.example.Spring.boot.learn;

import com.example.Spring.boot.learn.service.ColorPrinter;
import com.example.Spring.boot.learn.service.GreenPrinter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class  SpringBootLearnApplication {

    private ColorPrinter colorPrinter;

    public SpringBootLearnApplication(ColorPrinter colorPrinter) {
        this.colorPrinter = colorPrinter;
    }

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(SpringBootLearnApplication.class, args);
        System.out.println("color printer ===>" + ctx.getBean(ColorPrinter.class).print());
	}
}
