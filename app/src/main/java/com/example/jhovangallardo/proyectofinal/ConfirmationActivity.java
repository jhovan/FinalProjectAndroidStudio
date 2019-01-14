package com.example.jhovangallardo.proyectofinal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Actividad que muestra un mensaje de confirmacion cuando se compra una computadora
 */

public class ConfirmationActivity extends Activity{
    private TextView cliente;
    private TextView procesador;
    private TextView almacenamiento;
    private TextView cargador;
    private TextView audifonos;
    private TextView total;

    private int precio;
    private final String EXTRA_COMPUTADORA = "computadora";
    private final String EXTRA_PRECIO = "precio";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirmation);

        Intent intent = getIntent();
        Computadora computadora = intent.getParcelableExtra(EXTRA_COMPUTADORA);
        precio = intent.getIntExtra(EXTRA_PRECIO,0);


        cliente = (TextView) findViewById(R.id.computadoraCliente);
        cliente.setText("Nombre: " + computadora.getCliente());
        procesador = (TextView) findViewById(R.id.computadoraProcesador);
        procesador.setText("Procesador: " + computadora.getProcesador());
        almacenamiento = (TextView) findViewById(R.id.computadoraAlmacenamiento);
        almacenamiento.setText("Almacenamiento: " + computadora.getAlmacenamiento());
        cargador = (TextView) findViewById(R.id.computadoraCargador);
        cargador.setText("Cargador: " + computadora.getCargador());
        audifonos = (TextView) findViewById(R.id.computadoraAudifonos);
        audifonos.setText("Audifonos: " + computadora.getAudifonos());
        total = findViewById(R.id.computadoraTotal);
        //total.setText("Total: $" + precio);
    }
}