package com.example.iagropf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ActividadCampo extends AppCompatActivity  {
    DatePickerDialog picker;
    EditText titulo , descripcion , cantidad , fecha ;
    Spinner departamento, estacionMuestreo , metodoMuestreo ;
    private String selectedDate;
    private DatePickerDialog.OnDateSetListener setListener;

    int idMetodoMuestreoSeleccionado;
    int idDepartamentoSeleccionado;
    int idEstacionMuestreoSeleccionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_campo);

        traerListadoDeptos();
        traerListadoMetodos();
        traerListadoEstaciones();

        fecha=(EditText) findViewById(R.id.etFecha);
        fecha.setInputType(InputType.TYPE_NULL);

        handlerOnClickSpinnerMetodoM();
        handlerOnClickSpinnerDpto();
        handlerOnClickSpinnerEstacionM();
        handlerDataPickerFecha();
    }

    private void handlerOnClickSpinnerEstacionM() {
        estacionMuestreo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                EstacionMuestreoDTO estacionMuestreo = (EstacionMuestreoDTO) parent.getSelectedItem();
                //Toast.makeText(ActividadCampo.this, "MetodoM ID: "+mm.getIdMetodoMuestreo()+",  MetodoM Nombre : "+mm.getNombre(), Toast.LENGTH_SHORT).show();
                idEstacionMuestreoSeleccionado = Integer.parseInt(estacionMuestreo.getIdEstacionMuestreo()+"");

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void handlerOnClickSpinnerDpto() {
        departamento.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                DepartamentoDTO depto = (DepartamentoDTO) parent.getSelectedItem();
                //Toast.makeText(ActividadCampo.this, "MetodoM ID: "+mm.getIdMetodoMuestreo()+",  MetodoM Nombre : "+mm.getNombre(), Toast.LENGTH_SHORT).show();
                idDepartamentoSeleccionado = Integer.parseInt(depto.getIdDepartamento()+"");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void handlerDataPickerFecha() {
        fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(ActividadCampo.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                fecha.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });
    }

    private void handlerOnClickSpinnerMetodoM() {
        metodoMuestreo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                MetodoMuestreoDTO mm = (MetodoMuestreoDTO) parent.getSelectedItem();

                idMetodoMuestreoSeleccionado = Integer.parseInt(mm.getIdMetodoMuestreo()+"");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
    }

    public void traerListadoDeptos() {
        URL url;
        HttpURLConnection urlConnection = null;
        try {
            String urlServicio = "http://10.0.2.2:8080/WebIagro2/rest/departamentos/llenarSpinner";
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
                List<DepartamentoDTO> allNamesDep = new ArrayList<>();

                for (int i=0; i<jsonAsObj.length(); i++) {
                    JSONObject deptos = jsonAsObj.getJSONObject(i);
                    String nameD = deptos.getString("nombre");
                    int idD = deptos.getInt("idDepartamento");
                    DepartamentoDTO dep = new DepartamentoDTO(idD,nameD);
                    allNamesDep.add(dep);
                }

                departamento = (Spinner) findViewById(R.id.spinnerDepto);

                ArrayAdapter<DepartamentoDTO> dataAdapter = new ArrayAdapter<DepartamentoDTO>(ActividadCampo.this, android.R.layout.simple_spinner_dropdown_item,allNamesDep);

                //  dataAdapter.setDropDownViewResource (android.R.layout.simple_spinner_dropdown_item);

                departamento.setAdapter(dataAdapter);

            }

        } catch (IOException | JSONException e) {
            Log.d("STATE",e.getMessage());
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();

        }
    }

    public void traerListadoMetodos() {
        URL url;
        HttpURLConnection urlConnection = null;
        try {
            String urlServicio = "http://10.0.2.2:8080/WebIagro2/rest/metodomuestreos/llenarSpinner";
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


                List<MetodoMuestreoDTO> allNamesMet = new ArrayList<MetodoMuestreoDTO>();

                for (int i=0; i<jsonAsObj.length(); i++) {
                    JSONObject metodosM = jsonAsObj.getJSONObject(i);
                    String nameD = metodosM.getString("nombre");
                    int idm = metodosM.getInt("idMetodoMuestreo");
                    MetodoMuestreoDTO metm = new MetodoMuestreoDTO(idm,nameD);
                    allNamesMet.add(metm);
                }

                metodoMuestreo = (Spinner) findViewById(R.id.spinnerMetMuestreo);

                ArrayAdapter<MetodoMuestreoDTO> dataAdapter = new ArrayAdapter<MetodoMuestreoDTO>(ActividadCampo.this, android.R.layout.simple_spinner_dropdown_item,allNamesMet);

              //  dataAdapter.setDropDownViewResource (android.R.layout.simple_spinner_dropdown_item);

                metodoMuestreo.setAdapter(dataAdapter);

            }
        } catch (IOException | JSONException e) {
            Log.d("STATE",e.getMessage());
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();

        }
    }

    public void traerListadoEstaciones() {
        URL url;
        HttpURLConnection urlConnection = null;
        try {
            String urlServicio = "http://10.0.2.2:8080/WebIagro2/rest/estacionmuestreo/llenarSpinner";
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
                List<EstacionMuestreoDTO> allNamesEst = new ArrayList<EstacionMuestreoDTO>();

                for (int i=0; i<jsonAsObj.length(); i++) {
                    JSONObject estacionesM = jsonAsObj.getJSONObject(i);
                    String nameE = estacionesM.getString("nombre");
                    int idE = estacionesM.getInt("idEstacionMuestreo");
                    EstacionMuestreoDTO estM = new EstacionMuestreoDTO(idE,nameE);
                    allNamesEst.add(estM);
                }

                estacionMuestreo = (Spinner) findViewById(R.id.spinnerEstacionMuestreo);

                ArrayAdapter<EstacionMuestreoDTO> dataAdapter = new ArrayAdapter<EstacionMuestreoDTO>(ActividadCampo.this, android.R.layout.simple_spinner_dropdown_item,allNamesEst);

                //  dataAdapter.setDropDownViewResource (android.R.layout.simple_spinner_dropdown_item);

                estacionMuestreo.setAdapter(dataAdapter);

            }

        } catch (IOException | JSONException e) {
            Log.d("STATE",e.getMessage());
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();

        }
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
        setListener= new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker newFragment, int year, int month, int dayOfMonth) {
                System.out.println(year);
                selectedDate=dayOfMonth+"/"+month+1+"/"+year;
                fecha=(EditText)findViewById(R.id.etFecha);
                Toast.makeText(ActividadCampo.this,selectedDate,Toast.LENGTH_SHORT).show();
                fecha.setText(selectedDate);

            }
        };



    }

    public boolean validarCampos(){
        boolean value = true;

        String txtTitulo = titulo.getText().toString().trim();
        String txtDescripcion = descripcion.getText().toString().trim();
        String txtCantidad = cantidad.getText().toString();

        if(txtTitulo == null || txtTitulo.equals("")){
            titulo.setError("debe ingresar titulo");
            value = false;
        }

        if (txtDescripcion == null || txtDescripcion.equals("")){
            descripcion.setError("debe ingresar descipcion");
            value = false;
        }
        if (txtCantidad == null || txtCantidad.equals("")){
            descripcion.setError("debe ingresar cantidad");
            value = false;
        }

        return value;

    }

    public void llamoalRest(View view){
    titulo = findViewById(R.id.textTitulo);
    descripcion = findViewById(R.id.multiLineDescripcion);
    cantidad = findViewById(R.id.eTNumberCantidad);
    fecha=(EditText) findViewById(R.id.etFecha);
    departamento = findViewById(R.id.spinnerDepto);
    metodoMuestreo = findViewById(R.id.spinnerMetMuestreo);
    estacionMuestreo = findViewById(R.id.spinnerEstacionMuestreo);


    //Obtengo los valores ingresados
    String title = titulo.getText().toString().trim();
    String description =descripcion.getText().toString().trim();
    String quantity = cantidad.getText().toString().trim() == null ? "0" : cantidad.getText().toString().trim();
    String date = fecha.getText().toString().trim();


    DepartamentoDTO depa = new DepartamentoDTO(idDepartamentoSeleccionado,"");
    MetodoMuestreoDTO muestreo = new MetodoMuestreoDTO(idMetodoMuestreoSeleccionado,"");
    EstacionMuestreoDTO estam = new EstacionMuestreoDTO(idEstacionMuestreoSeleccionado,"");

    Bundle extras = getIntent().getExtras();
    Integer id = extras.getInt("idFormulario");

    FormularioDTO form = new FormularioDTO(id,"");

    //instancio objeto Java
    //ActividadCampoDTO act = new ActividadCampoDTO(title,description, Date.valueOf(date),Integer.valueOf(quantity),id,idMetodoMuestreoSeleccionado,idEstacionMuestreoSeleccionado,idDepartamentoSeleccionado);
    ActividadCampoDTO act = new ActividadCampoDTO();
    act.setNombre(title);
    act.setDescripcion(description);
    act.setFecha(Date.valueOf(date));
    act.setCantidad(Integer.valueOf(quantity));
    act.setFormulario(form);
    act.setMetMuestreo(muestreo);
    act.setEstacionMuestreo(estam);
    act.setDepartamento(depa);


    //Convierto objeto Java a JSON
    Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();

    String jsonAct=gson.toJson(act);
    int SDK_INT = Build.VERSION.SDK_INT;
    if (SDK_INT > 8)
    {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);

        if (!validarCampos() ){

    }
            else {
        URL url;
        HttpURLConnection urlConnection = null;

        try {
            String urlServicio = "http://10.0.2.2:8080/WebIagro2/rest/actividades/crearA";
            url = new URL(urlServicio);

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestProperty("content-type", "application/json");
            urlConnection.setRequestMethod("POST");
            urlConnection.setReadTimeout(7500);
            urlConnection.setConnectTimeout(7500);
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);

            OutputStream os = urlConnection.getOutputStream();

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, StandardCharsets.UTF_8));
            writer.write(jsonAct);
            writer.flush();
            writer.close();
            os.close();

            int respuesta=urlConnection.getResponseCode();
            if(respuesta==HttpURLConnection.HTTP_OK){
                //Obtengo valores devueltos por Rest WS
                BufferedReader br=new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                String jsonResult=br.readLine();

                JsonElement jsonElement = new JsonParser().parse(jsonResult);
                JsonObject jsonObject = jsonElement.getAsJsonObject();

                boolean error = jsonObject.get("error").getAsBoolean();
                String mensaje = jsonObject.get("mensaje").toString();

                if(!error){

                    Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_LONG).show();
                }

            }
            else{
                Toast.makeText(getApplicationContext(), "Hubo algun error", Toast.LENGTH_LONG).show();
            }

        } catch (IOException eio) {
            Toast.makeText(getApplicationContext(), "CONNECT: No se puede conectar al servidor", Toast.LENGTH_LONG).show();
            eio.printStackTrace();
        }

    }

}
}
    public void btnVolveraForm(View v) {
        Intent intent = new Intent(ActividadCampo.this, Formularios.class);
        startActivity(intent);
    }

}