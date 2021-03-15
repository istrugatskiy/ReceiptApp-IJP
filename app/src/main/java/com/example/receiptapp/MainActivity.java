package com.example.receiptapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button continueButton;
    private EditText peopleSharingBill;
    private EditText peopleEating;
    private SeekBar tipAmount;
    private EditText taxRate;
    private TextView percentText;
    // This makes sure that all of the fields have valid input
    private boolean validationSuccess = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Sets up references to all of the buttons and inputs needed by the code.
        peopleSharingBill = findViewById(R.id.peopleSharingBill);
        peopleEating = findViewById(R.id.peopleEating);
        tipAmount = findViewById(R.id.tipAmount);
        taxRate = findViewById(R.id.taxRate);
        continueButton = findViewById(R.id.continue1);
        percentText = findViewById(R.id.percentText1);
        // Adds an event listener to horizontal slider to change the percent text whenever you change it
        tipAmount.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                percentText.setText("" + progress + "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        // Adds a click listener so that this code can be run whenever you click on the continueButton
        continueButton.setOnClickListener(v -> {
            // Prevents weirdnesses with integers and always better be safe with pesky user input
            validationSuccess = true;
            // Validates all of the fields
            validateTextView(peopleSharingBill);
            validateTextView(taxRate);
            if(peopleEating.getText().toString().trim().equals("") || Integer.parseInt(peopleEating.getText().toString()) <= 0 || Integer.parseInt(peopleEating.getText().toString()) > 9) {
                peopleEating.setError( "Empty Field or Incorrect Format" );
                validationSuccess = false;
            }
            if(validationSuccess) {
                // Starts Intent which is like scene transition
                Intent intent = new Intent(v.getContext(), pricing.class);
                intent.putExtra("peopleEating", Integer.parseInt(peopleEating.getText().toString()));
                intent.putExtra("tipAmount", (float) tipAmount.getProgress());
                intent.putExtra("peopleSharingBill", Integer.parseInt(peopleSharingBill.getText().toString()));
                intent.putExtra("taxRate", Float.parseFloat(taxRate.getText().toString()));
                startActivity(intent);
            }
        });
    }

    // A function for validating a textView
    private void validateTextView(TextView text) {
        if(text.getText().toString().trim().equals("") || Integer.parseInt(text.getText().toString()) <= 0) {
            text.setError( "Empty Field or Incorrect Format" );
            validationSuccess = false;
        }

    }
}