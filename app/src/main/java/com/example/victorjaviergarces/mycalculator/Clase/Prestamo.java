package com.example.victorjaviergarces.mycalculator.Clase;

public class Prestamo {

    public double tasa;
    public double interes;
    public double cuotas;
    public double amortizacion;
    public double balance;

    public Prestamo Calculate(double monto,double tasaInteres, double plazo){
        this.tasa = tasaInteres/100;
        this.interes = tasa*monto;
        this.cuotas = monto *((tasa * (1 + tasa) * plazo) / (((1 + tasa)*plazo)-1))*4.536;
        this.amortizacion = cuotas - interes;
        this.balance =  monto - amortizacion;

        return this;
    }
}
