package com.example.inynieriasystemwmobilnych;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CircleActivity extends AppCompatActivity {
    public static final String RESULT = "Area of circle";
    private Circle c;

    class Circle {
        private double r;

        Circle(double _r) {
            r = _r;
        }
        double area() {
            return Math.PI*r*r;
        }
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle);
        findViewById(R.id.circleCaclcButton).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        hideKeyboard();
                        try {
                            double r = Double.parseDouble(((EditText) findViewById(R.id.rEditText)).getText().toString());

                            c = new Circle(r);
                            ((TextView) findViewById(R.id.resultTextView)).setText(String.valueOf(c.area()));
                        } catch (NumberFormatException e) {
                            Toast.makeText(CircleActivity.this, "Podaj długość promienia", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
        findViewById(R.id.addButton).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            Double area_string = (c.area());
                            Intent backIntent = new Intent();
                            backIntent.putExtra(RESULT, area_string);
                            setResult(RESULT_OK, backIntent);
                            finish();
                        } catch (Exception e) {
                            Toast.makeText(CircleActivity.this, "Podaj długości wszystkich boków", Toast.LENGTH_LONG).show();
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

    void hideKeyboard() {
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(RectangleActivity.INPUT_METHOD_SERVICE);

        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
    }
}
