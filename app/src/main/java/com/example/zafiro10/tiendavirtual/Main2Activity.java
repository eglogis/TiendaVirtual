package com.example.zafiro10.tiendavirtual;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener{

    EditText edtNombre;
    EditText edtApellido;
    EditText edtApellido2;
    EditText edtDireccion;
    Button aceptar;
    Button cancelar;
    ArrayList<Cliente> filtradoCliente = new ArrayList<Cliente>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        edtNombre = (EditText)findViewById(R.id.edtNombre);
        edtApellido = (EditText)findViewById(R.id.edtApellido);
        edtApellido2 = (EditText)findViewById(R.id.edtApellido2);
        edtDireccion = (EditText)findViewById(R.id.edtDireccion);

        filtradoCliente = MainActivity.ControladorClientes.recuperarUnCliente(MainActivity.CodClienteModificar);

        edtNombre.setText(filtradoCliente.get(0).getNombreCliente());
        edtApellido.setText(filtradoCliente.get(0).getApellido1Cliente());
        edtApellido2.setText(filtradoCliente.get(0).getApellido2Cliente());
        edtDireccion.setText(filtradoCliente.get(0).getDireccionCliente());

        aceptar = (Button) findViewById(R.id.btnAceptar);
        aceptar.setOnClickListener(this);
        cancelar = (Button) findViewById(R.id.btnCancelar);
        cancelar.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        if(view.getId() == findViewById(R.id.btnAceptar).getId()) {

            MainActivity.ControladorClientes.modificarCliente(MainActivity.CodClienteModificar, edtNombre.getText().toString(), edtApellido.getText().toString(), edtApellido2.getText().toString(), edtDireccion.getText().toString());
            setResult(RESULT_OK);
            finish();
        }
        if(view.getId() == findViewById(R.id.btnCancelar).getId()) {

            finish();
        }

    }
}
