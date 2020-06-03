package com.dev.kd1412.timtrosv.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.dev.kd1412.timtrosv.R;
import com.dev.kd1412.timtrosv.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
    private EditText edt_username, edt_password;
    private Button btn_login;
    private ImageView img_fb, img_gg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        ActivityLoginBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_login);
        
    }
}