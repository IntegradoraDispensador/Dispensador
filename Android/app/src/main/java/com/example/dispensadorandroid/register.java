package com.example.dispensadorandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.example.dispensadorandroid.entities.AccessToken;
import com.example.dispensadorandroid.network.ApiService;
import com.example.dispensadorandroid.network.RetrofitBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class register extends AppCompatActivity {

    private  static  final String TAG = "Register";

    @BindView(R.id.input_name)
    EditText inputname;

    @BindView(R.id.input_email)
    EditText inputemail;

    @BindView(R.id.input_password)
    EditText inputpassword;

    ApiService service;
    Call<AccessToken> call;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        ButterKnife.bind(this);

        service = RetrofitBuilder.CreateService(ApiService.class);
    }

    @OnClick(R.id.btn_signup)
         void register(){

            String name = inputname.getText().toString();
            String email = inputemail.getText().toString();
            String password = inputpassword.getText().toString();

            call = service.register(name, email, password);
            call.enqueue(new Callback<AccessToken>() {
                @Override
                public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {


                    Log.w(TAG, "on response: " + response);
                    if (response.isSuccessful()){

                    }

                    else{

                    }
                }

                @Override
                public void onFailure(Call<AccessToken> call, Throwable t) {

                    Log.w(TAG, "on failure: " + t.getMessage());

                }
            });
        }


    @Override
    protected void onDestroy(){

        super.onDestroy();
        if (call != null) {
            call.cancel();
            call = null;
        }

    }
}
