package com.jjv.examenjson_2016.UI;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jjv.examenjson_2016.Objects.Actor;
import com.jjv.examenjson_2016.Objects.Filmografia;
import com.jjv.examenjson_2016.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.security.PublicKey;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class InfoActorActivity extends AppCompatActivity {
    public final static String API_KEY = "?api_key=67c90d41-14bc-4494-b86f-00bae7742268";
    public final static String API_URL = "http://imdb.wemakesites.net/api/";
    public final static String FILMOGRAFIA= "filmografia";
    String codigo;
    TextView lbl_nombre;
    TextView lbl_descripcion;
    Button btn_filmografia;
    ImageView imagen;
    private Actor actor;
    private String state;
    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infoactor);

        lbl_descripcion = (TextView) findViewById(R.id.lbl_descripcion);
        lbl_nombre = (TextView) findViewById(R.id.lbl_nombre);
        imagen = (ImageView) findViewById(R.id.imagen);
        btn_filmografia = (Button) findViewById(R.id.btn_filmografia);
        btn_filmografia.setVisibility(View.INVISIBLE);
        Intent i = getIntent();
        codigo = i.getStringExtra(MainActivity.CODIGO);

        consulta();
    }

    private void consulta() {
        String ibmdURL = API_URL + codigo + API_KEY;
        Log.i(TAG, ibmdURL);
        if (isNetworkAvailable()) {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(ibmdURL).build();
            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    try {
                        //Response response = call.execute();

                        String result = response.body().string();
                        Log.v(TAG, result);

                        if (response.isSuccessful()) {
                            actor = getActor(result);
                            Log.d(TAG,"Estado: "+state);
                            if (state.equals("success")) {
                                mostrarDatos();
                            } else {
                                mostrarFallo();
                            }


                        } else {
                            alerUserAboutError();
                        }

                    } catch (IOException | JSONException ex) {
                        Log.e(TAG, "Excepcion: ", ex);

                    }
                }
            });

        } else {
            Toast.makeText(this, "Error de conexion", Toast.LENGTH_SHORT).show();
        }
    }

    private void alerUserAboutError() {
        AlertDialogFragment dialog = new AlertDialogFragment();
        dialog.show(getFragmentManager(), "Error dialog:");
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()) {
            isAvailable = true;
        }
        return isAvailable;
    }

    private Actor getActor(String jsonData) throws JSONException {
        JSONObject result = new JSONObject(jsonData);
        Actor actor = new Actor();
        state = result.getString("status");

        if (state.equals("success")) {
            JSONObject actordata = result.getJSONObject("data");
            actor.setNombre(actordata.getString("title"));
            actor.setDescripcion(actordata.getString("description"));
            actor.setFoto(actordata.getString("image"));
            actor.setFilmografia(getFilmografia(actordata));
        }

        Log.d(TAG,"Stado: "+state);
        return actor;
    }

    private ArrayList<Filmografia> getFilmografia(JSONObject actordata) throws JSONException {
        JSONArray data = actordata.getJSONArray("filmography");
        ArrayList <Filmografia>  filmografia = new ArrayList <Filmografia> ();
        for (int i = 0; i < data.length(); i++) {
            JSONObject filmdata = data.getJSONObject(i);
            //filmografia[i] = new Filmografia();
            //filmografia[i].setNombre(filmdata.getString("title"));

            String fecha = filmdata.getString("year").replaceAll("&nbsp;"," ");
            Log.d(TAG,"Fecha:"+fecha);
            filmografia.add(new Filmografia(filmdata.getString("title"),fecha));
            //filmografia[i].setFecha(fecha);

        }
        return filmografia;
    }

    public void mostrarDatos(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                lbl_descripcion.setText(actor.getDescripcion());
                lbl_nombre.setText(actor.getNombre());
                Picasso.with(getBaseContext()).load(actor.getFoto()).into(imagen);
                btn_filmografia.setVisibility(View.VISIBLE);

            }
        });

    }

    public void mostrarFallo() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                lbl_nombre.setText(getString(R.string.error_noactor));
                lbl_descripcion.setText(getString(R.string.error_vuelva));
                btn_filmografia.setVisibility(View.INVISIBLE);

            }
        });
    }

    public void  verFilmografia (View v){
        Intent i = new Intent(this,FilmografiaActivity.class);
        i.putExtra(FILMOGRAFIA,actor.getFilmografia());
        startActivity(i);
    }
}
