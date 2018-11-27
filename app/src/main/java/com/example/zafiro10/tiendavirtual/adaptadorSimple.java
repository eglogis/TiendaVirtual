package com.example.zafiro10.tiendavirtual;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class adaptadorSimple extends BaseAdapter {

    protected Activity activity;
    protected ArrayList<Cliente> items;

    public adaptadorSimple (Activity activity, ArrayList<Cliente> items) {
        this.activity = activity;
        this.items = items;
    }


    @Override
    public int getCount() {
        return items.size();
    }

    public void clear() {
        items.clear();
    }

    public void addAll(ArrayList<Cliente> category) {
        for (int i = 0; i < category.size(); i++) {
            items.add(category.get(i));
        }
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View v = view;

        if (view == null) {
            LayoutInflater inf = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inf.inflate(R.layout.listapersonalizada, null);
        }

        Cliente dir = items.get(i);

        TextView txvNombre = (TextView) v.findViewById(R.id.txvNombre);
        txvNombre.setText(dir.getNombreCliente());

        TextView txvApellido1 = (TextView) v.findViewById(R.id.txvApellido1);
        txvApellido1.setText(dir.getApellido1Cliente());

        TextView txvApellido2 = (TextView) v.findViewById(R.id.txvApellido2);
        txvApellido2.setText(dir.getApellido2Cliente());

        TextView txvdireccion = (TextView) v.findViewById(R.id.txvdireccion);
        txvdireccion.setText(dir.getDireccionCliente());
        txvdireccion.setVisibility(view.INVISIBLE);

        TextView txvid = (TextView) v.findViewById(R.id.txvid);
        txvid.setText(Integer.toString(dir.getCodCliente()));
        txvid.setVisibility(view.INVISIBLE);

        TextView txvdirec = (TextView) v.findViewById(R.id.txvdire);
        txvdirec.setVisibility(view.INVISIBLE);

        return v;
    }
}
