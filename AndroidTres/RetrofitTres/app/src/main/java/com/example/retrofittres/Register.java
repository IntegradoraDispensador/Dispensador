package com.example.retrofittres;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.example.retrofittres.entities.AccessToken;
import com.example.retrofittres.entities.ApiError;
import com.example.retrofittres.network.ApiService;
import com.example.retrofittres.network.RetrofitBuilder;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Converter;
import retrofit2.Retrofit;



public class Register extends AppCompatActivity {

    private static final String TAG = "Register";


    @BindView(R.id.input_name)
    EditText inputname;


    @BindView(R.id.input_email)
    EditText inputemail;


    @BindView(R.id.input_password)
    EditText inputpassword;





    ApiService service;
    Call<AccessToken> call;
    AwesomeValidation validator;
    TokenManager tokenManager;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);


        ButterKnife.bind(this);

        service = RetrofitBuilder.CreateService(ApiService.class);

        validator = new AwesomeValidation(ValidationStyle.BASIC);

        tokenManager = TokenManager.getInstance(getSharedPreferences("prefs", MODE_PRIVATE));

        setupRules();
    }



    @OnClick(R.id.btn_signup)
    void registeruser() {

       // Toast.makeText(this, "Hola", Toast.LENGTH_LONG).show();

        String name = inputname.getText().toString();
        String email = inputemail.getText().toString();
        String password = inputpassword.getText().toString();


        inputname.setError(null);
        inputemail.setError(null);
        inputpassword.setError(null);


        validator.clear();


        if (validator.validate()) {

            call = service.signup(name, email, password);
            call.enqueue(new Callback<AccessToken>() {
                @Override
                public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {


                    Log.w(TAG, "onResponse: " + response);
                    if (response.isSuccessful()) {

                        Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();

                        Log.w(TAG, "onResponse: " + response.body());

                        tokenManager.saveToken(response.body());
                        startActivity(new Intent(Register.this, BotonesActivity.class));
                        finish();


                    } else {

                        handleErrors(response.errorBody());

                    }
                }

                @Override
                public void onFailure(Call<AccessToken> call, Throwable t) {

                    Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();


                    Log.w(TAG, "onFailure: " + t.getMessage());

                }
            });
        }
    }

    @OnClick(R.id.link_login)
    void goToRegister(){
        startActivity(new Intent(Register.this, Login.class));
    }






    public void handleErrors (ResponseBody response){

        ApiError apiError = Utils.convertErrors(response);

        for (Map.Entry<String, List<String>> error : apiError.getErrors().entrySet()) {
            if (error.getKey().equals("name")) {
                inputname.setError(error.getValue().get(0));
            }

            if (error.getKey().equals("email")) {
                inputemail.setError(error.getValue().get(0));
            }

            if (error.getKey().equals("password")) {
                inputpassword.setError(error.getValue().get(0));
            }


        }

    }

    public void setupRules () {
        validator.addValidation(this, R.id.input_name, RegexTemplate.NOT_EMPTY, R.string.err_name);
        validator.addValidation(this, R.id.input_email, Patterns.EMAIL_ADDRESS, R.string.err_email);
        validator.addValidation(this, R.id.input_password, RegexTemplate.NOT_EMPTY, R.string.err_password);
    }

    @Override
    protected void onDestroy () {

        super.onDestroy();
        if (call != null) {
            call.cancel();
            call = null;
        }

    }
}