package com.megamott.android_recycler.view.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SortedList;

import com.megamott.android_recycler.App;
import com.megamott.android_recycler.R;
import com.megamott.android_recycler.model.Note;
import com.megamott.android_recycler.view.screens.details.NoteDetailsActivity;

import java.util.List;
import java.util.SortedMap;

public class NumberAdapter extends RecyclerView.Adapter<NumberAdapter.NoteViewHolder> {

    private final SortedList<Note> content;

    public NumberAdapter() {
        this.content = new SortedList<>(Note.class, new SortedList.Callback<Note>() {
            @Override
            public int compare(Note o1, Note o2) {
                if (!o2.isDone() && o1.isDone()) {
                    return 1;
                }
                if (o2.isDone() && !o1.isDone()) {
                    return -1;
                }
                return (int) (o2.getTimestamp() - o1.getTimestamp());
            }

            @Override
            public void onChanged(int position, int count) {
                notifyItemChanged(position, count);
            }

            @Override
            public boolean areContentsTheSame(Note oldItem, Note newItem) {
                return oldItem.equals(newItem);
            }

            @Override
            public boolean areItemsTheSame(Note item1, Note item2) {
                return item1.getUid() == item2.getUid();
            }

            @Override
            public void onInserted(int position, int count) {
                notifyItemRangeInserted(position, count);
            }

            @Override
            public void onRemoved(int position, int count) {
                notifyItemRangeRemoved(position, count);
            }

            @Override
            public void onMoved(int fromPosition, int toPosition) {
                notifyItemMoved(fromPosition, toPosition);
            }
        });
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NoteViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.number_in_sheet_layout, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        holder.bind(content.get(position));
    }

    @Override
    public int getItemCount() {
        return content.size();
    }

    public void setItems(List<Note> notes){
        content.replaceAll(notes);
    }

    public static class NoteViewHolder extends RecyclerView.ViewHolder {

        private static final String COLOR = "color";
        private static final String TEXT = "text";
        private final TextView noteText;
        private final CheckBox completed;
        private final View delete;

        private Note note;

        boolean silentUpdate;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            noteText = itemView.findViewById(R.id.number_element_text);
            completed = itemView.findViewById(R.id.number_element_completed);
            delete = itemView.findViewById(R.id.number_element_delete);

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    App.getInstance().getNoteDao().delete(note);
                }
            });

            completed.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (!silentUpdate) {
                        note.setDone(isChecked);
                        App.getInstance().getNoteDao().update(note);
                    }
                    updateStrokeOut();
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NoteDetailsActivity.startActivity((Activity) itemView.getContext(), note);
                }
            });
        }

        public void bind(Note note) {
            this.note = note;

            noteText.setText(note.getText());
            updateStrokeOut();

            silentUpdate = true;
            completed.setChecked(note.isDone());
            silentUpdate = false;
        }

        private void updateStrokeOut() {
            if (note.isDone()) {
                // Set Flag to true. The text is crossed out.
                noteText.setPaintFlags(noteText.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            } else {
                // Set Flag to false. The text is not crossed out.
                noteText.setPaintFlags(noteText.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
            }
        }
    }
}
