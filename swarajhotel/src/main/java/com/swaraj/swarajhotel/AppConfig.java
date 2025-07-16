package com.swaraj.swarajhotel;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import jakarta.annotation.PostConstruct;  // ✅ IMPORTANT

@Configuration
public class AppConfig {

    @Value("${cloudinary.cloud.name}")
    private String cloudName;

    @Value("${cloudinary.api.key}")
    private String apiKey;

    @Value("${cloudinary.api.secret}")
    private String apiSecret;

    // ✅ Add this method to log environment values on startup
    @PostConstruct
    public void logEnv() {
        System.out.println("☁️ cloudName = " + cloudName);
        System.out.println("🔑 apiKey = " + apiKey);
        System.out.println("🕶️ apiSecret = " + apiSecret);
    }

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(
                ObjectUtils.asMap(
                        "cloud_name", cloudName,
                        "api_key", apiKey,
                        "api_secret", apiSecret
                )
        );
    }
}
