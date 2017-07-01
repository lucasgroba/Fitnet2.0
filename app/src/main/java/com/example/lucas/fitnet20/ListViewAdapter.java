package com.example.lucas.fitnet20;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by santiago on 04/06/17.
 */

public class ListViewAdapter extends BaseAdapter{
    private ArrayList<Item> arrayListItem;
    private Context context;
    private LayoutInflater layoutInflater;

    public ListViewAdapter(ArrayList<Item> arrayListItem, Context context) {
        this.arrayListItem = arrayListItem;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayListItem.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayListItem.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view!=null){
            //layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //view = layoutInflater.inflate(R.layout.item,viewGroup,false);
        }
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(R.layout.item,viewGroup,false);
        ImageView image = (ImageView) view.findViewById(R.id.imagen);
        TextView titulo = (TextView) view.findViewById(R.id.titulo);
        TextView descripcion = (TextView) view.findViewById(R.id.descripcion);
        image.setImageResource(arrayListItem.get(i).getImagen());
        titulo.setText(arrayListItem.get(i).getTitulo());
        descripcion.setText(arrayListItem.get(i).getDescripcion());
        return view;
    }
}
