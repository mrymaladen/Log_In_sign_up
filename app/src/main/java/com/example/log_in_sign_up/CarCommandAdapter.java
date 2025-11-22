package com.example.log_in_sign_up;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

// Define the CarCommand class عرض بيانات أوامر السيارة داخل RecyclerView بشكل منسّق.
//🔥 كل أمر (CarCommand) يظهر كعنصر مستقل داخل القائمة.

public class CarCommandAdapter extends RecyclerView.Adapter<CarCommandAdapter.CarCommandViewHolder> {

    private List<CarCommand> commands;
    public CarCommandAdapter(List<CarCommand> commands) {
        this.commands = commands;
    }

    @NonNull
    @Override
    public CarCommandViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_car_command, parent, false);
        return new CarCommandViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarCommandViewHolder holder, int position) {
        CarCommand command = commands.get(position);
        holder.tvTargetSquare.setText("Square: " + command.getTargetSquare());
        holder.tvMode.setText("Mode: " + command.getMode());
        holder.tvAction.setText("Action: " + command.getAction());
        holder.tvTime.setText("Time: " + command.getTime());
    }

    @Override
    public int getItemCount() {
        return commands.size();
    }

    static class CarCommandViewHolder extends RecyclerView.ViewHolder {
        TextView tvTargetSquare, tvMode, tvAction, tvTime;

        public CarCommandViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTargetSquare = itemView.findViewById(R.id.tvTargetSquare);
            tvMode = itemView.findViewById(R.id.tvMode);
            tvAction = itemView.findViewById(R.id.tvAction);
            tvTime = itemView.findViewById(R.id.tvTime);
        }
    }
}
