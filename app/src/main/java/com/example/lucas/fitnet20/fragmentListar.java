package com.example.lucas.fitnet20;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.math.BigDecimal;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by santiago on 04/06/17.
 */

public class fragmentListar extends Fragment {
    private View view;
    private ListView list;
    private ArrayList<Item_Actividad> arrayItem;
    private Context mycontext;

    public fragmentListar() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_listar,container,false);
        String data = getArguments().getString("dato");
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentIngresarActividad fragment= new FragmentIngresarActividad();
                FragmentManager mf = getFragmentManager();
                android.app.FragmentTransaction t= mf.beginTransaction();
                t.add(R.id.content_main,fragment);
                //ft.replace(R.id.content_main,fragment);
                //ft.commit();
                t.commit();
                Bundle data = new Bundle();
                data.putString("dato", String.valueOf(data));
                fragment.setArguments(data);
            }
        });


        new ActividadTask(data).execute();


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub
                Log.v("TAG", "CLICKED row number: " + arg2);
                Item_Actividad seleccionado = (Item_Actividad) list.getAdapter().getItem(arg2);
                fragmentMostrar fragment= new fragmentMostrar();
                FragmentManager mf = getFragmentManager();
                android.app.FragmentTransaction t= mf.beginTransaction();
                t.add(R.id.content_main,fragment);
                //ft.replace(R.id.content_main,fragment);
                //ft.commit();
                t.commit();
                Bundle data = new Bundle();
                data.putSerializable("item", seleccionado);
                fragment.setArguments(data);

            }

        });
        return view;

    }

    public class ActividadTask extends AsyncTask<String, Void, Void> {

        private final String mKey;
        Item_Actividad actividad;
        ArrayList<Item_Actividad> arrayActividad;

        ActividadTask(String dato) {
            mKey = dato;
        }

        @Override
        protected Void doInBackground(String... params) {
            Log.i("Inicio de consulta","doInBackground");
            HttpClient httpClient = new DefaultHttpClient();

            HttpGet get = new HttpGet("http://fitnet.com.uy/api/actividades/listar/"+mKey);
            get.setHeader("Content-Type","application/json");
            actividad=new Item_Actividad();
            arrayItem =new ArrayList();
            try{
                Log.i("Pre execute","doInBackground");
                HttpResponse resp = httpClient.execute(get);
                Log.i("execute","doInBackground");
                String respString = EntityUtils.toString(resp.getEntity());
                JSONArray Json = new JSONArray(respString);
                for(int cont = 0; cont<Json.length(); cont++){
                    JSONObject Act = (JSONObject) Json.get(cont);
                    actividad.setActividad( Act.getString("nombre"));
                    actividad.setId(Act.getInt("id"));
                    actividad.setPerdido(Act.getInt("periodo"));
                    actividad.setDias(Act.getInt("dias"));
                    actividad.setPrecio(BigDecimal.valueOf(Act.getDouble("precio")).floatValue());
                    arrayItem.add(actividad);
                }


//                URL url = new URL("http://fitnet.com.uy"+imagen);
//                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//                connection.setDoInput(true);
//                connection.connect();
//                InputStream input = connection.getInputStream();
//                Bitmap bmp = BitmapFactory.decodeStream(input);
//                ByteArrayOutputStream stream = new ByteArrayOutputStream();
//                bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
//                imagenarray = stream.toByteArray();
//                //(Byte[])

            }
            catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            //ArrayAdapter<String> lva = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,array);
            Item_ActividadAdapter adapter=new Item_ActividadAdapter(arrayItem,getActivity());
            list=(ListView)view.findViewById(R.id.listaMenu);
            list.setAdapter(adapter);


        }
    }
}
