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

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * A simple {@link Fragment} subclass. Fragmento con la funcionalidad de borrar una nota.
 */
public class BorrarNota extends Fragment {

    private Button btnBorrar;
    private EditText txtNombreArchivo;

    public BorrarNota() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //obtiene la vista y el contexto
        final View view = inflater.inflate(R.layout.fragment_borrar_nota, container, false);
        final Context context = view.getContext();

        btnBorrar = view.findViewById(R.id.BtnBorrar);
        txtNombreArchivo = view.findViewById(R.id.TxtNombreArchivoBorrar);

        //comportamiento del boton crear
        btnBorrar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0)
            {
                Log.v("MainActivity", "Botón Crear pulsado!");

                String FILENAME = txtNombreArchivo.getText().toString() + ".txt";

                //intenta abrir el archivo para ver si existe
                if(context.deleteFile(FILENAME)) {
                    //Envia un Toast
                    CharSequence text = "El archivo " + FILENAME + " fue eliminado";
                    Toast toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
                    toast.show();
                }
                else {
                    //Envia un Toast
                    CharSequence text = "El archivo " + FILENAME +  " no existe" ;
                    Toast toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
                    toast.show();
                }

            }
        });


        // Inflate the layout for this fragment
        return view;
    }

}
