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

public class Login extends AppCompatActivity {

    private static final String TAG = "Login";


    @BindView(R.id.input_email)
    EditText inputemail;


    @BindView(R.id.input_password)
    EditText inputpassword;

    ApiService service;
    TokenManager tokenManager;
    Call<AccessToken> call;
    AwesomeValidation validator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        service = RetrofitBuilder.CreateService(ApiService.class);
        tokenManager = TokenManager.getInstance(getSharedPreferences("prefs", MODE_PRIVATE));
        validator = new AwesomeValidation(ValidationStyle.BASIC);
        setupRules();

    }

    @OnClick(R.id.btn_login)
    void login(){


        String email = inputemail.getText().toString();
        String password = inputpassword.getText().toString();

        inputemail.setError(null);
        inputpassword.setError(null);

        validator.clear();

        if (validator.validate()){

        call = service.login(email,password);
        call.enqueue(new Callback<AccessToken>() {
            @Override
            public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {

                Log.w(TAG , "on response: " + response.body());

                if (response.isSuccessful()){

                    tokenManager.saveToken(response.body());
                    startActivity(new Intent(Login.this, BotonesActivity.class));
                    finish();


                } else{

                    if (response.code() == 422){
                        handleErrors(response.errorBody());
                    }

                    if (response.code() == 401){
                        ApiError apiError = Utils.convertErrors(response.errorBody());
                        Toast.makeText(Login.this, apiError.getMessage(), Toast.LENGTH_LONG).show();
                    }

                }
            }

            @Override
            public void onFailure(Call<AccessToken> call, Throwable t) {
                Log.w(TAG,"on failure: " + t.getMessage());

            }
        });

    }
    }

    @OnClick(R.id.link_register)
        void goToRegister(){
            startActivity(new Intent(Login.this, Register.class));
        }


    public void handleErrors (ResponseBody response){

        ApiError apiError = Utils.convertErrors(response);

        for (Map.Entry<String, List<String>> error : apiError.getErrors().entrySet()) {

            if (error.getKey().equals("email")) {
                inputemail.setError(error.getValue().get(0));
            }

            if (error.getKey().equals("password")) {
                inputpassword.setError(error.getValue().get(0));
            }


        }

    }

    public void setupRules () {
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
