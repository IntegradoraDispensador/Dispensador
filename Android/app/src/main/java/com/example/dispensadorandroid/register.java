package com.example.dispensadorandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.widget.EditText;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.example.dispensadorandroid.entities.AccessToken;
import com.example.dispensadorandroid.entities.ApiError;
import com.example.dispensadorandroid.network.ApiService;
import com.example.dispensadorandroid.network.RetrofitBuilder;
import com.google.android.material.textfield.TextInputLayout;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;

public class register extends AppCompatActivity {

    private static final String TAG = "Register";

    @BindView(R.id.input_name)
    TextInputLayout inputname;


    @BindView(R.id.input_email)
    TextInputLayout inputemail;


    @BindView(R.id.input_password)
    TextInputLayout inputpassword;

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

        validator = new AwesomeValidation(ValidationStyle.TEXT_INPUT_LAYOUT);

        tokenManager = TokenManager.getInstance(getSharedPreferences("prefs", MODE_PRIVATE));

        setupRules();
    }

    @OnClick(R.id.btn_signup)
    void register() {

        String name = inputname.getEditText().getText().toString();
        String email = inputemail.getEditText().getText().toString();
        String password = inputpassword.getEditText().getText().toString();

        inputname.setError(null);
        inputemail.setError(null);
        inputpassword.setError(null);

        validator.clear();

        if (validator.validate()) {

            call = service.register(name, email, password);
            call.enqueue(new Callback<AccessToken>() {
                @Override
                public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {


                    Log.w(TAG, "on response: " + response);
                    if (response.isSuccessful()) {

                        Log.w(TAG, "on response: " + response.body());

                        tokenManager.saveToken(response.body());

                    } else {

                        handleErrors(response.errorBody());

                    }
                }

                @Override
                public void onFailure(Call<AccessToken> call, Throwable t) {

                    Log.w(TAG, "on failure: " + t.getMessage());

                }
            });
        }
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

