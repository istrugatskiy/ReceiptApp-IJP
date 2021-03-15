package com.example.receiptapp;

import android.app.Application;
import android.content.Context;
import android.os.Debug;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

// Deals with creating the second menu's person input list
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    @NonNull
    // Boilerplate which happens the second page's listView and renders it
    private int amountOfPeople;

    public RecyclerViewAdapter(int amountOfPeople) {
        this.amountOfPeople = amountOfPeople;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layoutinput, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.input.setHint("Meal Cost (Person " + (position + 1) + ")");
    }

    @Override
    public int getItemCount() {
        return amountOfPeople;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        EditText input;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            input = (EditText) itemView.findViewById(R.id.mealCost);
        }
    }
}
