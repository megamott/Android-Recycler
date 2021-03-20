package com.megamott.android_recycler;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class NumbersListFragment extends Fragment implements NumberAdapter.ItemClickListener{

    private RecyclerView numberSheet;
    private NumberAdapter numberAdapter;
    private Button button;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_numbers_list, container, false);

        numberSheet = view.findViewById(R.id.number_sheet);
        numberSheet.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        numberAdapter = new NumberAdapter(20, getActivity());
        numberAdapter.setClickListener(this::onItemClick);
        numberSheet.setAdapter(numberAdapter);

        button = view.findViewById(R.id.insertion_button);
        button.setOnClickListener(v -> numberAdapter.insert());
        return view;
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(getActivity(), String.valueOf(position + 1), Toast.LENGTH_SHORT).show();
    }
}