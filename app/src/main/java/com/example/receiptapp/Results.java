package com.example.receiptapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.text.DecimalFormat;

public class Results extends AppCompatActivity {

    private Bundle extras;
    private TextView resultsText;
    private String finalText = "";
    private DecimalFormat df = new DecimalFormat("0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        extras = getIntent().getExtras();
        // Parses the Intent and gets the values I put into it
        resultsText = findViewById(R.id.resultsText);
        // Adds all the values to the Intent
        finalText += "Subtotal: $" + df.format(extras.getFloat("subtotal")) + "\n";
        finalText += "Tax: $" + df.format(extras.get("tax")) + "\n";
        finalText += "Tip: $" + df.format(extras.get("tip")) + "\n";
        finalText += "Total: $" + df.format(extras.get("total")) + "\n";
        finalText += "Cost/person: $" + df.format(extras.get("costPerPerson")) + "\n";

        resultsText.setText(finalText);
    }
}