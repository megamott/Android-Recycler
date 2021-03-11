package com.megamott.android_recycler;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NumberViewHolder extends RecyclerView.ViewHolder {

    private TextView numberView;

    public NumberViewHolder(@NonNull View itemView) {
        super(itemView);
        numberView = itemView.findViewById(R.id.number_element);
    }

    public void bind(int onSheetIndex){
        numberView.setText(String.valueOf(onSheetIndex));
    }
}
