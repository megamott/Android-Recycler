package com.megamott.android_recycler;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private RecyclerView numberSheet;
    private NumberAdapter numberAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        numberSheet = findViewById(R.id.number_sheet);
        numberSheet.setLayoutManager(new LinearLayoutManager(this));
        numberAdapter = new NumberAdapter(20, this);
        numberSheet.setAdapter(numberAdapter);
    }

    public void onClickInsert(View view){
        numberAdapter.insert();
    }
}