package com.example.iagropf;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Formularios extends AppCompatActivity {

    ListView listado;
    ArrayAdapter ADP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formularios);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher_logo_round);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        listado = (ListView) findViewById(R.id.LisFormulario);
        traerListado();
        eventoClickFormularios();
    }

    //instancio objeto Java
    FormularioDTO form = new FormularioDTO();

    //Convierto objeto Java a JSON
    Gson gson = new Gson();
    String jsonForm = gson.toJson(form);

//traigo el listado de formularios y los meto en el arrayadapter
    public void traerListado() {
        URL url;
        HttpURLConnection urlConnection = null;
        try {
            String urlServicio = "http://10.0.2.2:8080/WebIagro2/rest/formularios/listaFormularios";
            url = new URL(urlServicio);

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestProperty("content-type", "application/json");
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(7500);
            urlConnection.setConnectTimeout(7500);
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(false);

            int respuesta = urlConnection.getResponseCode();
            if (respuesta == HttpURLConnection.HTTP_OK) {
                //Obtengo valores devueltos por Rest WS
                BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                String jsonResult = br.readLine();

                JSONArray jsonAsObj = new JSONArray(jsonResult);
                ObjectMapper mapper = new ObjectMapper();
                List<FormularioDTO> forms = mapper.readValue(jsonAsObj.toString(), new TypeReference<List<FormularioDTO>>(){});

                Map<String, Integer> map = new HashMap<String, Integer>();

                ADP = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, forms);
                ADP.notifyDataSetChanged();
                listado.setAdapter(ADP);
            }

        } catch (IOException | JSONException e) {
            Log.d("STATE",e.getMessage());
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();

        }
    }
//me llevo el id del formulario para la otra activity
    public void eventoClickFormularios(){
        listado.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long idForm) {

                FormularioDTO mm = (FormularioDTO) ADP.getItem(position);

                Intent intent = new Intent(Formularios.this, ActividadCampo.class);
                intent.putExtra("idFormulario", mm.getIdFormulario());

                startActivity(intent);
            }
        });
    }

    public void btnVolver(View v) {
        Intent intent = new Intent(Formularios.this, MainActivity.class);
        startActivity(intent);
    }

}