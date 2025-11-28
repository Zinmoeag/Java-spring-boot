package com.example.Spring.boot.learn;

import com.example.Spring.boot.learn.config.PizzaConfig;
import com.example.Spring.boot.learn.service.ColorPrinter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class  SpringBootLearnApplication {

    private ColorPrinter colorPrinter;
    private PizzaConfig pizzaConfig;

    public SpringBootLearnApplication(ColorPrinter colorPrinter,  PizzaConfig pizzaConfig) {
        this.pizzaConfig = pizzaConfig;
        this.colorPrinter = colorPrinter;
    }

	public static void main(String[] args) {

		ApplicationContext ctx = SpringApplication.run(SpringBootLearnApplication.class, args);
        System.out.println("color printer ===> " + ctx.getBean(ColorPrinter.class).print());

        PizzaConfig pizzaBean = ctx.getBean(PizzaConfig.class);

        System.out.println("green printer ===> " + String.format(
                "pizza %s is %s color and prize is %s dolor",
                pizzaBean.getName(),
                pizzaBean.getColor(),
                pizzaBean.getPrice()
        ));
	}
}
