package com.example.retrofittres;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.retrofittres.Interface.Adafruit;
import com.example.retrofittres.Model.Example;

import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BotonesActivity extends AppCompatActivity {

    private TextView mjsonText;

    public String token = "aio_MJKy655V2CjnXX6z6tL6xfrEJM7c";
    public String tokenDos = "aio_IUvL84xa2W8MWPU5LkBnsmRQRDx9";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_botones);

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
                Toast.makeText(BotonesActivity.this, "hola", Toast.LENGTH_LONG).show();
                getDistanciaTres();
            }
        });

        Button botonCuatro = (Button) findViewById(R.id.button2);
        botonCuatro.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(BotonesActivity.this, "holaDos", Toast.LENGTH_LONG).show();

                getDistanciaCuatro();
            }
        });

        Button botonCambiar = (Button) findViewById(R.id.botonCambiar);
        botonCambiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(BotonesActivity.this, UltrasonicoActivity.class));

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

                Toast.makeText(BotonesActivity.this,response.body().getValue(),Toast.LENGTH_LONG).show();
            }
            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                mjsonText.setText(t.getMessage());
                Toast.makeText(BotonesActivity.this,"Error", Toast.LENGTH_SHORT).show();
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

                Toast.makeText(BotonesActivity.this,response.body().getValue(),Toast.LENGTH_LONG).show();
            }
            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                mjsonText.setText(t.getMessage());
                Toast.makeText(BotonesActivity.this,"Error", Toast.LENGTH_SHORT).show();
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

                Toast.makeText(BotonesActivity.this,response.body().getValue(),Toast.LENGTH_LONG).show();
            }
            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                mjsonText.setText(t.getMessage());
                Toast.makeText(BotonesActivity.this,"Error", Toast.LENGTH_SHORT).show();
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

                Toast.makeText(BotonesActivity.this,response.body().getValue(),Toast.LENGTH_LONG).show();
            }
            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                mjsonText.setText(t.getMessage());
                Toast.makeText(BotonesActivity.this,"Error", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
