package com.example.lucas.fitnet20;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by lucas on 9/7/2017.
 */

public class fragmentMostrarArticulo extends Fragment {
    Item_Articulo seleccionado;
    TextView nombre;
    TextView precio;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.fragment_mostrar,container,false);

        nombre = (TextView)view.findViewById(R.id.textNombreArt);
        precio = (TextView)view.findViewById(R.id.textPrecioArt);

        seleccionado =(Item_Articulo) getArguments().getSerializable("item");
        nombre.setText(seleccionado.getNombre().toString());
        precio.setText(seleccionado.getPrecio().toString());


        return view;
    }
}
