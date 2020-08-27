package com.omarafifii.thirdwayvchallenge;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.omarafifii.thirdwayvchallenge.databinding.ActivityMainBinding;
import com.omarafifii.thirdwayvchallenge.databinding.HistoryItemBinding;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    ArrayList<String> operationsList;
    ArrayList<Integer> numbersList;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        // each data item is just a string in this case
        private HistoryItemBinding hBinding;

        public MyViewHolder(HistoryItemBinding b) {
            super(b.getRoot());
            hBinding = b;
        }

    }


    public MyAdapter (ArrayList<String> operationsList,ArrayList<Integer> numbersList){
        this.numbersList = numbersList;
        this.operationsList = operationsList;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {

        // create a new view
        return new MyViewHolder(HistoryItemBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    // Replace the contents of a view (invoked by the layout manager)
    @NonNull
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.hBinding.tvPastOperation.setText(operationsList.get(position));
        holder.hBinding.tvPastNumber.setText(numbersList.get(position).toString());

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return operationsList.size();
    }
}
