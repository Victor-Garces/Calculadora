package com.example.victorjaviergarces.mycalculator;

import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;

import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DesgloseActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desglose);

        Intent intent = getIntent();

        /*  Valores para el prestamo  */
        double monto = intent.getIntExtra("montoPrestamo",0);
        double tasaInteres = intent.getIntExtra("tasaInteres",0);
        double tasaInteres_100 = tasaInteres/100;
        double plazo = intent.getIntExtra("plazoMeses",0);
        double interes = tasaInteres_100*monto;
        double cuotas = monto *((tasaInteres_100 * (1 + tasaInteres_100) * plazo) / (((1 + tasaInteres_100)*plazo)-1))*4.536;
        double amortizacion = cuotas - interes;
        double balance =  monto - amortizacion;

/********************************************************************************************/
/*
        TextView tb = (TextView) findViewById(R.id.topBar);
        tb.setText("   Cuotas    Interes    Amort.    Balance   Fecha");
/*
        final Calendar c ;
        c = (Calendar) intent.getSerializableExtra("calendar");
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH)+1;
        int mDay = c.get(Calendar.DAY_OF_MONTH);
*/
        int mDay = intent.getIntExtra("day",0);
        int mMonth = intent.getIntExtra("month",0);
        int mYear = intent.getIntExtra("year",0);

        String fecha = mYear+"-"+ mMonth+"-"+mDay;

/********************************************************************************************/

        GridView listView = (GridView) findViewById(R.id.listView);
        String[] values = new String[(int) plazo];

        for(int i = 0; i < values.length; i++)
        {
            values[i] = "Pago #"+(i+1)+"\n"+
                        "Cuotas  :"+" "+ String.format("%.2f",cuotas)+"\n"+
                        "Interes :"+" "+String.format("%.2f",interes)+"\n"+
                        "Amort.  :"+" "+String.format("%.2f",amortizacion)+"\n"+
                        "Balance :"+" "+String.format("%.2f",balance)+"\n"+
                        "Fecha   :"+" "+fecha+"\n";

            interes = tasaInteres_100*balance;
            amortizacion = cuotas - interes;
            balance = balance - amortizacion;
        }
        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, values));

    }




}
