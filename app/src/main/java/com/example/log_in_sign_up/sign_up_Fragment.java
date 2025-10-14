package com.example.log_in_sign_up;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;
import static com.google.firebase.auth.AuthKt.getAuth;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link sign_up_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class sign_up_Fragment extends Fragment {

    private EditText edUsernameSingUp , edPasswordSingUp ;
    private Button btnSingUp ;
    private FirebaseServeces fbs ;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public sign_up_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment sign_up_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static sign_up_Fragment newInstance(String param1, String param2) {
        sign_up_Fragment fragment = new sign_up_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up_, container, false);
    }
    public void onStart()
    {
        super.onStart();
        //conecting components
        fbs = FirebaseServeces.getInstance();
        edUsernameSingUp = getView().findViewById(R.id.edUsernameSingUp);
        edPasswordSingUp = getView().findViewById(R.id.edPasswordSingUp);
        btnSingUp = getActivity().findViewById(R.id.btnSingUp);
        btnSingUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //data validation
                String username = edUsernameSingUp.getText().toString();
                String password = edPasswordSingUp.getText().toString();
                if (username.trim().isEmpty() || password.isEmpty()) {
                    Toast.makeText(getActivity(), "some fieds are empty!", Toast.LENGTH_SHORT).show();
                    return;
                }
                //singup procedure
                fbs.getAuth().createUserWithEmailAndPassword(username, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity(), "somethings went wrong", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}