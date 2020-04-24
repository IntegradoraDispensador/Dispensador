package com.example.retrofittres;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.retrofittres.Interface.Adafruit;
import com.example.retrofittres.Model.Example;
import com.example.retrofittres.ModelUltrasonico.ExampleUltrasonico;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UltrasonicoActivity extends AppCompatActivity {

    private TextView textViewResult;

    public String token = "aio_TAPe68HndORlKq8E1Iv20EDtNLOS";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ultrasonico);

        textViewResult = findViewById(R.id.textUltrasonico);

        Button botonU = (Button) findViewById(R.id.btn_U);
        botonU.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getDistanciaU();
            }
        });
    }

    private void getDistanciaU() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://io.adafruit.com/api/v2/ubaldo01/feeds/ultrasonico/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Adafruit adafruit = retrofit.create(Adafruit.class);

        Call<List<ExampleUltrasonico>> call = adafruit.traerData(token ,5);

        call.enqueue(new Callback<List<ExampleUltrasonico>>() {
            @Override
            public void onResponse(Call<List<ExampleUltrasonico>> call, Response<List<ExampleUltrasonico>> response) {


                if (!response.isSuccessful()){


                    textViewResult.setText("Codigo: " + response.code());
                    return ;
                }

                List<ExampleUltrasonico> datosU = response.body();

                for (ExampleUltrasonico datoU : datosU){
                    String content = "";
                    content += "Centimetros: " + datoU.getValue() + "\n";
                    content += "Fecha: " + datoU.getCreatedAt() + "\n";

                    textViewResult.append(content);
                }

            }
            @Override
            public void onFailure(Call<List<ExampleUltrasonico>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
                Toast.makeText(UltrasonicoActivity.this, "error", Toast.LENGTH_LONG).show();
            }
        });
    }
}
