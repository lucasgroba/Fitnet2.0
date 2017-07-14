package com.example.lucas.fitnet20;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by lucas on 13/7/2017.
 */

public class fragmentMostrarConcepto extends Fragment {

    Item_Concepto seleccionado;
    TextView nombre;
    TextView descripcion;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.fragment_mostrar_concepto,container,false);

        nombre = (TextView)view.findViewById(R.id.NombreConc);
        descripcion = (TextView)view.findViewById(R.id.DescripcionConc);

        seleccionado =(Item_Concepto) getArguments().getSerializable("item");
        nombre.setText(seleccionado.getNombre().toString());
        descripcion.setText(seleccionado.getDescripcion());


        return view;
    }
}
