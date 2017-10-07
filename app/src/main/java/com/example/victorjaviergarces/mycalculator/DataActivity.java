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
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Locale;


public class DataActivity extends AppCompatActivity{
//********************************************************************************************/

        /*DatePicker Variables*/
    Calendar mCalendar;
    TextView tv;
    int day,month,year;
    private SimpleDateFormat mDateFormat;

        /*Variable de envio de datos*/
    Button aceptar;


//********************************************************************************************/

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        /*Seteo de la fecha*/
                tv = (TextView) findViewById(R.id.my_fecha);
                mCalendar = Calendar.getInstance();
                mDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());


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
                                monthOfYear+=1;
                                tv.setText(dayOfMonth+"/"+monthOfYear+"/"+year);
                                mCalendar.set(Calendar.YEAR,year);
                                mCalendar.set(Calendar.MONTH,monthOfYear-1);
                                mCalendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                            }
                        },year,month,day);

                        datePickerDialog.show();
                    }
                });

//********************************************************************************************/

        aceptar = (Button) findViewById(R.id.my_button);
        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*Datos del Monto del Prestamo*/

                Intent intent = new Intent(DataActivity.this,DesgloseActivity.class);

                String fechaa = (mDateFormat.format(mCalendar.getTime()));

                EditText textMonto = (EditText) findViewById(R.id.my_monto_prestamo);
                EditText textTasa = (EditText) findViewById(R.id.my_tasa_interes);
                EditText textPlazo = (EditText) findViewById(R.id.my_plazo);

                String sMonto =textMonto.getText().toString();
                String sTasa =textTasa.getText().toString();
                String sPlazo =textPlazo.getText().toString();

                if (sMonto.matches("")||sMonto.matches("")||sMonto.matches("")) {
                    Toast.makeText(DataActivity.this, "Rellene todos los campos", Toast.LENGTH_SHORT).show();
                }
                else{
                    int monto = Integer.parseInt(sMonto);
                    int interes = Integer.parseInt(sTasa);
                    int plazo = Integer.parseInt(sPlazo);

                    intent.putExtra("tasaInteres",interes);
                    intent.putExtra("montoPrestamo",monto);
                    intent.putExtra("plazoMeses",plazo);
                    intent.putExtra("fecha",fechaa);

                    startActivity(intent);
                }

            }
        });
    }
}