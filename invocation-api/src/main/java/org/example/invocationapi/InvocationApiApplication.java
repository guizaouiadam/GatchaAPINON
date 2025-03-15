package org.example.invocationapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"org.example.invocationapi", "com.example.gatchaapi"})
public class InvocationApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(InvocationApiApplication.class, args);
	}
}