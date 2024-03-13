package com.example.demo.service;

import com.example.demo.objects.Livro;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class FirebaseService {
    public String saveBookDetails(Livro livro) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        CollectionReference booksCollection = dbFirestore.collection("livro"); // Obtém a referência para a coleção "livro"
        ApiFuture<DocumentReference> resultFuture = booksCollection.add(livro); // Adiciona um novo documento com uma ID aleatória
        return resultFuture.get().getId();
    }

    public List<Livro> getAllBooks() throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = dbFirestore.collection("livro").get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();

        List<Livro> livros = new ArrayList<>();
        for (QueryDocumentSnapshot document : documents) {
            Livro livro = document.toObject(Livro.class);
            livros.add(livro);
        }

        return livros;
    }

    public Livro getBookDetails(String titulo) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection("livro").document(titulo);
        ApiFuture<DocumentSnapshot> future = documentReference.get();

        DocumentSnapshot document = future.get();

        Livro livro = null;

        if(document.exists()){
            livro = document.toObject(Livro.class);
            return livro;
        } else {
            return null;
        }
    }

    public String updateBookDetails(Livro livro) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("livro").document(livro.getTitulo()).set(livro);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public String deleteBook(String titulo){
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("livro").document(titulo).delete();
        return "Document with the ID: "+titulo+" has been deleted";
    }
}

