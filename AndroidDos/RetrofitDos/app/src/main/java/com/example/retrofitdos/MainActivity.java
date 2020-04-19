package com.example.retrofitdos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.retrofitdos.Interface.Adafruit;
import com.example.retrofitdos.Model.Example;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView mjsonText;

    public String token = "aio_pHjQ04DaljjLSM6O4dgdWzXO7jNj";
    public String tokenDos = "aio_OuaG21e1LNIio6zF1IrleZCZP0cx";
    public String username = "ubaldo01";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button boton = (Button) findViewById(R.id.boton);
        boton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getDistancia();
            }
        });

        Button botonDos = (Button) findViewById(R.id.botonDos);
        botonDos.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getDistanciaDos();
            }
        });

        Button botonTres = (Button) findViewById(R.id.button);
        botonTres.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "hola", Toast.LENGTH_LONG).show();
                getDistanciaTres();
            }
        });

        Button botonCuatro = (Button) findViewById(R.id.button2);
        botonCuatro.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "holaDos", Toast.LENGTH_LONG).show();

                getDistanciaCuatro();
            }
        });

    }

    private void getDistanciaDos() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://io.adafruit.com/api/v2/ubaldo01/feeds/onoff/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Adafruit adafruit = retrofit.create(Adafruit.class);

        Example example = new Example("OFF");

        Call<Example> call = adafruit.getDistancia(example, token);

        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                if (!response.isSuccessful()){
                    mjsonText.setText("Codigo: " + response.body());
                    return ;
                }

                Toast.makeText(MainActivity.this,response.body().getValue(),Toast.LENGTH_LONG).show();
            }
            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                mjsonText.setText(t.getMessage());
                Toast.makeText(MainActivity.this,"Error", Toast.LENGTH_SHORT).show();
            }
        });
    }



    private void getDistancia() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://io.adafruit.com/api/v2/ubaldo01/feeds/onoff/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Adafruit adafruit = retrofit.create(Adafruit.class);

        Example example = new Example("ON");

        Call<Example> call = adafruit.getDistancia(example, token);

        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                if (!response.isSuccessful()){
                    mjsonText.setText("Codigo: " + response.body());
                    return ;
                }

                Toast.makeText(MainActivity.this,response.body().getValue(),Toast.LENGTH_LONG).show();
            }
            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                mjsonText.setText(t.getMessage());
                Toast.makeText(MainActivity.this,"Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getDistanciaTres() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://io.adafruit.com/api/v2/GerardoDM/feeds/agua/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Adafruit adafruit = retrofit.create(Adafruit.class);

        Example example = new Example("ON");

        Call<Example> call = adafruit.getDistancia(example, tokenDos);

        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                if (!response.isSuccessful()){
                    mjsonText.setText("Codigo: " + response.body());
                    return ;
                }

                Toast.makeText(MainActivity.this,response.body().getValue(),Toast.LENGTH_LONG).show();
            }
            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                mjsonText.setText(t.getMessage());
                Toast.makeText(MainActivity.this,"Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getDistanciaCuatro() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://io.adafruit.com/api/v2/GerardoDM/feeds/agua/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Adafruit adafruit = retrofit.create(Adafruit.class);

        Example example = new Example("OFF");

        Call<Example> call = adafruit.getDistancia(example, tokenDos);

        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                if (!response.isSuccessful()){
                    mjsonText.setText("Codigo: " + response.body());
                    return ;
                }

                Toast.makeText(MainActivity.this,response.body().getValue(),Toast.LENGTH_LONG).show();
            }
            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                mjsonText.setText(t.getMessage());
                Toast.makeText(MainActivity.this,"Error", Toast.LENGTH_SHORT).show();
            }
        });
    }



}