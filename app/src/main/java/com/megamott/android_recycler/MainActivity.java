package com.megamott.android_recycler;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity implements NumberAdapter.ItemClickListener{

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
        numberSheet.setLayoutManager(new GridLayoutManager(this, 3));
        numberAdapter = new NumberAdapter(20, this);
        numberAdapter.setClickListener(this::onItemClick);
        numberSheet.setAdapter(numberAdapter);
    }

    public void onClickInsert(View view){
        numberAdapter.insert();
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, String.valueOf(position + 1), Toast.LENGTH_SHORT).show();
    }
}