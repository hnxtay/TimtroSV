package com.dev.kd1412.timtrosv.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;

import com.dev.kd1412.timtrosv.R;
import com.dev.kd1412.timtrosv.databinding.SplashScreenBinding;

public class SplashActivity extends AppCompatActivity {
    private SplashScreenBinding splashScreenBinding;
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
            Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
            startActivity(intent);
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