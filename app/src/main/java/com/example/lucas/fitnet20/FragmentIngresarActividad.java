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
import android.widget.ListView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucas on 6/7/2017.
 */

public class FragmentIngresarActividad extends Fragment {
    Item_Actividad actividad;
    Button BtnIngresar;
    EditText nombre;
    EditText precio;
    EditText dias;
    EditText periodo;
    String Key;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.fragment_ingresar_actividad,container,false);
        Key = getArguments().getString("dato");
        nombre=(EditText)view.findViewById(R.id.nombre);
        precio = (EditText) view.findViewById(R.id.precio);
        dias = (EditText) view.findViewById(R.id.dias);
        periodo = (EditText) view.findViewById(R.id.periodo);
        BtnIngresar = (Button) view.findViewById(R.id.buttonIngresar);
        BtnIngresar.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   actividad.setActividad(nombre.getText().toString());
                   actividad.setPrecio(Float.valueOf(precio.getText().toString()));
                   actividad.setDias(Integer.valueOf(dias.getText().toString()));
                   actividad.setPerdido(Integer.valueOf(periodo.getText().toString()));
                   new IngresarActividadTask(Key,actividad).execute();

               }
           }
        );


        return view;
    }

    public class IngresarActividadTask extends AsyncTask<String, Void, Void> {

        private final String mKey;
        Item_Actividad actividad;

        IngresarActividadTask(String dato, Item_Actividad act) {
            mKey = dato;
            actividad = act;
        }

        @Override
        protected Void doInBackground(String... params) {
            Log.i("Inicio de consulta","doInBackground");
            HttpClient httpClient = new DefaultHttpClient();

            HttpPost post = new HttpPost("http://fitnet.com.uy/api/actividades/crear");


            try{
                Log.i("Pre execute","doInBackground");
                JSONObject principal = new JSONObject();
                JSONObject interno = new JSONObject();
                principal.put("key",mKey);
                interno.put("nombre",actividad.getActividad());
                interno.put("precio",actividad.getPrecio());
                interno.put("dias",actividad.getDias());
                interno.put("periodo",actividad.getPerdido());
                principal.put("actividad",interno);
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
            Toast.makeText(getActivity(),"La actividad se ha ingresado correctamente.",Toast.LENGTH_SHORT);


        }
    }
}
