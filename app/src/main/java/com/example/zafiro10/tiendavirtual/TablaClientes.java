package com.example.zafiro10.tiendavirtual;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class TablaClientes extends SQLiteOpenHelper {

    private static final int VERSION_BASEDATOS = 1;

    private static final String NOMBRE_BASEDATOS = "tienda.db";

    private static final String TABLA_CLIENTES = "CREATE TABLE cliente (_id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, apellido1 TEXT, apellido2 TEXT, direccion TEXT)";

    //CREA LA BASE DE DATOS CON ESTE CONSTRUCTOR
    public TablaClientes(Context context) {
        super(context, NOMBRE_BASEDATOS, null, VERSION_BASEDATOS);
    }

    //METODO QUE TE CREA LA TABLA CLIENTE SI NO ESTA CREADA
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(TABLA_CLIENTES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS libro");
        onCreate(db);
    }

    //METODO PARA INSERTAR UN CLIENTE
    public void insertarCliente(String nombre, String apellido1, String apellido2, String direccion) {

        SQLiteDatabase db = getWritableDatabase();

        if(db != null){

            ContentValues valores = new ContentValues();

            valores.put("nombre", nombre);
            valores.put("apellido1", apellido1);
            valores.put("apellido2", apellido2);
            valores.put("direccion", direccion);

            db.insert("cliente", null, valores);
            db.close();
        }
    }

    //METODO PARA MODIFICAR UN CLIENTE
    public void modificarCliente(int id, String nombre, String apellido1, String apellido2, String direccion) {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues valores = new ContentValues();

        valores.put("nombre", nombre);
        valores.put("apellido1", apellido1);
        valores.put("apellido2", apellido2);
        valores.put("direccion", direccion);

        db.update("cliente", valores, "_id=" + id, null);
        db.close();
    }

    //METODO PARA BORRAR UN CLIENTE
    public void borrarCliente(int id) {

        SQLiteDatabase db = getWritableDatabase();

        db.delete("cliente", "_id="+id, null);
        db.close();
    }

    //METODO PARA BORRAR UN CLIENTE
    public void borrarTodosCliente() {

        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM cliente");
        db.close();
    }

    //METODO PARA RECUPERAR UN CONJUNTO DE CLIENTES
    public ArrayList<Cliente> recuperarClientes() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Cliente> lista_clientes = new ArrayList<Cliente>();

        String[] valores_recuperar = {"nombre", "apellido1", "apellido2", "direccion"};
        Cursor c = db.query("cliente", valores_recuperar, null, null, null, null, null, null);
        Cliente clienteRecuperar;

        if (c.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {

                clienteRecuperar = new Cliente(c.getString(0), c.getString(1), c.getString(2), c.getString(3));
                lista_clientes.add(clienteRecuperar);
            } while (c.moveToNext());

            //return c;
        }
        db.close();
        c.close();
        return lista_clientes;
    }

    public ArrayList<Cliente> recuperarUnCliente(int id) {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Cliente> lista_clientes = new ArrayList<Cliente>();

        String[] valores_recuperar = {"_id", "nombre", "apellido1", "apellido2", "direccion"};
        Cursor c = db.query("cliente", valores_recuperar, "_id=" + id, null, null, null, null, null);
        Cliente clienteRecuperar;

        if (c.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {

                clienteRecuperar = new Cliente(c.getInt(0),c.getString(1), c.getString(2), c.getString(3), c.getString(4));
                lista_clientes.add(clienteRecuperar);
            } while (c.moveToNext());

            //return c;
        }
        db.close();
        c.close();
        return lista_clientes;
    }

    int numeroClientes() {
        Cursor c;
        //controlador
        SQLiteDatabase db = getReadableDatabase();
        //definimos el cursor

        int numeroClientes=0;
        //definimos la sentencia sql en una cadena

        String[] valores_recuperar = {"_id"};
        //Ejecutamos la cadena
        c = db.query("cliente", valores_recuperar,null, null, null, null, null, null);

        if(c!=null) {
            numeroClientes = c.getCount();
        }

        //cerramos el cursor y el SQLiteDatabase
        c.close();
        db.close();
        //devolvemos el numero de libros
        return numeroClientes;


    }//fin numero_de_libros

    ArrayList<Cliente> todosClientes()
    {
        Cursor c;
        //Array donde se devuelven todos los libros
        ArrayList<Cliente> arrayCliente = new ArrayList<Cliente>();

        //controlador
        SQLiteDatabase db = getReadableDatabase();

        //definimos la sentencia sql en una cadena
        String[] valores_recuperar = {"_id", "nombre", "apellido1", "apellido2", "direccion"};

        //Ejecutamos la cadena
        c = db.query("cliente", valores_recuperar,null, null, null, null, null, null);
        //c = db.rawQuery(" SELECT * FROM libros ", null);

        //Nos aseguramos de que existe al menos un registro
        if (c.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                arrayCliente.add(new Cliente(c.getInt(0),c.getString(1),c.getString(2), c.getString(3), c.getString(4)));

            } while(c.moveToNext());
        }
        //cerramos el cursor y el SQLiteDatabase
        c.close();
        db.close();
        //devolvemos el cursor
        return arrayCliente;

    }//fin numero_de_libros




}
