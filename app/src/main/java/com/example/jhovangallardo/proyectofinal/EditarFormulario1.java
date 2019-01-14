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
 * A simple {@link Fragment} subclass. Fragmento con la funcionalidad de editar un orden almacenada en la base de datos.
 * Se introducen el id de la orden a editar.
 */
public class EditarFormulario1 extends Fragment {

    private Button btnEditar;
    private EditText txtIdFormulario;

    public EditarFormulario1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //obtiene la vista y el contexto
        final View view = inflater.inflate(R.layout.fragment_editar_formulario1, container, false);
        final Context context = view.getContext();

        btnEditar = view.findViewById(R.id.BtnEditarFormulario);
        txtIdFormulario = view.findViewById(R.id.TxtIdEditarFormulario);

        //comportamiento del boton editar
        btnEditar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0)
            {
                Log.v("MainActivity", "Botón Editar pulsado!");

                //intenta abrir el archivo para ver si existe
                try {
                    int id =  Integer.parseInt(txtIdFormulario.getText().toString());
                    ComputadoraBDD computadoraBdd = new ComputadoraBDD(getContext());
                    computadoraBdd.openForRead();
                    Computadora computadora = computadoraBdd.getComputadora(id);
                    computadoraBdd.close();
                    //Log.v("Editar1",computadora.toString());
                    //Envia un Toast
                    if (computadora==null)
                        throw new Exception();
                    CharSequence text = "El registro fue obtenido";
                    Toast toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
                    toast.show();
                    EditarFormulario2 editarFormulario2 = new EditarFormulario2(computadora);
                    android.support.v4.app.FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.fragment,editarFormulario2).commit();
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
