package com.example.lucas.fitnet20;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by santiago on 04/06/17.
 */

public class fragmentMostrar extends Fragment {
    Item_Actividad seleccionado;
    TextView nombre;
    TextView precio;
    TextView dias;
    TextView periodo;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.fragment_mostrar,container,false);

        nombre = (TextView)view.findViewById(R.id.textNombre);
        precio = (TextView) view.findViewById(R.id.textPrecio);
        dias = (TextView) view.findViewById(R.id.textDias);
        periodo = (TextView) view.findViewById(R.id.textPeriodo);

        seleccionado =(Item_Actividad) getArguments().getSerializable("item");
        nombre.setText(seleccionado.getActividad().toString());
        precio.setText(seleccionado.getPrecio().toString());
        String dia = String.valueOf(seleccionado.getDias());
        dias.setText(dia);
        periodo.setText(String.valueOf(seleccionado.getPeriodo()));


        return view;
    }
}
