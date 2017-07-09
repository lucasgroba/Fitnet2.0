package com.example.lucas.fitnet20;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by lucas on 9/7/2017.
 */

public class Item_ArticuloAdapter extends BaseAdapter {
    private ArrayList<Item_Articulo> arrayListItem;
    private Context context;
    private LayoutInflater layoutInflater;

    public Item_ArticuloAdapter(ArrayList<Item_Articulo> arrayListItem, Context context) {
        this.arrayListItem = arrayListItem;
        this.context = context;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view!=null){
            //layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //view = layoutInflater.inflate(R.layout.item,viewGroup,false);
        }
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(R.layout.item_articulos,viewGroup,false);
        TextView nombre = (TextView) view.findViewById(R.id.Articulo);
        TextView precio = (TextView) view.findViewById(R.id.Precio);
        nombre.setText(arrayListItem.get(i).getNombre());
        precio.setText(arrayListItem.get(i).getPrecio().toString());
        return view;
    }
}
