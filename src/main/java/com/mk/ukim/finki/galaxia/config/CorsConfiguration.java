package com.mk.ukim.finki.galaxia.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration {

    @Bean
    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**")
//                        .allowedOriginPatterns("http://localhost:5173")
                        .allowedOriginPatterns("http://localhost:5173/galaxia")
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        .exposedHeaders("Content-Type")
                        .allowCredentials(true);
            }
        };
    }
}
