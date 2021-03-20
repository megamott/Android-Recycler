package com.megamott.android_recycler;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class NumbersListFragment extends Fragment implements NumberAdapter.ItemClickListener{

    private static final String POSITION_KEY = "POSITION";
    private RecyclerView numberSheet;
    private NumberAdapter numberAdapter;
    private Button button;
    private int position_counter = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        position_counter = savedInstanceState == null ? 20 : Integer.parseInt(savedInstanceState.getString(POSITION_KEY));

        View view = inflater.inflate(R.layout.fragment_numbers_list, container, false);

        numberSheet = view.findViewById(R.id.number_sheet);

        int orientation = getActivity().getResources().getConfiguration().orientation;
        numberSheet.setLayoutManager(new GridLayoutManager(getActivity(), orientation == Configuration.ORIENTATION_PORTRAIT ? 3 : 4));

        numberAdapter = new NumberAdapter(position_counter, getActivity());
        numberAdapter.setClickListener(this::onItemClick);
        numberSheet.setAdapter(numberAdapter);

        button = view.findViewById(R.id.insertion_button);
        button.setOnClickListener(v -> {
            ++position_counter;
            numberAdapter.insert();});

        return view;
    }

    @Override
    public void onItemClick(View view, int position) {
        NumberFragment nextFrag = new NumberFragment(view);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_layout, nextFrag, "findThisFragment")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(POSITION_KEY, String.valueOf(position_counter));
    }
}