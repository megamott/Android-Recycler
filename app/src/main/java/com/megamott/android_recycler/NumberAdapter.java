package com.megamott.android_recycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NumberAdapter extends RecyclerView.Adapter<NumberAdapter.NumberViewHolder> {

    private int sheetSize;
    private Context parent;

    NumberAdapter(int sheetSize, Context parent){
        this.sheetSize = sheetSize;
        this.parent = parent;
    }

    @NonNull
    @Override
    public NumberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForNumber = R.layout.number_layout;
        View numberLayoutView = LayoutInflater.from(context).inflate(layoutIdForNumber, parent, false);
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

    public class NumberViewHolder extends RecyclerView.ViewHolder {

        private TextView numberView;

        public NumberViewHolder(@NonNull View itemView) {
            super(itemView);
            numberView = itemView.findViewById(R.id.number_element);
            itemView.setOnClickListener(v -> Toast.makeText(parent, String.valueOf(getAdapterPosition() + 1), Toast.LENGTH_SHORT).show());
        }

        public void bind(int onSheetIndex){
            numberView.setText(String.valueOf(onSheetIndex));
            numberView.setTextColor(parent
                    .getResources()
                    .getColor(onSheetIndex %2 == 0 ? R.color.teal_700 : R.color.design_default_color_error));
        }
    }
}
