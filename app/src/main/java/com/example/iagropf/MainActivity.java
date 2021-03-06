package com.example.iagropf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {
        private Button login ;
        private EditText user , passtx;
        private boolean estar=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher_logo_round);
        setContentView(R.layout.activity_main);
        login = findViewById(R.id.btnIngresar);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarContrasenia(v);
                Intent intent = new Intent(MainActivity.this, Formularios.class);
                if(estar) startActivity(intent);

            }
        });

    }
    public boolean chekuser(){
        boolean resultado = false;
        user = findViewById(R.id.TxtUsuario);
        passtx = findViewById(R.id.TxtPass);
        String textopas= passtx.getText().toString().trim();
        String txtUsu= user.getText().toString().trim();
        if(textopas.isEmpty() || textopas==null){
            passtx.setError("Contraseña vacía");
            resultado = true;
        }
        if(txtUsu.isEmpty() || txtUsu==null){
            user.setError("Usuario vacío");
            resultado = true;
        }
        return resultado;
    }

    public void validarContrasenia(View vista){
        user = findViewById(R.id.TxtUsuario);
        passtx = findViewById(R.id.TxtPass);
       // Button boton = (Button) this.findViewById(R.id.button) ;

        //Obtengo los valores ingresados
        String usr=user.getText().toString().trim();
        String pass=passtx.getText().toString().trim();

        //instancio objeto Java
        UsuarioDTO usuIng=new UsuarioDTO("");
        usuIng.setNombre(usr);
        usuIng.setPassword(pass);

        //Convierto objeto Java a JSON
        Gson gson=new Gson();
        String jsonUsu=gson.toJson(usuIng);
        int SDK_INT = Build.VERSION.SDK_INT;
        if (SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);

            if (chekuser() ){

            }
            else {
                URL url;
                HttpURLConnection urlConnection = null;

                try {
                    String urlServicio = "http://10.0.2.2:8080/WebIagro2/rest/usuarios/login";
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
                    writer.write(jsonUsu);
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
                            estar = true;
                            Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_LONG).show();
                        }

                        //Borro usuario y contraseña ingresados. Previene el reingreso con botón de Volver
                        user.setText("");
                        passtx.setText("");

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
}