package com.example.lucas.fitnet20;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by lucas on 14/7/2017.
 */

public class Item_UsuarioAdapter extends BaseAdapter {
    private ArrayList<Item_Usuario> arrayListItem;
    private Context context;
    private LayoutInflater layoutInflater;

    public Item_UsuarioAdapter(ArrayList<Item_Usuario> arrayListItem, Context context) {
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
        view = layoutInflater.inflate(R.layout.item_usuario,viewGroup,false);
        TextView nombre = (TextView) view.findViewById(R.id.userName);
        TextView Nick = (TextView) view.findViewById(R.id.userNick);
        ImageView imagen = (ImageView) view.findViewById(R.id.userImage);
        Nick.setText(arrayListItem.get(position).getNick());
        nombre.setText(arrayListItem.get(position).getNombre());
        Bitmap bmp = BitmapFactory.decodeByteArray(arrayListItem.get(position).getImagen(), 0, arrayListItem.get(position).getImagen().length);
        imagen.setImageBitmap(bmp);
        return view;
    }
}
