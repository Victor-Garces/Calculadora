package com.example.victorjaviergarces.mycalculator;

import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
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

        // Valores para el prestamo
        double monto = intent.getIntExtra("montoPrestamo",0);

        double tasaInteres = intent.getIntExtra("tasaInteres",0);
        double tasaInteres_100 = tasaInteres/100;

        double plazo = intent.getIntExtra("plazoMeses",0);

        double interes = tasaInteres_100*monto;

        double cuotas = monto *((tasaInteres_100 * (1 + tasaInteres_100) * plazo) / (((1 + tasaInteres_100)*plazo)-1))*4.536;

        double amortizacion = cuotas - interes;

        double balance =  monto - amortizacion;
        // // // // //------------------------ // // // //


        TextView tb = (TextView) findViewById(R.id.topBar);
        tb.setText("\t\t\t"+"Cuotas"+"\t\t\t"+"Interes"+"\t\t\t"+"Amortizacion"+"\t\t\t"+"Balance");

        /*final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        TextView time=(TextView) findViewById(R.id.my_plazoMes);
        time.setText(mYear+" "+ mMonth+" "+mDay+" ");*/

        /* LIST VIEW */
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String currentDate = sdf.format(new Date());

        ListView listView = (ListView) findViewById(R.id.listView);

        String[] values = new String[(int) plazo];

        for(int i = 0; i < values.length; i++)
        {
            values[i] = String.format("%.2f",cuotas)+
                    "\t\t\t"+String.format("%.2f",interes)+
                    "\t\t\t\t"+String.format("%.2f",amortizacion)+
                    "\t\t\t\t\t\t\t\t\t\t"+String.format("%.2f",balance);

            interes = tasaInteres_100*balance;
            amortizacion = cuotas - interes;
            balance = balance - amortizacion;
        }
        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, values));

    }


}
