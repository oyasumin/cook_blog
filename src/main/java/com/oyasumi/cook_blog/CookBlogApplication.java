package com.oyasumi.cook_blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class CookBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(CookBlogApplication.class, args);
    }

}
