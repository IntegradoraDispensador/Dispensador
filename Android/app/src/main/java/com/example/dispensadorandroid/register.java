package com.example.dispensadorandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;

public class register extends AppCompatActivity {

    @BindView(R.id.input_name)
    EditText inputname;

    @BindView(R.id.input_email)
    EditText inputemail;

    @BindView(R.id.input_password)
    EditText inputpassword;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        ButterKnife.bind(this);
    }
}
