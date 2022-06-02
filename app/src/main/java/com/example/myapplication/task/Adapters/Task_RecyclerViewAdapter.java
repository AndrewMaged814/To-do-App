package com.example.myapplication.task.Adapters;


import android.content.Context;
import android.graphics.Color;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.airbnb.lottie.LottieAnimationView;
import com.example.myapplication.R;
import com.example.myapplication.task.Task;

import java.util.ArrayList;


public class Task_RecyclerViewAdapter extends RecyclerView.Adapter<Task_RecyclerViewAdapter.MyViewHolder> {
    private final RecyclerViewInterface recyclerViewInterface;
    Context context;
    public static ArrayList<Task> taskModelArrayList;
    LottieAnimationView lottieAnimationView;


    public Task_RecyclerViewAdapter(Context context, ArrayList<Task> taskModelArrayList, RecyclerViewInterface recyclerViewInterface, LottieAnimationView lottieAnimationView) {
        this.context = context;
        Task_RecyclerViewAdapter.taskModelArrayList = taskModelArrayList;
        this.recyclerViewInterface = recyclerViewInterface;
        this.lottieAnimationView = lottieAnimationView;


    }

    @NonNull
    @Override
    public Task_RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //this is where I inflate the layout (giving a look to our rows)

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_row, parent, false);


        return new Task_RecyclerViewAdapter.MyViewHolder(view, recyclerViewInterface, lottieAnimationView);


    }

    @Override
    public void onBindViewHolder(@NonNull Task_RecyclerViewAdapter.MyViewHolder holder, int position) {
        // assigning values to the views created in the recycler_view_raw layout file
        //based on the position of the recycler view
        Task taskModel = taskModelArrayList.get(position);
        switch (taskModel.Category) {
            case "Study":
                holder.cardView.setCardBackgroundColor(Color.parseColor("#E46472"));
                break;
            case "Sport":
                holder.cardView.setCardBackgroundColor(Color.parseColor("#6488e4"));
                break;
            case "hobby":
                holder.cardView.setCardBackgroundColor(Color.parseColor("#309397"));
                break;
            case "other":
                holder.cardView.setCardBackgroundColor(Color.parseColor("#f9be7c"));
                break;
        }

        holder.tvName.setText(taskModel.getTaskName());
        holder.imageView.setImageResource(taskModel.getImage());
        boolean isChecked = taskModelArrayList.get(position).isTaskDone();
        holder.cbDone.setChecked(isChecked);


    }

    @Override
    public int getItemCount() {
        //the recycler view just want to know the number of items you want displayed
        return taskModelArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        //grabbing the views from our recycler_view_row layout file
        //kinda like in the onCreate method
        ImageView imageView;
        TextView tvName;
        CheckBox cbDone;
        CardView cardView;


        private final static String TAG = "TASK APP";

        public MyViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface, LottieAnimationView lottieAnimationView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            tvName = itemView.findViewById(R.id.tvTaskName);
            cbDone = itemView.findViewById(R.id.cbDone);
            cardView = itemView.findViewById(R.id.itemRow);


            cbDone.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    String taskName = tvName.getText().toString();
                    Task task = taskModelArrayList.get(getAdapterPosition());
                    Log.d(TAG, "onCreate: " + taskModelArrayList.toString());
                    if (task != null) {
                        if (cbDone.isChecked()) {
                            tvName.setText(Html.fromHtml("<strike>" + taskName + "</strike>"));
                            task.setTaskDone(true);


                        } else {
                            tvName.setText(taskName);
                            task.setTaskDone(false);
                        }
                        Log.d(TAG, "onCreate: " + taskModelArrayList.toString());
                        if(task.isTaskDone()){
                            lottieAnimationView.playAnimation();
                        }

                    }

                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (recyclerViewInterface != null) {
                        int position = getAdapterPosition();

                        if (position != RecyclerView.NO_POSITION) {
                            recyclerViewInterface.OnItemClick(position);
                        }

                    }
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    if (recyclerViewInterface != null) {
                        int position = getAdapterPosition();

                        if (position != RecyclerView.NO_POSITION) {
                            recyclerViewInterface.onItemLongClick(position);

                        }

                    }
                    return true;
                }
            });

        }


    }

}