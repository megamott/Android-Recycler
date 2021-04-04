package com.megamott.notes.view.fragments;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.megamott.notes.R;
import com.megamott.notes.view.adapter.NumberAdapter;
import com.megamott.notes.view.screens.details.NoteDetailsActivity;
import com.megamott.notes.view.screens.main.MainViewModel;

import java.util.Objects;

public class NumbersListFragment extends Fragment {

    private NumberAdapter notesAdapter;

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

        RecyclerView numberSheet = view.findViewById(R.id.number_sheet);

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

        Button button = view.findViewById(R.id.insertion_button);

        button.setOnClickListener(v -> NoteDetailsActivity.startActivity(getActivity(), null));

        MainViewModel mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        mainViewModel.getListLiveData().observe(getViewLifecycleOwner(), notes -> notesAdapter.setItems(notes));

        return view;
    }


}