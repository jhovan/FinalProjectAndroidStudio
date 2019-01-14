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


/**
 * A simple {@link Fragment} subclass.
 */
public class BorrarFormulario extends Fragment {

    private Button btnBorrar;
    private EditText txtIdFormulario;

    public BorrarFormulario() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //obtiene la vista y el contexto
        final View view = inflater.inflate(R.layout.fragment_borrar_formulario, container, false);
        final Context context = view.getContext();

        btnBorrar = view.findViewById(R.id.BtnBorrarFormulario);
        txtIdFormulario = view.findViewById(R.id.TxtIdBorrarFormulario);

        //comportamiento del boton borrar
        btnBorrar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0)
            {
                Log.v("MainActivity", "Botón Borrar pulsado!");

                //intenta abrir el archivo para ver si existe
                try {
                    int id =  Integer.parseInt(txtIdFormulario.getText().toString());
                    ComputadoraBDD computadoraBdd = new ComputadoraBDD(getContext());
                    computadoraBdd.openForWrite();
                    computadoraBdd.removeComputadora(id);
                    computadoraBdd.close();
                    //Envia un Toast
                    CharSequence text = "El registro fue eliminado";
                    Toast toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
                    toast.show();
                }
                catch (Exception error){
                    //Envia un Toast
                    CharSequence text = "Id no valido" ;
                    Toast toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
                    toast.show();
                }

            }
        });


        // Inflate the layout for this fragment
        return view;
    }

}
