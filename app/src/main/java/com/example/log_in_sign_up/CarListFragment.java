package com.example.log_in_sign_up;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class CarListFragment extends Fragment {

    private RecyclerView recyclerView;
    private CarCommandAdapter adapter;
    private List<CarCommand> carCommandList;
    private FirebaseServices fbs;

    public CarListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_car_list, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        fbs = FirebaseServices.getInstance();
        recyclerView = getView().findViewById(R.id.recyclerViewCarCommands);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        carCommandList = new ArrayList<>();
        adapter = new CarCommandAdapter(carCommandList);
        recyclerView.setAdapter(adapter);

        loadCarCommands();
    }

    private void loadCarCommands() {
        fbs.getFirestore().collection("commands")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    carCommandList.clear();
                    for (DocumentSnapshot doc : queryDocumentSnapshots) {
                        CarCommand command = doc.toObject(CarCommand.class);
                        carCommandList.add(command);
                    }
                    adapter.notifyDataSetChanged();
                })
                .addOnFailureListener(e ->
                        Toast.makeText(getContext(), "Failed to load commands", Toast.LENGTH_SHORT).show()
                );
    }

}
