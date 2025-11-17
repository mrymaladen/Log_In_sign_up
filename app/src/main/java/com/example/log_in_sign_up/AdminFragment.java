package com.example.log_in_sign_up;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class AdminFragment extends Fragment {

    private Button btnAdd, btnAll;

    public AdminFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_admin, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        btnAdd = getView().findViewById(R.id.btnAdd);
        btnAll = getView().findViewById(R.id.btnAll);

        // زر Add يأخذ المستخدم إلى AdCarCommandFragment
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.frameLayoutMain, new AdCarCommandFragment());
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        // زر All يأخذ المستخدم إلى CarListFragment
        btnAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.frameLayoutMain, new CarListFragment());
                ft.addToBackStack(null);
                ft.commit();
            }
        });
    }
}
