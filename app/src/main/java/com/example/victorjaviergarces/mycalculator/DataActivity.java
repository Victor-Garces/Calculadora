package com.example.victorjaviergarces.mycalculator;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Date;

public class DataActivity extends AppCompatActivity{
/*/*******************************************************************************************/

        /*DatePicker Variables*/
    Calendar mCalendar;
    TextView tv;
    int day,month,year;

        /*Variable de envio de datos*/
    Button aceptar;

        /*Variables de Entrada de Datos*/

/*/*******************************************************************************************/

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

    /*Envio de datos del Monto del Prestamo*/
        aceptar = (Button) findViewById(R.id.my_button);

        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DataActivity.this,DesgloseActivity.class);

                EditText text_monto = (EditText) findViewById(R.id.my_monto_prestamo);
                String monto = text_monto.getText().toString();

                EditText text_tasa = (EditText) findViewById(R.id.my_tasa_interes);
                String tasa = text_tasa.getText().toString();

                EditText text_plazo = (EditText) findViewById(R.id.my_plazo);
                String plazo = text_plazo.getText().toString();

                intent.putExtra("tasaInteres",tasa);
                intent.putExtra("montoPrestamo",monto);
                intent.putExtra("plazoMeses",plazo);

                startActivity(intent);
            }
        });

    /*Seteo de la fecha*/
        tv = (TextView) findViewById(R.id.my_fecha);
        mCalendar = Calendar.getInstance();

        day = mCalendar.get(Calendar.DAY_OF_MONTH);
        month = mCalendar.get(Calendar.MONTH)+1;
        year = mCalendar.get(Calendar.YEAR);

        tv.setText(day+"/"+month+"/"+year);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(DataActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                        monthOfYear = monthOfYear+1;
                        tv.setText(dayOfMonth+"/"+monthOfYear+"/"+year);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });
    }

}

