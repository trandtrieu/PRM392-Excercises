package com.example.simpleui;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class SimpleCalculator extends AppCompatActivity {

    private EditText editTextNumber;
    private EditText editTextNumber2;
    private EditText editTextNumberSigned;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator);

        editTextNumber = findViewById(R.id.editTextNumber);
        editTextNumber2 = findViewById(R.id.editTextNumber2);
        editTextNumberSigned = findViewById(R.id.editTextNumberSigned);
    }

    public void onAddClicked(View view) {
        performOperation(Operation.ADD);
    }

    public void onSubClicked(View view) {
        performOperation(Operation.SUBTRACT);
    }

    public void onMulClicked(View view) {
        performOperation(Operation.MULTIPLY);
    }

    public void onDivClicked(View view) {
        performOperation(Operation.DIVIDE);
    }

    private void performOperation(Operation operation) {
        String num1Str = editTextNumber.getText().toString();
        String num2Str = editTextNumber2.getText().toString();

        if (!num1Str.isEmpty() && !num2Str.isEmpty()) {
            double num1 = Double.parseDouble(num1Str);
            double num2 = Double.parseDouble(num2Str);
            double result = 0;

            switch (operation) {
                case ADD:
                    result = num1 + num2;
                    break;
                case SUBTRACT:
                    result = num1 - num2;
                    break;
                case MULTIPLY:
                    result = num1 * num2;
                    break;
                case DIVIDE:
                    if (num2 != 0) {
                        result = num1 / num2;
                    } else {
                        editTextNumberSigned.setText("Error");
                        return;
                    }
                    break;
            }
            editTextNumberSigned.setText(String.valueOf(result));
        } else {
            editTextNumberSigned.setText("Error");
        }
    }

    private enum Operation {
        ADD, SUBTRACT, MULTIPLY, DIVIDE
    }
}
