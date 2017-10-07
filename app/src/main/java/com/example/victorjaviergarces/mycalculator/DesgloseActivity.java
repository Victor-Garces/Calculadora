package com.example.victorjaviergarces.mycalculator;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import com.example.victorjaviergarces.mycalculator.Clase.Prestamo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DesgloseActivity extends AppCompatActivity {

    private double monto;
    private double tasaInteres;
    private double plazo;

    private void getValorExtra(){
        Intent intent = getIntent();
        monto = intent.getIntExtra("montoPrestamo",0);
        tasaInteres = intent.getIntExtra("tasaInteres",0);
        plazo = intent.getIntExtra("plazoMeses",0);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desglose);

        Intent intent = getIntent();

        //  Calculos del prestamo
        getValorExtra();
        Prestamo prest = new Prestamo();
        prest.Calculate(monto,tasaInteres,plazo);

//********************************************************************************************/
        String fecha = intent.getStringExtra("fecha");

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy",Locale.getDefault());
        Calendar cal  = Calendar.getInstance();
        try {
            cal.setTime(df.parse(fecha));
        } catch (ParseException e) {
            Toast.makeText(DesgloseActivity.this,"Error",Toast.LENGTH_SHORT).show();
        }
//********************************************************************************************/

        GridView listView = (GridView) findViewById(R.id.listView);
        String[] values = new String[(int) plazo];

        for(int i = 0; i < values.length; i++)
        {
            values[i] = "Pago #"+(i+1)+"\n"+
                        "Cuotas  :"+" "+String.format(Locale.getDefault(),"%.2f",prest.cuotas)+"\n"+
                        "Interes :"+" "+String.format(Locale.getDefault(),"%.2f",prest.interes)+"\n"+
                        "Amort.  :"+" "+String.format(Locale.getDefault(),"%.2f",prest.amortizacion)+"\n"+
                        "Balance :"+" "+String.format(Locale.getDefault(),"%.2f",prest.balance)+"\n"+
                        "Fecha   :"+" "+df.format(cal.getTime())+"\n";

            prest.interes = prest.tasa*prest.balance;
            prest.amortizacion = prest.cuotas - prest.interes;
            prest.balance = prest.balance - prest.amortizacion;
            cal.add(Calendar.MONTH,1);
        }
        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, values));

    }
}
