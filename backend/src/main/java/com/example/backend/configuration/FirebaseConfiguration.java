package com.example.backend.configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class FirebaseConfiguration {
    public FirebaseConfiguration() {
        initializeFirebase();
    }

    private void initializeFirebase() {
        try {
            // TODO add in /resources
            /*
            Go to https://console.firebase.google.com -> select / create  your project
            -> settings -> serviceaccounts -> adminsdk
            -> generate a new private key -> drag & drop the file in /resources
            */
            ClassPathResource resource = new ClassPathResource("firebase-config.json");
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(resource.getInputStream()))
                    .build();

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error initializing Firebase", e);
        }
    }
}