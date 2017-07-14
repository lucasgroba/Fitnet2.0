package com.example.lucas.fitnet20;

import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Base64;
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

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static android.app.Activity.RESULT_OK;

/**
 * Created by santiago on 13/07/17.
 */

public class FragmentIngresarCliente extends Fragment {
    Item_Cliente actividad;
    Button setimagen;
    Button crearbutton;
    EditText nombre;
    EditText cedula;
    EditText fnac;
    EditText correo;
    EditText celular;
    EditText mutualista;
    EditText sexo;
    Bitmap imagen;
    String Key;
    private static int LOAD_IMAGE_RESULTS = 1;
    private static final int PICK_IMAGE=100;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.fragment_ingresar_cliente,container,false);
        setimagen = (Button) view.findViewById(R.id.imagenbutton);
        crearbutton = (Button) view.findViewById(R.id.crearbutton);
        Key = getArguments().getString("dato").toString();
        nombre=(EditText)view.findViewById(R.id.nom_cli);
        cedula = (EditText) view.findViewById(R.id.ced_cli);
        celular = (EditText) view.findViewById(R.id.cel_cli);
        fnac = (EditText) view.findViewById(R.id.fnac);
        correo = (EditText) view.findViewById(R.id.correo_cli);
        mutualista = (EditText) view.findViewById(R.id.mutu_cli);
        sexo = (EditText) view.findViewById(R.id.sexo_cli);

        setimagen.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, LOAD_IMAGE_RESULTS);
            }
        });

        crearbutton.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View v) {
                                               //if(nombre.getText().toString()!=""&& cedula.getText().toString()!="" && celular.getText().toString()!=""&& correo.getText().toString()!="" && fnac.getText().toString()!="" && mutualista.getText().toString()!="" && sexo.getText().toString()!=""){
                                                   actividad = new Item_Cliente();
                                                   actividad.setNombre(nombre.getText().toString());
                                                   actividad.setCedula(cedula.getText().toString());
                                                   actividad.setFecha_nac(fnac.getText().toString());
                                                   actividad.setCorreo(correo.getText().toString());
                                                   actividad.setCelular(celular.getText().toString());
                                                   actividad.setMutualista(mutualista.getText().toString());
                                                   actividad.setSexo(sexo.getText().toString());
                                                   new FragmentIngresarCliente.IngresarClienteTask(Key, actividad).execute();
                                               //}
                                               //else {
                                               //    Toast.makeText(getActivity(), "Todos los campos son requeridos.", Toast.LENGTH_SHORT).show();
                                               //}

                                           }
                                       }
        );


        return view;
    }

    public void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode == RESULT_OK && requestCode == PICK_IMAGE){
            Uri pickedImage = data.getData();
            String[] filePath = { MediaStore.Images.Media.DATA };
            Cursor cursor = getActivity().getContentResolver().query(pickedImage, filePath, null, null, null);
            cursor.moveToFirst();
            String imagePath = cursor.getString(cursor.getColumnIndex(filePath[0]));

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            imagen = BitmapFactory.decodeFile(imagePath, options);
            cursor.close();
        }
    }

    public class IngresarClienteTask extends AsyncTask<String, Void, Void> {

        private final String mKey;
        Item_Cliente actividad;

        IngresarClienteTask(String dato, Item_Cliente act) {
            mKey = dato;
            actividad = act;
        }

        @Override
        protected Void doInBackground(String... params) {
            Log.i("Inicio de consulta","doInBackground");
            HttpClient httpClient = new DefaultHttpClient();

            HttpPost post = new HttpPost("http://fitnet.com.uy/api/clientes/crear");


            try{
                Log.i("Pre execute","doInBackground");
                JSONObject principal = new JSONObject();
                JSONObject interno = new JSONObject();
                principal.put("key",mKey);
                interno.put("nombre",actividad.getNombre());
                interno.put("cedula",actividad.getCedula());
                interno.put("fnac",actividad.getFecha_nac());
                interno.put("correo",actividad.getCorreo());
                interno.put("celular",actividad.getCelular());
                interno.put("mutualista",actividad.getMutualista());
                interno.put("correo",actividad.getSexo());
                /*
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                imagen.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                byte[] byteArray = byteArrayOutputStream .toByteArray();
                String encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);

                interno.put("imagen",encoded);
                */
                principal.put("cliente",interno);
                String json = principal.toString();
                StringEntity jsonEnt = new StringEntity(json);
                post.setEntity(jsonEnt);
//                post.setHeader("Accept", "application/json");
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
            Toast.makeText(getActivity(),"El Cliente se ha ingresado correctamente.",Toast.LENGTH_SHORT).show();


        }
    }
}
