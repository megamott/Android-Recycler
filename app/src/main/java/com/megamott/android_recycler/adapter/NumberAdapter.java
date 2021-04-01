package com.megamott.android_recycler.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.megamott.android_recycler.R;

import java.util.List;

public class NumberAdapter extends RecyclerView.Adapter<NumberAdapter.NumberViewHolder> {

    private List<String> content;
    private Context parentContext;
    private ItemClickListener itemClickListener;

    public NumberAdapter(List<String> content, Context context, ItemClickListener itemClickListener) {
        this.content = content;
        this.itemClickListener = itemClickListener;
        this.parentContext = context;
    }

    public void insert() {
        notifyItemInserted(content.size());
    }

    @NonNull
    @Override
    public NumberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater numberInflater = LayoutInflater.from(parentContext);
        View numberLayoutView = numberInflater.inflate(R.layout.number_in_sheet_layout, parent, false);
        return new NumberViewHolder(numberLayoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull NumberViewHolder holder, int position) {
        holder.bind(position + 1);
    }

    @Override
    public int getItemCount() {
        return content.size();
    }

    public class NumberViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private static final String COLOR = "color";
        private static final String TEXT = "text";
        private final TextView numberView;

        public NumberViewHolder(@NonNull View itemView) {
            super(itemView);
            numberView = itemView.findViewById(R.id.number_element);
            itemView.setOnClickListener(this);
        }

        public void bind(int onSheetIndex) {
            numberView.setText(String.valueOf(content.get(onSheetIndex - 1)));
            numberView.setTextColor(ContextCompat.getColor(parentContext, onSheetIndex % 2 == 0 ? R.color.red : R.color.blue));
        }

        @Override
        public void onClick(View view) {
            Bundle args = new Bundle();
            args.putInt(COLOR, numberView.getCurrentTextColor());
            args.putCharSequence(TEXT, numberView.getText());
            if (itemClickListener != null)
                itemClickListener.onItemClick(args, getAdapterPosition());
        }
    }
}
