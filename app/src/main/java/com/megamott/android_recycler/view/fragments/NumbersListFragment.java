package com.megamott.android_recycler.view.fragments;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.megamott.android_recycler.R;
import com.megamott.android_recycler.view.adapter.ItemClickListener;
import com.megamott.android_recycler.view.adapter.NumberAdapter;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NumbersListFragment extends Fragment implements ItemClickListener {

    private static final String POSITION_KEY = "POSITION";
    private static final String FRAGMENT = "FRAGMENT";
    private RecyclerView numberSheet;
    private NumberAdapter numberAdapter;
    private Button button;
    private List<String> list;
    private int positionCounter = 10;

    enum ItemsInLine
    {
        PORTRAIT(2),
        LANDSCAPE(3);

        private final int value;

        ItemsInLine(final int newValue)
        {
            value = newValue;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null)
            positionCounter = Integer.parseInt(savedInstanceState.getString(POSITION_KEY));
            list = Stream.generate(() -> "Do it").limit(positionCounter).collect(Collectors.toList());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_numbers_list, container, false);

        numberSheet = view.findViewById(R.id.number_sheet);

        int orientation = Objects.requireNonNull(getActivity()).getResources().getConfiguration().orientation;
        numberSheet.setLayoutManager(new GridLayoutManager(getActivity(),
                orientation == Configuration.ORIENTATION_PORTRAIT ? ItemsInLine.PORTRAIT.value : ItemsInLine.LANDSCAPE.value));


        numberAdapter = new NumberAdapter(list, this.getContext(), this);
        numberSheet.setAdapter(numberAdapter);

        button = view.findViewById(R.id.insertion_button);
        button.setOnClickListener(v -> {
            list.add("Do it");
            numberAdapter.insert();
        });

        return view;
    }

    @Override
    public void onItemClick(Bundle args, int position) {
        NumberFragment nextFrag = NumberFragment.newInstance(args);

        Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_layout, nextFrag, FRAGMENT)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(POSITION_KEY, String.valueOf(list.size()));
    }
}