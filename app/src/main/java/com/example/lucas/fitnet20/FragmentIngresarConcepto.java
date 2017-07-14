package com.example.lucas.fitnet20;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by lucas on 13/7/2017.
 */

public class FragmentIngresarConcepto extends Fragment{
    Item_Concepto concepto;
    Button BtnIngresar;
    EditText nombre;
    EditText descripcion;
    String Key;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.fragment_ingresar_concepto,container,false);
        Key = getArguments().getString("dato").toString();
        nombre=(EditText)view.findViewById(R.id.NombreConc);
        descripcion =(EditText) view.findViewById(R.id.DescripcionConc);

        BtnIngresar = (Button) view.findViewById(R.id.BotonIngConc);
        BtnIngresar.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View v) {
                                               if(true){
                                                   concepto = new Item_Concepto();
                                                   concepto.setNombre(nombre.getText().toString());
                                                   concepto.setDescripcion(descripcion.getText().toString());
                                                   new IngresarActividadTask(Key, concepto).execute();
                                               }
                                               else {
                                                   Toast.makeText(getActivity(), "Todos los campos son requeridos.", Toast.LENGTH_SHORT).show();
                                               }

                                           }
                                       }
        );


        return view;
    }

    public class IngresarActividadTask extends AsyncTask<String, Void, Void> {

        private final String mKey;
        Item_Concepto concepto;

        IngresarActividadTask(String dato, Item_Concepto act) {
            mKey = dato;
            concepto = act;
        }

        @Override
        protected Void doInBackground(String... params) {
            Log.i("Inicio de consulta","doInBackground");
            HttpClient httpClient = new DefaultHttpClient();

            HttpPost post = new HttpPost("http://fitnet.com.uy/api/conceptos/crear");


            try{
                Log.i("Pre execute","doInBackground");
                JSONObject principal = new JSONObject();
                JSONObject interno = new JSONObject();
                interno.put("descripcion",concepto.getDescripcion());
                interno.put("nombre",concepto.getNombre());
                principal.put("concepto",interno);
                principal.put("key",mKey);
                String json = principal.toString();
                StringEntity jsonEnt = new StringEntity(json);
                post.setEntity(jsonEnt);
                post.setHeader("Content-type", "application/json");


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
            Toast.makeText(getActivity(),"El Concepto se ha ingresado correctamente.",Toast.LENGTH_SHORT).show();


        }
    }
}
