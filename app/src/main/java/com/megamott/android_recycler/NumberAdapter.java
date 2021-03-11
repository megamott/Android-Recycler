package com.megamott.android_recycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NumberAdapter extends RecyclerView.Adapter<NumberViewHolder> {

    private int sheetSize;

    NumberAdapter(int sheetSize){
        this.sheetSize = sheetSize;
    }

    @NonNull
    @Override
    public NumberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForNumber= R.layout.number_layout;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View numberLayoutView = layoutInflater.inflate(layoutIdForNumber, parent, false);
        return new NumberViewHolder(numberLayoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull NumberViewHolder holder, int position) {
        holder.bind(position + 1);
    }

    @Override
    public int getItemCount() {
        return sheetSize;
    }
}
