package com.example.retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.retrofit.Interface.AdafruitIO;
import com.example.retrofit.Model.Example;
import com.example.retrofit.Model.DistanciaWrapper;
import com.example.retrofit.Model.Example;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    private TextView mjsonText;

    public String token = "aio_oOmk46UrCwESAcw9Rdr3ZHYlzIpC";
    public String username = "ubaldo01";







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mjsonText = findViewById(R.id.jsonText);
        //Button boton = findViewById(R.id.boton);
        //getDistancia();



       Button boton = (Button) findViewById(R.id.boton);
        boton.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getDistancia();
            }
        });


        }





        private void getDistancia() {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://io.adafruit.com/api/v2/ubaldo01/feeds/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            AdafruitIO adafruitIO = retrofit.create(AdafruitIO.class);

            Call<Example> call = adafruitIO.getDistancia(token);

            call.enqueue(new Callback<Example>() {
                @Override
                public void onResponse(Call<Example> call, Response<Example> response) {



                    if (!response.isSuccessful()){
                        mjsonText.setText("Codigo: " + response.body());
                        return ;
                    }

                    response.body().setLastValue("500");

                    Toast.makeText(MainActivity.this,response.body().getLastValue().toString(),Toast.LENGTH_LONG).show();


                }

                @Override
                public void onFailure(Call<Example> call, Throwable t) {

                    mjsonText.setText(t.getMessage());
                    Toast.makeText(MainActivity.this,"Error",Toast.LENGTH_SHORT).show();


                }
            });

    }
}
