package com.example.lucas.fitnet20;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by lucas on 14/7/2017.
 */

public class fragmentMostrarUsuario  extends Fragment{
    Item_Usuario seleccionado;
    TextView nombre;
    TextView nick;
    ImageView imagen;
    TextView permisos;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.fragment_mostrar_usuario,container,false);

        nombre = (TextView)view.findViewById(R.id.NombreUser);
        nick = (TextView) view.findViewById(R.id.UserNick);
        imagen = (ImageView) view.findViewById(R.id.userImagen);
        permisos = (TextView) view.findViewById(R.id.UserPermisos);

        seleccionado =(Item_Usuario) getArguments().getSerializable("item");
        nombre.setText(seleccionado.getNombre().toString());
        nick.setText(seleccionado.getNick().toString());
        Bitmap bmp = BitmapFactory.decodeByteArray(seleccionado.getImagen(), 0, seleccionado.getImagen().length);
        imagen.setImageBitmap(bmp);

//        periodo.setText(String.valueOf(seleccionado.getPeriodo()));


        return view;
    }
}
