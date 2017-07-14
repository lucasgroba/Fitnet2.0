package com.example.lucas.fitnet20;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by lucas on 13/7/2017.
 */

public class Item_ConceptoAdapter extends BaseAdapter {
    private ArrayList<Item_Concepto> arrayListItem;
    private Context context;
    private LayoutInflater layoutInflater;

    public Item_ConceptoAdapter(ArrayList<Item_Concepto> arrayListItem, Context context) {
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
        return arrayListItem.get(position).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if(view!=null){
            //layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //view = layoutInflater.inflate(R.layout.item,viewGroup,false);
        }
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(R.layout.item_concepto,viewGroup,false);
        TextView titulo = (TextView) view.findViewById(R.id.NombreConc);
        TextView descripcion = (TextView) view.findViewById(R.id.DescripcionConc);
        titulo.setText(arrayListItem.get(position).getNombre());
        descripcion.setText(arrayListItem.get(position).getDescripcion());
        return view;
    }
}
