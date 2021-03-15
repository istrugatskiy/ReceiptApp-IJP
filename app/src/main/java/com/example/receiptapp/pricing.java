package com.example.receiptapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

public class pricing extends AppCompatActivity {

    Bundle extras;
    private int amountOfPeople;
    private Button continueButton;
    private RecyclerView recyclerView;
    // This is responsible for the list that shows how much each person pays.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        extras = getIntent().getExtras();
        amountOfPeople = extras.getInt("peopleEating");
        setContentView(R.layout.activity_pricing);
        initRecyclerViewer();
        continueButton = findViewById(R.id.continue2);
        // Adds click listener to this button to make it run the code whenever it is tapped
        continueButton.setOnClickListener(v -> {
            // This makes sure that all of the fields have valid input
            boolean validationSuccess = true;
            float subtotal = 0f;
            float tax = 0f;
            float tip = 0f;
            float total = 0f;
            float costPerPerson = 0f;
            // For each recyclerView element
            for (int i = 0; i < recyclerView.getChildCount(); i++) {
                // Gets the relativeLayout object which is the parent of the input field.
                final RelativeLayout childBefore = (RelativeLayout) recyclerView.getLayoutManager().findViewByPosition(i);
                // Checks if the child is null to prevent a crash if something goes wrong
                if (childBefore != null) {
                    // Gets the editText by getting the first child of the relativeLayout
                    final EditText child = (EditText) childBefore.getChildAt(0);
                    // Checks if the field is empty or has a value less than or equal to zero.
                    // If it does it will send an error message and prevent submission
                    if (child.getText().toString().trim().equals("") || Float.parseFloat(child.getText().toString()) <= 0) {
                        child.setError("Empty Field or Incorrect Format");
                        validationSuccess = false;
                    }
                    else {
                        // The system calculates all of the new values for the tax, tip and other stuff.
                        subtotal += Float.parseFloat(child.getText().toString());
                        tax = subtotal * ((float) extras.getFloat("taxRate")) / 100;
                        tip = subtotal * ((float) extras.get("tipAmount")) / 100;
                        total = subtotal + tax + tip;
                        costPerPerson = total / (float) extras.getInt("peopleSharingBill");
                    }
                }
                else {
                    validationSuccess = false;
                }
            }

            if(validationSuccess) {
                // If validation succeeds the system creates an Intent which is like a transition
                Intent intent = new Intent(v.getContext(), Results.class);
                // Puts data in the Intent so that it can be used by the next page.
                intent.putExtra("subtotal", subtotal);
                intent.putExtra("tax", tax);
                intent.putExtra("tip", tip);
                intent.putExtra("total", total);
                intent.putExtra("costPerPerson", costPerPerson);
                startActivity(intent);
            }
        });
    }

    // Initializes the list in which all of the individual pricing is put in.
    private void initRecyclerViewer() {
        recyclerView = findViewById(R.id.listOfPeople);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(amountOfPeople);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}