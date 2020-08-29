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
    private ArrayList<String> operationsList;
    private ArrayList<Integer> numbersList;
    private static View.OnClickListener mOnItemClickListener;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private HistoryItemBinding hBinding;

        public MyViewHolder(HistoryItemBinding b) {
            super(b.getRoot());
            hBinding = b;
            hBinding.getRoot().setTag(this);
            hBinding.getRoot().setOnClickListener(mOnItemClickListener);
        }

    }


    public MyAdapter (ArrayList<String> operationsList,ArrayList<Integer> numbersList){
        this.numbersList = numbersList;
        this.operationsList = operationsList;
    }

    /**
     *
     * Sets the object mOnItemClickListener equals to the parameter itemClickListener which is coming
     * from the main activiy.
     *
     * @param itemClickListener an OnClickListener coming from the main activity that defines what
     *                          should happen when a recyclerView item is clicked.
     */
    public void setOnItemClickListener(View.OnClickListener itemClickListener) {
        mOnItemClickListener = itemClickListener;
    }

    /**
     *
     * Create new views (invoked by the layout manager)
     *
     * @param parent ViewGroup parent
     * @param viewType view Type int
     * @return a new ViewHolder Object
     */
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {

        return new MyViewHolder(HistoryItemBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    /**
     *
     * get element from the dataset at this position
     * replace the contents of the view with that element
     *
     * @param holder a ViewHolder object
     * @param position the position at which the adapter will put this ViewHolder
     */
    @NonNull
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.hBinding.tvPastOperation.setText(operationsList.get(position));
        holder.hBinding.tvPastNumber.setText(numbersList.get(position).toString());

    }

    /**
     *
     * @return an integer representing the size of the dataset
     */
    @Override
    public int getItemCount() {
        return operationsList.size();
    }
}
