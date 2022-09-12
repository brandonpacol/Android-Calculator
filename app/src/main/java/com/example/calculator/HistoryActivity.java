package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class HistoryActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        if (MainActivity.history_list.isEmpty()) {
            TextView empty = findViewById(R.id.no_history);
            empty.setVisibility(View.VISIBLE);
        } else {
            recyclerView = findViewById(R.id.recyclerView);
            recyclerView.setHasFixedSize(true);
            mLayoutManager = new LinearLayoutManager(this);
            mAdapter = new RecycleViewAdapter(MainActivity.history_list);
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setAdapter(mAdapter);
            recyclerView.scrollToPosition(MainActivity.history_list.size()-1);
        }
    }

    public void backButton(View view) {
        Intent intent = new Intent (this, MainActivity.class);
        startActivity(intent);
    }

    public void clearButton(View view) {
        MainActivity.history_list.clear();
        TextView empty = findViewById(R.id.no_history);
        empty.setVisibility(View.VISIBLE);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setVisibility(View.INVISIBLE);
    }
}