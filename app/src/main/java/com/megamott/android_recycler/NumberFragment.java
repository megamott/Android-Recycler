package com.megamott.android_recycler;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class NumberFragment extends Fragment {

    private static final String COLOR = "color";
    private static final String TEXT = "text";
    private TextView currentNumberView;

    public static NumberFragment newInstance(TextView necessaryView){
        Bundle args = new Bundle();
        args.putInt(COLOR , necessaryView.getCurrentTextColor());
        args.putCharSequence(TEXT, necessaryView.getText());
        NumberFragment numberFragment = new NumberFragment();
        numberFragment.setArguments(args);
        return numberFragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (savedInstanceState != null) this.setArguments(savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_number, container, false);

        currentNumberView = view.findViewById(R.id.current_number);
        currentNumberView.setTextColor(getArguments().getInt(COLOR));
        currentNumberView.setText(getArguments().getCharSequence(TEXT));
        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putAll(getArguments());
    }
}