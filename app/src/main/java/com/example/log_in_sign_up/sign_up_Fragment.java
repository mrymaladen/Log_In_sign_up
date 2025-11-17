package com.example.log_in_sign_up;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;

public class sign_up_Fragment extends Fragment {

    private EditText edUsernameSingUp, edPasswordSingUp;
    private Button btnSingUp;
    private FirebaseServices fbs;

    public sign_up_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sign_up_, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        fbs = FirebaseServices.getInstance();
        edUsernameSingUp = getView().findViewById(R.id.edUsernameSingUp);
        edPasswordSingUp = getView().findViewById(R.id.edPasswordSingUp);
        btnSingUp = getView().findViewById(R.id.btnSingUp);
        Button btnGoToLogin= getView().findViewById(R.id.btnGoToLogin);
        btnGoToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frameLayoutMain, new LoginFragment())
                        .addToBackStack(null)
                        .commit();
            }

            });

        btnSingUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Data validation
                String username = edUsernameSingUp.getText().toString();
                String password = edPasswordSingUp.getText().toString();
                if (username.trim().isEmpty() && password.trim().isEmpty()) {
                    Toast.makeText(getActivity(), "Some fields are empty!", Toast.LENGTH_LONG).show();
                    return;
                }

                // Signup procedure
                fbs.getAuth().createUserWithEmailAndPassword(username, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Toast.makeText(getActivity(), "You have successfully signed up!", Toast.LENGTH_LONG).show();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity(), "Failed to signup! Check user or password!", Toast.LENGTH_LONG).show();

                    }
                });


            }


        });
    }
}