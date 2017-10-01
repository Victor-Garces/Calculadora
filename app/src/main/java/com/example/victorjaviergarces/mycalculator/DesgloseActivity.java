package com.example.victorjaviergarces.mycalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DesgloseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desglose);

        Intent intent = getIntent();

        TextView montoDet = (TextView) findViewById(R.id.my_montoDet);
        String monto = intent.getStringExtra("montoPrestamo");
        montoDet.setText(monto);

        TextView tasaDet = (TextView) findViewById(R.id.my_tasaDet);
        String tasa = intent.getStringExtra("tasaInteres");
        tasaDet.setText(tasa);

        TextView plazoDet = (TextView) findViewById(R.id.my_plazoMes);
        String plazo = intent.getStringExtra("plazoMeses");
        plazoDet.setText(plazo);

    }
}
