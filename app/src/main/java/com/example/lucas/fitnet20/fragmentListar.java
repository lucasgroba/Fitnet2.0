package com.example.lucas.fitnet20;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by santiago on 04/06/17.
 */

public class fragmentListar extends Fragment {
    private View view;
    private ListView list;
    private ArrayList<Item> arrayItem;
    private ArrayList<String> array;
    private Context mycontext;

    public fragmentListar() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.fragment_listar, container,false);
        /*
        if(view==null)
        {
            view=inflater.inflate(R.layout.fragment_listar,container,false);
        }
        else
        {
            ViewGroup parent = (ViewGroup) view.getParent();
            parent.removeView(view);
        }*/
        /*
        array=new ArrayList();
        array.add("HOLAAAA");
        array.add("CHAUUU");
        array.add("SAPEEE!!");
        */
        view = inflater.inflate(R.layout.fragment_listar,container,false);
        arrayItem = new ArrayList();
        Item i= new Item("El loco de WTF");
        i.setTitulo("El loco de WTF");
        i.setImagen(R.drawable.wtf);
        arrayItem.add(i);

        Item e= new Item("Cantante del Cuarteto");
        e.setTitulo("Roberto Musso");
        e.setImagen(R.drawable.roberto);
        arrayItem.add(e);

        //ArrayAdapter<String> lva = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,array);
        ListViewAdapter adapter=new ListViewAdapter(arrayItem,getActivity());
        list=(ListView)view.findViewById(R.id.listaMenu);
        list.setAdapter(adapter);
        return view;
        /*
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub
                Log.v("TAG", "CLICKED row number: " + arg2);
            }

        });*/

    }
}
