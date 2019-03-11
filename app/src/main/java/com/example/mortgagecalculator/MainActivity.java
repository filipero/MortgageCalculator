package com.example.mortgagecalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.view.inputmethod.InputMethodManager;
import android.content.Context;



public class MainActivity extends AppCompatActivity {

    EditText edtHousePrice, edtDownPayment, edtInterestRate, edtTerms;
    TextView txtBiMonthly, txtMonthly;
    Button btnCalculate, btnClear;

    Double housePrice= 0d;
    Double downPayment= 0d;
    Double interestRates = 0d;
    Double terms = 0d;

    Double totalMortgage = 0d;
    Double biMonthlyPayment = 0d;
    Double monthlyPayment = 0d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonLogic();

    }

    private void buttonLogic(){
        btnCalculate = findViewById(R.id.buttonCalculate);
        btnClear = findViewById(R.id.buttonClear);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                calculate();
                dismissKeyboard();
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                clear();
                dismissKeyboard();
            }
        });

    }

    private void calculate(){

        edtHousePrice = findViewById(R.id.editTextHousePrice);
        edtDownPayment = findViewById(R.id.editTextDownPayment);
        edtInterestRate = findViewById(R.id.editTextInterestRate);
        edtTerms = findViewById(R.id.editTextTerms);

        housePrice = Double.parseDouble(edtHousePrice.getText().toString());
        downPayment = Double.parseDouble(edtDownPayment.getText().toString());
        interestRates = (Double.parseDouble(edtInterestRate.getText().toString())) / 100;
        terms = Double.parseDouble(edtTerms.getText().toString());

        monthlyPayment = (downPayment * interestRates * Math.pow( 1 + interestRates , terms )/( Math.pow( 1 + interestRates, terms ) ) -1);
        biMonthlyPayment = (monthlyPayment / 2);



        printResults();
    }

    private void clear(){
        txtBiMonthly = findViewById(R.id.textViewBiMonthlyPayment);
        txtMonthly = findViewById(R.id.textViewMonthlyPayment);

        edtHousePrice = findViewById(R.id.editTextHousePrice);
        edtDownPayment = findViewById(R.id.editTextDownPayment);
        edtInterestRate = findViewById(R.id.editTextInterestRate);
        edtTerms = findViewById(R.id.editTextTerms);

        txtMonthly.setText("Monthly Payment");
        txtBiMonthly.setText("Bi-Monthly Payment");

        edtHousePrice.setText("");
        edtDownPayment.setText("");
        edtInterestRate.setText("");
        edtTerms.setText("");

    }

    private void printResults(){
        txtBiMonthly = findViewById(R.id.textViewBiMonthlyPayment);
        txtMonthly = findViewById(R.id.textViewMonthlyPayment);

        String strMonthly = String.format("%1.2f",monthlyPayment);
        String strBiMonthly = String.format("%1.2f",biMonthlyPayment);

        txtMonthly.setText("Monthly Payment: $" +strMonthly);
        txtBiMonthly.setText("Bi-Monthly Payment: $" +strBiMonthly);
    }

    private void dismissKeyboard(){
        edtHousePrice = findViewById(R.id.editTextHousePrice);
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(edtHousePrice.getWindowToken(),0);
    }

    public void onClick(View v){
        dismissKeyboard();
    }
}
