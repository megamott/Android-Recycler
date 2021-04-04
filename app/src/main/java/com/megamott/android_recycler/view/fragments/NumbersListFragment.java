package com.megamott.android_recycler.view.fragments;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.megamott.android_recycler.R;
import com.megamott.android_recycler.model.Note;
import com.megamott.android_recycler.view.adapter.NumberAdapter;
import com.megamott.android_recycler.view.screens.details.NoteDetailsActivity;
import com.megamott.android_recycler.view.screens.main.MainViewModel;

import java.util.List;
import java.util.Objects;

public class NumbersListFragment extends Fragment {

    private static final String POSITION_KEY = "POSITION";
    private static final String FRAGMENT = "FRAGMENT";

    private RecyclerView numberSheet;
    private NumberAdapter notesAdapter;
    private Button button;

    enum ItemsInLine {
        PORTRAIT(1),
        LANDSCAPE(2);

        private final int value;

        ItemsInLine(final int newValue) {
            value = newValue;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_numbers_list, container, false);

        numberSheet = view.findViewById(R.id.number_sheet);

        int orientation = Objects.requireNonNull(getActivity()).getResources().getConfiguration().orientation;
        numberSheet.setLayoutManager(new GridLayoutManager(getActivity(),
                orientation == Configuration.ORIENTATION_PORTRAIT ? ItemsInLine.PORTRAIT.value : ItemsInLine.LANDSCAPE.value));


        if (this.getContext() != null)
        {
            numberSheet.addItemDecoration(new DividerItemDecoration(this.getContext(), DividerItemDecoration.VERTICAL));
            numberSheet.addItemDecoration(new DividerItemDecoration(this.getContext(), DividerItemDecoration.HORIZONTAL));
        }

        notesAdapter = new NumberAdapter();
        numberSheet.setAdapter(notesAdapter);

        button = view.findViewById(R.id.insertion_button);

        button.setOnClickListener(v -> {
            NoteDetailsActivity.startActivity(getActivity(), null);
        });

        MainViewModel mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        mainViewModel.getListLiveData().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                notesAdapter.setItems(notes);
            }
        });

        return view;
    }


}