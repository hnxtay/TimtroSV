/*
 * Copyright (c) 2020.  by kd1412
 */

package com.dev.kd1412.timtrosv.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.dev.kd1412.timtrosv.R;
import com.dev.kd1412.timtrosv.databinding.ActivityDebugBinding;
import com.dev.kd1412.timtrosv.model.User;
import com.dev.kd1412.timtrosv.network.RoomServiceApi;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DebugActivity extends AppCompatActivity {
    private ActivityDebugBinding debugBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        debugBinding = DataBindingUtil.setContentView(this, R.layout.activity_debug);
        checkUser();
    }

    private void checkUser() {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        RoomServiceApi.getInstance().getUser(firebaseUser.getUid()).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful() && response.body() != null && response.body().equals("")) {
                    Toast.makeText(DebugActivity.this, "nonnull", Toast.LENGTH_SHORT).show();
                } else {
                    User user = new User(firebaseUser.getUid(), firebaseUser.getDisplayName()
                            , "Người cho thuê", "", firebaseUser.getEmail()
                            , firebaseUser.getPhoneNumber());
                    RoomServiceApi.getInstance().postUser(user).enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            if (response.body() != null){

                            }else {
//                                Toast.makeText(DebugActivity.this, "User already exists", Toast.LENGTH_SHORT).show();
                                Snackbar.make(debugBinding.getRoot(),R.string.text_user_already_exists,Snackbar.LENGTH_SHORT).show();
                                debugBinding.textView7.setText(R.string.text_user_already_exists);
                            }
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {
                            Log.e("TAG", "onFailure: " + call.request().url() + " req " + call.request(), t);
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("TAG", "onFailure: " + call.request().url() + " req " + call.request(), t);
                Log.d("TAG", "onFailure: " + firebaseUser.getUid());
            }
        });
    }
}