package com.dev.kd1412.timtrosv.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.dev.kd1412.timtrosv.R;
import com.dev.kd1412.timtrosv.databinding.ActivityLoginBinding;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;


public class LoginActivity extends AppCompatActivity {
    private EditText edt_username, edt_password;
    private Button btn_login;
    private ImageView img_fb, img_gg;
    private static final int SIGNED_IN = 0;
    private static final int STATE_SIGNING_IN = 1;
    private static final int STATE_IN_PROGRESS = 2;
    private static final int RC_SIGN_IN = 0;

    @Override
    protected void onStart() {
        super.onStart();

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        updateUI(account);
    }

    private void updateUI(GoogleSignInAccount account) {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
        ActivityLoginBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_login);
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                LoginActivity.this.finish();
            }
        });
        GoogleSignInOptions sign = new GoogleSignInOptions.Builder(GoogleSignInOptions
                .DEFAULT_GAMES_SIGN_IN)
                .requestEmail()
                .build();
        GoogleSignInClient signInClient = GoogleSignIn.getClient(this,sign);

        binding.imgGg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSign = signInClient.getSignInIntent();
                startActivityForResult(intentSign,RC_SIGN_IN);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==RC_SIGN_IN){
            Task<GoogleSignInAccount> accountTask = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(accountTask);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> accountTask) {
        try{
            GoogleSignInAccount googleSignInAccount = accountTask.getResult(ApiException.class);
        }catch (Exception e){

        }
    }
}