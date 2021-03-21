package com.megamott.android_recycler.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.megamott.android_recycler.R;

public class NumberAdapter extends RecyclerView.Adapter<NumberAdapter.NumberViewHolder> {

    private int sheetSize;
    private Context parent;
    private LayoutInflater numberInflater;
    private ItemClickListener itemClickListener;

    public NumberAdapter(int sheetSize, Context parent, ItemClickListener itemClickListener){
        this.sheetSize = sheetSize;
        this.parent = parent;
        this.numberInflater = LayoutInflater.from(parent);
        this.itemClickListener = itemClickListener;
    }

    public void insert(){
        sheetSize += 1;
        notifyItemInserted(sheetSize);
    }

    @NonNull
    @Override
    public NumberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View numberLayoutView = numberInflater.inflate(R.layout.number_in_sheet_layout, parent, false);
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

    public class NumberViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private final TextView numberView;

        public NumberViewHolder(@NonNull View itemView) {
            super(itemView);
            numberView = itemView.findViewById(R.id.number_element);
            itemView.setOnClickListener(this::onClick);
        }

        public void bind(int onSheetIndex){
            numberView.setText(String.valueOf(onSheetIndex));
            numberView.setTextColor(ContextCompat.getColor(parent, onSheetIndex %2 == 0 ? R.color.red : R.color.blue));
        }

        @Override
        public void onClick(View view) {
            if (itemClickListener != null) itemClickListener.onItemClick(view, getAdapterPosition());
        }
    }
}
