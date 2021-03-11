package com.megamott.android_recycler;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NumberViewHolder extends RecyclerView.ViewHolder {

    private TextView numberView;
    private Context parent;

    public NumberViewHolder(@NonNull View itemView) {
        super(itemView);
        numberView = itemView.findViewById(R.id.number_element);
        parent = itemView.getContext();
    }

    public void bind(int onSheetIndex){
        numberView.setText(String.valueOf(onSheetIndex));
    }

}
