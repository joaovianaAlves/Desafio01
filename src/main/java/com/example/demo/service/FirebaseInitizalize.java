package com.example.demo.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.google.auth.oauth2.GoogleCredentials;
import org.springframework.stereotype.Service;
import  com.google.firebase.FirebaseOptions;
import com.google.firebase.FirebaseApp;

import javax.annotation.PostConstruct;

@Service
public class FirebaseInitizalize {

    @PostConstruct
    public void Initialize() throws FileNotFoundException {
        try {
            FileInputStream serviceAccount =
                    new FileInputStream("serviceAccount.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://livraria-62a65-default-rtdb.firebaseio.com")
                    .build();

            FirebaseApp.initializeApp(options);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
