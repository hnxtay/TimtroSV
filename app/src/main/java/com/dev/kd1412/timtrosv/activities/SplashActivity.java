package com.dev.kd1412.timtrosv.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;

import com.dev.kd1412.timtrosv.R;
import com.dev.kd1412.timtrosv.databinding.SplashScreenBinding;
import com.dev.kd1412.timtrosv.model.User;
import com.dev.kd1412.timtrosv.network.RoomServiceApi;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends AppCompatActivity {
    private SplashScreenBinding splashScreenBinding;

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        splashScreenBinding = DataBindingUtil.setContentView(this,R.layout.splash_screen);
        SplashASyncTask aSyncTask = new SplashASyncTask();
        aSyncTask.execute();
    }

    class SplashASyncTask extends AsyncTask<Void , Integer, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
            SplashActivity.this.finish();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            for (int i = 0; i <= 100; i++) {
                SystemClock.sleep(10);
                publishProgress(i);
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            splashScreenBinding.progressBar.setProgress(values[0]);
        }
    }
}