package com.example.article_sales_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude={UserDetailsServiceAutoConfiguration.class})
public class ArticleSalesApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArticleSalesApiApplication.class, args);
	}

}
