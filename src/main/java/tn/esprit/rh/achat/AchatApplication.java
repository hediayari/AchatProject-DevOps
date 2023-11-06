package tn.esprit.rh.achat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableScheduling
@SpringBootApplication
public class AchatApplication {

    public static void main(String[] args) {
        SpringApplication.run(AchatApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // You can specify the path patterns here
                        .allowedOrigins("*") // Specify the allowed origins here
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH") // Allowed HTTP methods
                        .allowedHeaders("*") // Allowed headers
                        .allowCredentials(true); // Credential support
            }
        };
    }
}
