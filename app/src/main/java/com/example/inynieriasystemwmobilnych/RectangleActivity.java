package com.example.inynieriasystemwmobilnych;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RectangleActivity extends CalcActivity {
    private RectangleActivity.Rectangle r;

    class Rectangle {
        private double a, b, c;

        Rectangle(double _a, double _b) {
            a = _a;
            b = _b;
        }

        double area() {
            return a * b;
        }
    }

    Rectangle parse() {
        double a = Double.parseDouble(((EditText) findViewById(R.id.aEditText)).getText().toString());
        double b = Double.parseDouble(((EditText) findViewById(R.id.bEditText)).getText().toString());
        return new Rectangle(a, b);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rectangle);
        findViewById(R.id.rectangleCaclcButton).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        hideKeyboard();
                        try {
                            r = parse();
                            ((TextView) findViewById(R.id.resultTextView)).setText(String.valueOf(r.area()));
                        } catch (NumberFormatException e) {
                            Toast.makeText(RectangleActivity.this, "Podaj długości wszystkich boków", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
        findViewById(R.id.addButton).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            r = parse();
                            Double area_string = (r.area());
                            Intent backIntent = new Intent();
                            backIntent.putExtra(RESULT, area_string);
                            setResult(RESULT_OK, backIntent);
                            finish();
                        } catch (Exception e) {
                            Toast.makeText(RectangleActivity.this, "Podaj długości wszystkich boków", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
        findViewById(R.id.backButton).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent backIntent = new Intent();
                        backIntent.putExtra(RESULT, "0.0");
                        setResult(RESULT_OK, backIntent);
                        finish();
                    }
                }
        );
    }
}

