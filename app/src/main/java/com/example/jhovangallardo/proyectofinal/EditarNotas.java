package com.example.jhovangallardo.proyectofinal;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * A simple {@link Fragment} subclass.
 */
public class EditarNotas extends Fragment {
    private Button btnAbrir;
    private Button btnGuardar;
    private EditText txtNombreArchivo;
    private EditText txtContenido;
    private String FILENAME;
    private String str;

    public EditarNotas() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //obtiene la vista y el contexto
        final View view = inflater.inflate(R.layout.fragment_editar_notas, container, false);
        final Context context = view.getContext();
        btnAbrir = view.findViewById(R.id.BtnAbrir);
        btnGuardar =  view.findViewById(R.id.BtnGuardar);
        txtNombreArchivo = view.findViewById(R.id.TxtNombreArchivoEditar);
        txtContenido = view.findViewById(R.id.TxtContenidoEditar);



        btnAbrir.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View arg0) {
                FILENAME = txtNombreArchivo.getText().toString() + ".txt";
                //abre el archivo
                StringBuilder content = new StringBuilder();

                FileInputStream fis;
                try {
                    fis = context.openFileInput(FILENAME);
                    byte[] buffer = new byte[1024];
                    //StringBuilder content = new StringBuilder();
                    while ((fis.read(buffer)) != -1) {
                        content.append(new String(buffer));
                    }
                    Log.d("Ch10", "content = " + content);
                    txtNombreArchivo.setEnabled(false);
                    btnAbrir.setEnabled(false);
                    txtContenido.setEnabled(true);
                    btnGuardar.setEnabled(true);
                    txtContenido.setText(content);
                } catch (FileNotFoundException e) {
                    //Envia un Toast
                    CharSequence text = "El archivo no existe" ;
                    Toast toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
                    toast.show();
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0)
            {
                Log.v("MainActivity", "Botón Crear pulsado!");

                str = txtContenido.getText().toString();

                //Escribe el archivo
                FileOutputStream fos;
                try {
                    fos = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
                    fos.write(str.getBytes());
                    fos.close();

                    //Envia un Toast
                    CharSequence text = "Archivo guardado en " + FILENAME;
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    txtContenido.setEnabled(false);
                    btnGuardar.setEnabled(false);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        // Inflate the layout for this fragment
        return view;
    }

}
