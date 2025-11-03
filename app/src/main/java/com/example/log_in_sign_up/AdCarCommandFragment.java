package com.example.log_in_sign_up;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AdCarCommandFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AdCarCommandFragment extends Fragment {
    private EditText etSquare, etMode, etAction, etTime;
    private Button btnSend;
    private FirebaseServices fbs;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AdCarCommandFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AdCarCommandFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AdCarCommandFragment newInstance(String param1, String param2) {
        AdCarCommandFragment fragment = new AdCarCommandFragment();
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
        return inflater.inflate(R.layout.fragment_ad_car_command, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        ConnectComponents();
    }
    public void ConnectComponents() {
        etSquare = getView().findViewById(R.id.etSquare);
        etMode = getView().findViewById(R.id.etMode);
        etAction = getView().findViewById(R.id.etAction);
        etTime = getView().findViewById(R.id.etTime);
        fbs = FirebaseServices.getInstance();
        btnSend = getActivity().findViewById(R.id.btnSend);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //data validation
                String square, mode, action, time;
                square = etSquare.getText().toString();
                mode = etMode.getText().toString();
                action = etAction.getText().toString();
                time = etTime.getText().toString();

                if (time.trim().isEmpty()) {
                    // إذا المستخدم ما كتب الوقت، نحط الوقت الحالي تلقائيًا
                    time = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(new Date());
                }

                if (square.isEmpty() || mode.isEmpty() || action.isEmpty()) {
                    Toast.makeText(getContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }
                CarCommand command = new CarCommand(square, mode, action, time);
                fbs.getFirestore().collection("car command").add(command).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        //what to do in successful cases
                        Toast.makeText(getActivity(), "command sent successfully", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //what to do in failure
                        Toast.makeText(getActivity(), "somethings went wrong", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}