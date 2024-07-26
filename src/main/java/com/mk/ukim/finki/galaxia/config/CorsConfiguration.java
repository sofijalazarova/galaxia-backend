package com.mk.ukim.finki.galaxia.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
//public class CorsConfiguration {
//
//    @Bean
//    public WebMvcConfigurer corsConfigurer(){
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/api/**")
////                        .allowedOriginPatterns("http://localhost:5173")
//// 2                        .allowedOriginPatterns("http://localhost:5173/galaxia")
//                        .allowedOriginPatterns("https://sofijalazarova.github.io/galaxia/")
//                        .allowedMethods("GET", "POST", "PUT", "DELETE")
//                        .exposedHeaders("Content-Type")
//                        .allowCredentials(true);
//            }
//        };
//    }
//}

@Configuration
public class CorsConfiguration {

//    @Bean
//    public WebMvcConfigurer corsConfigurer(){
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/api/**")
//                        .allowedOriginPatterns("https://sofijalazarova.github.io/galaxia/")
//                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
//                        .allowedHeaders("Content-Type", "Authorization", "X-Requested-With", "Origin", "Access-Control-Request-Method", "Access-Control-Request-Headers")
//                        .exposedHeaders("Authorization")
//                        .allowCredentials(true);
//            }
//        };
//    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**")
                        .allowedOrigins("https://sofijalazarova.github.io")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true)
                        .maxAge(3600);
            }
        };
    }
}

