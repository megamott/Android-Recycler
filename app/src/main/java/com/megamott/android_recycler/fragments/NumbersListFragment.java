package com.megamott.android_recycler.fragments;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.megamott.android_recycler.R;
import com.megamott.android_recycler.adapter.ItemClickListener;
import com.megamott.android_recycler.adapter.NumberAdapter;

public class NumbersListFragment extends Fragment implements ItemClickListener {

    private static final String POSITION_KEY = "POSITION";
    private static final String FRAGMENT = "FRAGMENT";
    private RecyclerView numberSheet;
    private NumberAdapter numberAdapter;
    private Button button;
    private int position_counter = 20;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) position_counter = Integer.parseInt(savedInstanceState.getString(POSITION_KEY));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_numbers_list, container, false);

        numberSheet = view.findViewById(R.id.number_sheet);

        int orientation = getActivity().getResources().getConfiguration().orientation;
        numberSheet.setLayoutManager(new GridLayoutManager(getActivity(), orientation == Configuration.ORIENTATION_PORTRAIT ? 3 : 4));

        numberAdapter = new NumberAdapter(position_counter, getActivity(), this::onItemClick);
        numberSheet.setAdapter(numberAdapter);

        button = view.findViewById(R.id.insertion_button);
        button.setOnClickListener(v -> {
            ++position_counter;
            numberAdapter.insert();
        });

        return view;
    }

    @Override
    public void onItemClick(View view, int position) {
        NumberFragment nextFrag = NumberFragment.newInstance((TextView) view);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_layout, nextFrag, FRAGMENT)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(POSITION_KEY, String.valueOf(position_counter));
    }
}