package com.example.calculator;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ExampleViewHolder> {

    private ArrayList<RowItem> mExampleList;

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView1;
        public TextView mTextView2;

        public ExampleViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextView1 = itemView.findViewById(R.id.myText1);
            mTextView2 = itemView.findViewById(R.id.myText2);
        }
    }

    public RecycleViewAdapter(ArrayList<RowItem> exampleList) {
        mExampleList = exampleList;
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_row, parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
        RowItem currentItem = mExampleList.get(position);
        holder.mTextView1.setText(currentItem.getText1());
        holder.mTextView2.setText(currentItem.getText2());
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }
}
