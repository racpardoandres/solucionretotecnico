package org.web; // Package is important!

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication // No scanBasePackages needed!
@ComponentScan(basePackages = {
        "org.web",
        "org.web.controller","org.web.usecase", "org.web.security",
"org.web.repository","org.web.config","org.web.config.bean"})
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}