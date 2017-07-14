package com.example.lucas.fitnet20;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
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

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Created by lucas on 13/7/2017.
 */

public class FragmentListarConcepto extends Fragment {
    private View view;
    private ListView list;
    private ArrayList<Item_Concepto> arrayItem;
    private Context mycontext;

    public FragmentListarConcepto() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_listar_concepto,container,false);
        final String dat = getArguments().getString("dato");
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.NuevoConcepto);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentIngresarConcepto fragment= new FragmentIngresarConcepto();
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
        list=(ListView)view.findViewById(R.id.listaMenuConceptos);

        new ConceptoTask(dat).execute();


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub
                Log.v("TAG", "CLICKED row number: " + arg2);
                Item_Concepto seleccionado = (Item_Concepto) list.getAdapter().getItem(arg2);
                fragmentMostrarConcepto fragment= new fragmentMostrarConcepto();
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

    public class ConceptoTask extends AsyncTask<String, Void, Void> {

        private final String mKey;
        Item_Concepto concepto;
        ArrayList<Item_Concepto> arrayConcepto;

        ConceptoTask(String dato) {
            mKey = dato;
        }

        @Override
        protected Void doInBackground(String... params) {
            Log.i("Inicio de consulta Act","doInBackground");
            HttpClient httpClient = new DefaultHttpClient();

            HttpGet get = new HttpGet("http://fitnet.com.uy/api/conceptos/listar/"+mKey);
            get.setHeader("Content-Type","application/json");
            arrayItem =new ArrayList();
            try{
                Log.i("Pre execute","doInBackground");
                HttpResponse resp = httpClient.execute(get);
                Log.i("execute","doInBackground");
                String respString = EntityUtils.toString(resp.getEntity());
                JSONArray Json = new JSONArray(respString);
                for(int cont = 0; cont<Json.length(); cont++){
                    concepto=new Item_Concepto();
                    JSONObject Con = (JSONObject) Json.get(cont);
                    concepto.setNombre( Con.getString("nombre"));
                    concepto.setId(Con.getInt("id"));
                    concepto.setDescripcion(Con.getString("descripcion"));
                    arrayItem.add(concepto);
                    concepto = null;
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
            Item_ConceptoAdapter adapter=new Item_ConceptoAdapter(arrayItem,getActivity());

            list.setAdapter(adapter);


        }
    }
}

