package com.example.lucas.fitnet20;


import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by santiago on 13/07/17.
 */

public class fragmentListarCliente extends Fragment{
    private View view;
    private ListView list;
    private ArrayList<Item_Cliente> arrayItem;
    private Context mycontext;

    public fragmentListarCliente() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_listar_clientes,container,false);
        final String dat = getArguments().getString("dato");
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.nuevocli);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentIngresarCliente fragment= new FragmentIngresarCliente();
                FragmentManager mf = getFragmentManager();
                android.app.FragmentTransaction t= mf.beginTransaction();
                t.add(R.id.content_main,fragment);
                //ft.replace(R.id.content_main,fragment);
                //ft.commit();
                t.commit();
                Bundle data = new Bundle();
                data.putString("dato", dat);
                fragment.setArguments(data);
            }
        });
        list=(ListView)view.findViewById(R.id.listacli);

        new fragmentListarCliente.ClienteTask(dat).execute();


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

    public class ClienteTask extends AsyncTask<String, Void, Void> {

        private final String mKey;
        Item_Cliente cliente;
        ArrayList<Item_Cliente> arrayActividad;

        ClienteTask(String dato) {
            mKey = dato;
        }

        @Override
        protected Void doInBackground(String... params) {
            Log.i("Inicio de consulta Act","doInBackground");
            HttpClient httpClient = new DefaultHttpClient();

            HttpGet get = new HttpGet("http://fitnet.com.uy/api/clientes/listar/"+mKey);
            get.setHeader("Content-Type","application/json");
            arrayItem =new ArrayList();
            try{
                Log.i("Pre execute","doInBackground");
                HttpResponse resp = httpClient.execute(get);
                Log.i("execute","doInBackground");
                String respString = EntityUtils.toString(resp.getEntity());
                JSONArray Json = new JSONArray(respString);
                for(int cont = 0; cont<Json.length(); cont++){
                    cliente=new Item_Cliente();
                    JSONObject Act = (JSONObject) Json.get(cont);
                    cliente.setNombre( Act.getString("nombre"));
                    cliente.setCedula(Act.getString("cedula"));
                    cliente.setFecha_nac(Act.getString("fnac"));
                    String img=Act.getString("imagen");
                    URL url = new URL("http://fitnet.com.uy"+img);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setDoInput(true);
                    connection.connect();
                    InputStream input = connection.getInputStream();
                    Bitmap bmp = BitmapFactory.decodeStream(input);
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    byte[] imagenarray = stream.toByteArray();
                    Bitmap imagen = BitmapFactory.decodeByteArray(imagenarray, 0, imagenarray.length);
                    cliente.setImagen(imagen);
                    cliente.setCelular(Act.getString("celular"));
                    cliente.setCorreo(Act.getString("correo"));
                    cliente.setMutualista(Act.getString("mutualista"));
                    cliente.setSexo(Act.getString("sexo"));
                    arrayItem.add(cliente);
                    cliente = null;
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
            Log.i("inicio","postExecute");
            Item_ClienteAdapter adapter=new Item_ClienteAdapter(arrayItem,getActivity());

            list.setAdapter(adapter);


        }
    }
}
