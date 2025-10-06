package com.example.log_in_sign_up;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

public class FirebaseServeces
{
    private static FirebaseServeces instance;
    private FirebaseAuth auth;
    private FirebaseFirestore firestore;
    private FirebaseStorage storage;

    public FirebaseAuth getAuth() {
        return auth;
    }



    public FirebaseFirestore getFirestore() {
        return firestore;
    }
    public FirebaseStorage getStorage() {
        return storage;
    }


    public FirebaseServeces()
    {
        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        storage = FirebaseStorage.getInstance();
    }
    public static FirebaseServeces getInstance()
    {
        if(instance == null)
        {
            instance = new FirebaseServeces();
        }
        return instance;
    }

    private static class FirebaseServices {
    }
}



