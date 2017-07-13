package com.example.lucas.fitnet20;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucas on 9/7/2017.
 */

public class FragmentIngresarArticulo extends Fragment{
    Item_Articulo articulo;
    Button BtnIngresar;
    EditText nombre;
    EditText precio;
    String Key;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.fragment_ingresar_articulo,container,false);
        Key = getArguments().getString("dato");
        nombre=(EditText)view.findViewById(R.id.editArtNombre);
        precio = (EditText) view.findViewById(R.id.editArtPrecio);
        BtnIngresar = (Button) view.findViewById(R.id.BtnArtIngresar);
        BtnIngresar.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View v) {
                                               if(nombre.getText().toString()!="" && precio.getText().toString()!="") {
                                                   articulo.setNombre(nombre.getText().toString());
                                                   articulo.setPrecio(Float.valueOf(precio.getText().toString()));
                                                   new IngresarArticuloTask(Key, articulo).execute();
                                               }
                                               else{
                                                   Toast.makeText(getActivity(), "Todos los campos son requeridos.", Toast.LENGTH_SHORT).show();
                                               }
                                           }
                                       }
        );


        return view;
    }

    public class IngresarArticuloTask extends AsyncTask<String, Void, Void> {

        private final String mKey;
        Item_Articulo articulo;

        IngresarArticuloTask(String dato, Item_Articulo art) {
            mKey = dato;
            articulo = art;
        }

        @Override
        protected Void doInBackground(String... params) {
            Log.i("Inicio de consulta","doInBackground");
            HttpClient httpClient = new DefaultHttpClient();

            HttpPost post = new HttpPost("http://fitnet.com.uy/api/articulos/crear");


            try{
                Log.i("Pre execute","doInBackground");
                JSONObject principal = new JSONObject();
                JSONObject interno = new JSONObject();
                principal.put("key",mKey);
                interno.put("nombre",articulo.getNombre());
                interno.put("precio",articulo.getPrecio());
                principal.put("articulo",interno);
                List<NameValuePair> nvp = new ArrayList<NameValuePair>(2);
                nvp.add(new BasicNameValuePair("article", principal.toString()));
                //post.setHeader("Content-type", "application/json");
                post.setEntity(new UrlEncodedFormEntity(nvp));


                HttpResponse resp = httpClient.execute(post);
                Log.w("respuesta del server", resp.getStatusLine().toString());

            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (JSONException e1) {
                e1.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Toast.makeText(getActivity(),"El Articulo se ha ingresado correctamente.",Toast.LENGTH_SHORT);


        }
    }
}
