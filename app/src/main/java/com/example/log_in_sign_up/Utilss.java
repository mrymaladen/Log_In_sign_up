package com.example.log_in_sign_up;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

public class Utilss {
    private static Utilss instance;
    private FirebaseServices fbs;

    public Utilss() {
        fbs = FirebaseServices.getInstance();
    }

    public static Utilss getInstance() {
        if (instance == null)
            instance = new Utilss();
        return instance;
    }

    public void showMessageDialog(Context context, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(message);
        builder.setCancelable(false);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    public void uploadImage(Context context, Uri selectedImageUri) {
        if (selectedImageUri != null) {
            String imageName = "images/" + UUID.randomUUID().toString() + ".jpg";
            StorageReference imageRef = fbs.getStorage().getReference().child(imageName);

            UploadTask uploadTask = imageRef.putFile(selectedImageUri);
            uploadTask.addOnSuccessListener(taskSnapshot -> {
                imageRef.getDownloadUrl().addOnSuccessListener(uri -> {
                    // تأكد أن عندك دالة setSelectedImageURL في FirebaseServices
                    fbs.setSelectedImageURL(uri);
                    Toast.makeText(context, "Image uploaded successfully", Toast.LENGTH_SHORT).show();
                }).addOnFailureListener(e ->
                        Log.e("Utils: uploadImage: ", e.getMessage()));
            }).addOnFailureListener(e ->
                    Toast.makeText(context, "Failed to upload image", Toast.LENGTH_SHORT).show());
        } else {
            Toast.makeText(context, "Please choose an image first", Toast.LENGTH_SHORT).show();
        }
    }
}





