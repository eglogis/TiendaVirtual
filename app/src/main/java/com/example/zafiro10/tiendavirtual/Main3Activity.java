package com.example.zafiro10.tiendavirtual;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Main3Activity extends AppCompatActivity implements View.OnClickListener {

    EditText edtNombre;
    EditText edtApellido;
    EditText edtApellido2;
    EditText edtDireccion;
    Button aceptar;
    Button cancelar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        edtNombre = (EditText) findViewById(R.id.edtNombre);
        edtApellido = (EditText) findViewById(R.id.edtApellido);
        edtApellido2 = (EditText) findViewById(R.id.edtApellido2);
        edtDireccion = (EditText) findViewById(R.id.edtDireccion);

        aceptar = (Button) findViewById(R.id.btnAceptar);
        aceptar.setOnClickListener(this);

        cancelar = (Button) findViewById(R.id.btnCancelar);
        cancelar.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

        if (view.getId() == findViewById(R.id.btnAceptar).getId()) {

            if ((edtNombre.getText().toString().trim().length() != 0) || (edtApellido.getText().toString().trim().length() != 0) || (edtApellido2.getText().toString().trim().length() != 0)) {

                MainActivity.ControladorClientes.insertarCliente(edtNombre.getText().toString(), edtApellido.getText().toString(), edtApellido2.getText().toString(), edtDireccion.getText().toString());
                setResult(RESULT_OK);
                finish();

            }
        }

        if (view.getId() == findViewById(R.id.btnCancelar).getId()) {

            finish();

        }
    }
}


