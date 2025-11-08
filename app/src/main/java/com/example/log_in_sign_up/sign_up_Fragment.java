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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fbs = FirebaseServices.getInstance();
        edUsernameSingUp = view.findViewById(R.id.edUsernameSingUp);
        edPasswordSingUp = view.findViewById(R.id.edPasswordSingUp);
        btnSingUp = view.findViewById(R.id.btnSingUp);

        btnSingUp.setOnClickListener(v -> {
            String username = edUsernameSingUp.getText().toString().trim();
            String password = edPasswordSingUp.getText().toString().trim();

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(getActivity(), "Some fields are empty!", Toast.LENGTH_SHORT).show();
                return;
            }

            fbs.getAuth().createUserWithEmailAndPassword(username, password)
                    .addOnSuccessListener(authResult ->
                            Toast.makeText(getActivity(), "Sign up successful!", Toast.LENGTH_SHORT).show())
                    .addOnFailureListener(e ->
                            Toast.makeText(getActivity(), "Something went wrong: " + e.getMessage(), Toast.LENGTH_SHORT).show());
        });
    }
}
