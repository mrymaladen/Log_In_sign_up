package com.example.log_in_sign_up;



import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.log_in_sign_up.models.CarCommand;
import com.google.firebase.firestore.FirebaseFirestore;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CarCommandFragment extends Fragment {

    private EditText etSquare, etMode, etAction, etTime;
    private Button btnSend;

    public CarCommandFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_car_command, container, false);

        etSquare = view.findViewById(R.id.etSquare);
        etMode = view.findViewById(R.id.etMode);
        etAction = view.findViewById(R.id.etAction);
        etTime = view.findViewById(R.id.etTime);
        btnSend = view.findViewById(R.id.btnSend);

        btnSend.setOnClickListener(v -> sendCommandToFirebase());

        return view;
    }

    private void sendCommandToFirebase() {
        String square = etSquare.getText().toString().trim();
        String mode = etMode.getText().toString().trim();
        String action = etAction.getText().toString().trim();
        String time = etTime.getText().toString().trim();

        if (time.isEmpty()) {
            // إذا المستخدم ما كتب الوقت، نحط الوقت الحالي تلقائيًا
            time = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(new Date());
        }

        if (square.isEmpty() || mode.isEmpty() || action.isEmpty()) {
            Toast.makeText(getContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        CarCommand command = new CarCommand(square, mode, action, time);
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("carCommands")
                .add(command)
                .addOnSuccessListener(documentReference ->
                        Toast.makeText(getContext(), "✅ Command sent successfully!", Toast.LENGTH_SHORT).show())
                .addOnFailureListener(e ->
                        Toast.makeText(getContext(), "❌ Error: " + e.getMessage(), Toast.LENGTH_SHORT).show());
    }
}
