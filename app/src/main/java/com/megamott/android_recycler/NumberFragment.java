package com.megamott.android_recycler;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class NumberFragment extends Fragment {

    private TextView necessaryNumberView;
    private TextView currentNumberView;

    public NumberFragment(View necessaryNumberView) {
        this.necessaryNumberView = necessaryNumberView.findViewById(R.id.number_element);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_number, container, false);

        currentNumberView = view.findViewById(R.id.current_number);
        currentNumberView.setTextColor(necessaryNumberView.getCurrentTextColor());
        currentNumberView.setText(necessaryNumberView.getText());
        return view;
    }
}