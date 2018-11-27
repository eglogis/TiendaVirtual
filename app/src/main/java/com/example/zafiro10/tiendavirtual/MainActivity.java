package com.example.zafiro10.tiendavirtual;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.StaticLayout;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {

    Toolbar MinuevaToolbar;

    static TablaClientes ControladorClientes;

    ListView listadoCliente;
    adaptadorListViewClientes adaptador;
    adaptadorSimple vistaSimple;
    ArrayList<Cliente> arrayClientes = new ArrayList<Cliente>();
    Button btnOk;
    EditText edtIdenti;
    FloatingActionButton botonFlotante;

    static int CodClienteModificar;
    int contador = 0;
    int posicionCliente;
    private View view2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MinuevaToolbar = findViewById(R.id.toolbar);
        this.MinuevaToolbar.setTitle("Clientes");
        setSupportActionBar(MinuevaToolbar);

        int numeroClientes;

        listadoCliente = (ListView) findViewById(R.id.ListView);
        //listadoCliente.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        btnOk = (Button) findViewById(R.id.btnOk);
        btnOk.setOnClickListener(this);
        botonFlotante = (FloatingActionButton) findViewById(R.id.btnMostrartodos);
        botonFlotante.setOnClickListener(this);

        edtIdenti = (EditText)findViewById(R.id.edtIdenti);

        ControladorClientes = new TablaClientes(getApplicationContext());

        if ((numeroClientes = ControladorClientes.numeroClientes())== 0) {

                ControladorClientes.insertarCliente("Samuel","Bautista","Sanchez","Manantiales");
                ControladorClientes.insertarCliente("Manolo","Garcia","Perez","Zarzuela Baja");
                ControladorClientes.insertarCliente("Francisco","Bautista","Lucena","Manantiales");
                ControladorClientes.insertarCliente("Elena","Sanchez","Torrente","Mergal");
                ControladorClientes.insertarCliente("Elena","Bautista","Sanchez","Manantiales");
                ControladorClientes.insertarCliente("Miguel","Sanchez","Luque","Piruleta");
                ControladorClientes.insertarCliente("Alvaro","Garcia","Sanchez","Zarzuela Baja");
                ControladorClientes.insertarCliente("Alvaro","Garcia","Sanchez","Zarzuela Baja");
                ControladorClientes.insertarCliente("Diego","Tinedo","Barbosa","Piruleta");
                ControladorClientes.insertarCliente("Diego","Tinedo","Barbosa","Piruleta");

                arrayClientes=ControladorClientes.todosClientes();
                adaptador = new adaptadorListViewClientes(this, arrayClientes);
                listadoCliente.setAdapter(adaptador);
                listadoCliente.setOnItemClickListener(this);

        }//fin if
        else{

            arrayClientes=ControladorClientes.todosClientes();
            adaptador = new adaptadorListViewClientes(this, arrayClientes);
            listadoCliente.setAdapter(adaptador);

            listadoCliente.setOnItemClickListener(this);


        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        CodClienteModificar = arrayClientes.get(i).getCodCliente();

        listadoCliente.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final int posicion=i;

                AlertDialog.Builder dialogo1 = new AlertDialog.Builder(MainActivity.this);
                dialogo1.setTitle("Importante");
                dialogo1.setMessage("¿ Elimina este cliente ?");
                dialogo1.setCancelable(false);
                dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialogo1, int id) {

                        ControladorClientes.borrarCliente(arrayClientes.get(posicion).getCodCliente());
                        //ControladorClientes.borrarTodosCliente();
                        arrayClientes.remove(posicion);
                        adaptador.notifyDataSetChanged();

                    }
                });
                dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {



                    }
                });


                dialogo1.show();

                return false;
            }
        });
    }

    @Override
    public void onClick(View view) {

        if(view.getId() == findViewById(R.id.btnOk).getId()) {

            ArrayList<Cliente> filtradoCliente = new ArrayList<Cliente>();

            String identificador = edtIdenti.getText().toString();

            if(edtIdenti.getText().toString().trim().length() == 0 ){

                adaptador = new adaptadorListViewClientes(this, arrayClientes);
                listadoCliente.setAdapter(adaptador);
                listadoCliente.setOnItemClickListener(this);

            }else{

                filtradoCliente = ControladorClientes.recuperarUnCliente(Integer.parseInt(identificador));

                adaptadorListViewClientes adaptador2 = new adaptadorListViewClientes(this, filtradoCliente);
                listadoCliente.setAdapter(adaptador2);
                listadoCliente.setOnItemClickListener(this);
            }
        }

        if(view.getId() == findViewById(R.id.btnMostrartodos).getId()) {

            arrayClientes=ControladorClientes.todosClientes();
            adaptador = new adaptadorListViewClientes(this, arrayClientes);
            listadoCliente.setAdapter(adaptador);
        }

    }

    /*public void AlternarColorEnListas(View view){

        if(contador==1){
            this.view2=view;
            view.setBackgroundColor(Color.parseColor("#b3837b7b"));
        }

        else{
            view2.setBackgroundColor(Color.parseColor("#B3FFFEFE"));
            view.setBackgroundColor(Color.parseColor("#b3837b7b"));
            this.view2=view;
        }
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id)
        {
            case R.id.Nuevo:

                Intent in2 = new Intent(this, Main3Activity.class);
                startActivityForResult(in2, 1);

                break;
            case R.id.Simple:

                vistaSimple = new adaptadorSimple(this, arrayClientes);
                listadoCliente.setAdapter(vistaSimple);

                break;
            case R.id.Completa:

                adaptador = new adaptadorListViewClientes(this, arrayClientes);
                listadoCliente.setAdapter(adaptador);

                break;
            case R.id.Modificar:

                Intent in = new Intent(this, Main2Activity.class);
                startActivityForResult(in, 2);

                break;
        }//fin switch




        return super.onOptionsItemSelected(item);
    }//fin del método nOptionsItemSelected(MenuItem item)

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        if ((requestCode == 1) && (resultCode == RESULT_OK)){


            arrayClientes=ControladorClientes.todosClientes();
            adaptador = new adaptadorListViewClientes(this, arrayClientes);
            listadoCliente.setAdapter(adaptador);
        }
        if ((requestCode == 2) && (resultCode == RESULT_OK)) {

            arrayClientes=ControladorClientes.todosClientes();
            adaptador = new adaptadorListViewClientes(this, arrayClientes);
            listadoCliente.setAdapter(adaptador);
        }
    }
}




