package com.example.log_in_sign_up;

import android.net.Uri;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

public class FirebaseServices {
    private static FirebaseServices instance;
    private FirebaseAuth auth;

    private FirebaseFirestore firestore;
    private FirebaseStorage storage;

    // متغير لتخزين رابط الصورة
    private Uri selectedImageURL;

    // Constructor
    private FirebaseServices() {
        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        storage = FirebaseStorage.getInstance();
    }

    // Singleton
    public static FirebaseServices getInstance() {
        if (instance == null)
            instance = new FirebaseServices();
        return instance;
    }

    public FirebaseAuth getAuth() {
        return auth;
    }

    public FirebaseFirestore getFirestore() {
        return firestore;
    }

    public FirebaseStorage getStorage() {
        return storage;
    }

    public Uri getSelectedImageURL() {
        return selectedImageURL;
    }

    public void setSelectedImageURL(Uri uri) {
        this.selectedImageURL = uri;
    }
}




