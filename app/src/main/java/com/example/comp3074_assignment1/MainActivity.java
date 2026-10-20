package com.example.comp3074_assignment1;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etHours, etRate, etTax;
    private TextView tvPay, tvOvertime, tvTotal, tvTax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etHours = findViewById(R.id.etHours);
        etRate = findViewById(R.id.etRate);
        etTax = findViewById(R.id.etTax);
        tvPay = findViewById(R.id.tvPay);
        tvOvertime = findViewById(R.id.tvOvertime);
        tvTotal = findViewById(R.id.tvTotal);
        tvTax = findViewById(R.id.tvTax);

        Button btnCalculate = findViewById(R.id.btnCalculate);
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate();
            }
        });
    }

    private void calculate() {
        String hoursStr = etHours.getText().toString().trim();
        String rateStr = etRate.getText().toString().trim();
        String taxStr = etTax.getText().toString().trim();

        if (hoursStr.isEmpty() || rateStr.isEmpty() || taxStr.isEmpty()) {
            Toast.makeText(this, R.string.error_empty, Toast.LENGTH_SHORT).show();
            return;
        }

        double hours, rate, taxRate;
        try {
            hours = Double.parseDouble(hoursStr);
            rate = Double.parseDouble(rateStr);
            // Tax rate entered as a percentage, e.g. 15 means 15%
            taxRate = Double.parseDouble(taxStr) / 100.0;
        } catch (NumberFormatException e) {
            Toast.makeText(this, R.string.error_invalid, Toast.LENGTH_SHORT).show();
            return;
        }

        double pay, overtimePay;
        if (hours <= 40) {
            pay = hours * rate;
            overtimePay = 0;
        } else {
            pay = 40 * rate;
            overtimePay = (hours - 40) * rate * 1.5;
        }
        double totalPay = pay + overtimePay;
        double tax = pay * taxRate; // per assignment formula: tax = pay * tax_rate

        tvPay.setText(getString(R.string.label_pay, pay));
        tvOvertime.setText(getString(R.string.label_overtime, overtimePay));
        tvTotal.setText(getString(R.string.label_total, totalPay));
        tvTax.setText(getString(R.string.label_tax, tax));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_about) {
            startActivity(new Intent(this, AboutActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
