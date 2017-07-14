package com.example.lucas.fitnet20;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by santiago on 13/07/17.
 */

public class Item_ClienteAdapter extends BaseAdapter{
    private ArrayList<Item_Cliente> arrayListItem;
    private Context context;
    private LayoutInflater layoutInflater;

    public Item_ClienteAdapter(ArrayList<Item_Cliente> arrayListItem, Context context) {
        this.arrayListItem = arrayListItem;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayListItem.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayListItem.get(position);

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view!=null){
            //layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //view = layoutInflater.inflate(R.layout.item,viewGroup,false);
        }
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(R.layout.item_cliente,viewGroup,false);
        TextView nombre = (TextView) view.findViewById(R.id.NomCli);
        ImageView imagen = (ImageView) view.findViewById(R.id.ImagenCli);
        TextView cedula = (TextView) view.findViewById(R.id.Cedula);
        TextView correo = (TextView) view.findViewById(R.id.Correo);
        TextView celular = (TextView) view.findViewById(R.id.Celular);
        TextView mutualista = (TextView) view.findViewById(R.id.Mutualista);
        TextView fecha_nac = (TextView) view.findViewById(R.id.FechaNac);
        TextView sexo = (TextView) view.findViewById(R.id.Sexo);
        nombre.setText(arrayListItem.get(i).getNombre());
        cedula.setText(arrayListItem.get(i).getCedula());
        correo.setText(arrayListItem.get(i).getCorreo());
        celular.setText(arrayListItem.get(i).getCelular());
        fecha_nac.setText(arrayListItem.get(i).getFecha_nac());
        mutualista.setText(arrayListItem.get(i).getMutualista());
        sexo.setText(arrayListItem.get(i).getSexo());
        imagen.setImageBitmap(arrayListItem.get(i).getImagen());
        return view;
    }
}
