package com.megamott.android_recycler;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class NumbersListFragment extends Fragment implements NumberAdapter.ItemClickListener{

    private RecyclerView numberSheet;
    private NumberAdapter numberAdapter;
    private Button button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_numbers_list, container, false);

        numberSheet = view.findViewById(R.id.number_sheet);

        int orientation = getActivity().getResources().getConfiguration().orientation;
        numberSheet.setLayoutManager(new GridLayoutManager(getActivity(), orientation == Configuration.ORIENTATION_PORTRAIT ? 3 : 4));

        numberAdapter = new NumberAdapter(20, getActivity());
        numberAdapter.setClickListener(this::onItemClick);
        numberSheet.setAdapter(numberAdapter);

        button = view.findViewById(R.id.insertion_button);
        button.setOnClickListener(v -> numberAdapter.insert());
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
}