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
public class CrearNota extends Fragment {

    private Button btnCrear;
    private EditText txtNombreArchivo;
    private EditText txtContenido;

    public CrearNota() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //obtiene la vista y el contexto
        final View view = inflater.inflate(R.layout.fragment_crear_nota, container, false);
        final Context context = view.getContext();

        btnCrear = view.findViewById(R.id.BtnCrear);
        txtNombreArchivo = view.findViewById(R.id.TxtNombreArchivoCrear);
        txtContenido = view.findViewById(R.id.TxtContenidoCrear);

        //comportamiento del boton crear
        btnCrear.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0)
            {
                Log.v("MainActivity", "Botón Crear pulsado!");

                String FILENAME = txtNombreArchivo.getText().toString() + ".txt";
                String str = txtContenido.getText().toString();

                //intenta abrir el archivo para ver si existe
                try {
                    context.openFileInput(FILENAME);
                    //Envia un Toast
                    CharSequence text = "El archivo " + FILENAME +  " ya existe" ;
                    Toast toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
                    toast.show();
                } catch (FileNotFoundException e) {
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
                    } catch (FileNotFoundException err) {
                        err.printStackTrace();
                    } catch (IOException err) {
                        err.printStackTrace();
                    }
                }
            }
        });


        // Inflate the layout for this fragment
        return view;
    }

}
