package com.megamott.android_recycler.view.screens.main;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.megamott.android_recycler.R;
import com.megamott.android_recycler.view.fragments.NumbersListFragment;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        setTitle(R.string.note_title);

        if (getSupportFragmentManager().findFragmentById(R.id.sheet_container_layout) == null){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.sheet_container_layout, new NumbersListFragment())
                    .commit();
        }
    }
}