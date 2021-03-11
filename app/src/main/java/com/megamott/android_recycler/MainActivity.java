package com.megamott.android_recycler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private RecyclerView numberSheet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numberSheet = findViewById(R.id.number_sheet);
        numberSheet.setLayoutManager(new LinearLayoutManager(this));
        numberSheet.setAdapter(new NumberAdapter(100, this));
    }
}