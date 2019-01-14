package com.example.jhovangallardo.proyectofinal;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;


/**
 * A simple {@link Fragment} subclass. Fragmento que permite editar una orden previamente seleccionada y guardar los cambios
 */
public class EditarFormulario2 extends Fragment {


    private EditText txtCliente;
    private Button btnAceptar;
    private Button btnCancelar;
    private CheckBox cbCargador;
    private CheckBox cbAudifonos;
    private RadioButton rbOpcion1_1;
    private RadioButton rbOpcion1_2;
    private RadioButton rbOpcion1_3;
    private RadioButton rbOpcion2_1;
    private RadioButton rbOpcion2_2;
    private RadioButton rbOpcion2_3;

    private String[] mMenuSections;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    private final String EXTRA_COMPUTADORA = "computadora";
    private final String EXTRA_PRECIO = "precio";



    private String cliente;
    private String procesador = "Intel® Core™ i3 (incluido en el precio base)";
    private String almacenamiento = "120 GB (incluido en el precio base)";
    private String cargador = "No";
    private String audifonos = "No";
    private int precio = 10000;
    private int procesadorIndex = 0;
    private int almacenamientoIndex = 0;

    private Computadora computadora;

    public EditarFormulario2(Computadora computadora) {
        // Required empty public constructor
        this.computadora = computadora;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_crear_formulario, container, false);

        txtCliente = view.findViewById(R.id.TxtNombre);

        txtCliente.setText(computadora.getCliente());

        btnAceptar = view.findViewById(R.id.BtnAceptar);

        //comportamiento del boton crear
        btnAceptar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0)
            {
                Log.v("MainActivity", "Botón Aceptar pulsado!");

                cliente = txtCliente.getText().toString();

                switch(procesadorIndex){
                    case 1:
                        precio += 5000;
                        break;
                    case 2:
                        precio += 7000;
                        break;
                }

                switch(almacenamientoIndex){
                    case 1:
                        precio += 7500;
                        break;
                    case 2:
                        precio += 10000;
                        break;
                }

                Intent intent = new Intent(getActivity(), ConfirmationActivity.class);
                Computadora nueva_computadora = new Computadora(cliente, procesador,almacenamiento,cargador,audifonos);
                intent.putExtra(EXTRA_COMPUTADORA, nueva_computadora);
                intent.putExtra(EXTRA_PRECIO, precio);
                ComputadoraBDD computadoraBdd = new ComputadoraBDD(getContext());
                computadoraBdd.openForWrite();
                computadoraBdd.updateComputadora(computadora.getId(),nueva_computadora);
                //computadoraBdd.insertComputadora(computadora);
                computadoraBdd.close();
                startActivity(intent);
            }
        });


        cbCargador = view.findViewById(R.id.ChkCargador);

        cbCargador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isChecked = ((CheckBox)view).isChecked();

                if (isChecked) {
                    Log.v("MainActivity", "Checkbox Cargador marcado!");
                    cargador = "Si";
                    precio += 1000;
                }
                else {
                    Log.v("MainActivity", "Checkbox Cargador desmarcado!");
                    cargador = "No";
                    precio -= 1000;
                }
            }
        });

        cbAudifonos = view.findViewById(R.id.ChkAudifonos);

        cbAudifonos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isChecked = ((CheckBox)view).isChecked();

                if (isChecked) {
                    Log.v("MainActivity", "Checkbox Audifonos marcado!");
                    audifonos = "Si";
                    precio += 600;
                }
                else {
                    Log.v("MainActivity", "Checkbox Audifonos desmarcado!");
                    audifonos = "No";
                    precio -= 600;
                }
            }
        });


        rbOpcion1_1 = view.findViewById(R.id.RbOpcion1_1);
        rbOpcion1_2 = view.findViewById(R.id.RbOpcion1_2);
        rbOpcion1_3 = view.findViewById(R.id.RbOpcion1_3);


        View.OnClickListener list1 = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String opcion = "";
                switch(view.getId()) {
                    case R.id.RbOpcion1_1:
                        opcion = "@string/opcion1_1";
                        procesador = rbOpcion1_1.getText().toString();
                        procesadorIndex = 0;
                        break;
                    case R.id.RbOpcion1_2:
                        opcion = "@string/opcion1_2";
                        procesador = rbOpcion1_2.getText().toString();
                        procesadorIndex = 1;
                        break;
                    case R.id.RbOpcion1_3:
                        opcion = "@string/opcion1_3";
                        procesador = rbOpcion1_3.getText().toString();
                        procesadorIndex = 2;
                        break;
                }

                Log.v("MainActivity", "Radio button " + opcion + " seleccinado!");
            }
        };

        rbOpcion1_1.setOnClickListener(list1);
        rbOpcion1_2.setOnClickListener(list1);
        rbOpcion1_3.setOnClickListener(list1);

        rbOpcion2_1 = view.findViewById(R.id.RbOpcion2_1);
        rbOpcion2_2 = view.findViewById(R.id.RbOpcion2_2);
        rbOpcion2_3 = view.findViewById(R.id.RbOpcion2_3);


        View.OnClickListener list2 = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String opcion = "";
                switch(view.getId()) {
                    case R.id.RbOpcion2_1:
                        opcion = "@string/opcion2_1";
                        almacenamiento = rbOpcion2_1.getText().toString();
                        almacenamientoIndex = 0;
                        break;
                    case R.id.RbOpcion2_2:
                        opcion = "@string/opcion2_2";
                        almacenamiento = rbOpcion2_2.getText().toString();
                        almacenamientoIndex = 1;
                        break;
                    case R.id.RbOpcion2_3:
                        opcion = "@string/opcion2_3";
                        almacenamiento = rbOpcion2_3.getText().toString();
                        almacenamientoIndex = 2;
                        break;
                }

                Log.v("MainActivity", "Radio button " + opcion + " seleccinado!");
            }
        };

        rbOpcion2_1.setOnClickListener(list2);
        rbOpcion2_2.setOnClickListener(list2);
        rbOpcion2_3.setOnClickListener(list2);
        // Inflate the layout for this fragment
        return view;
    }


}
