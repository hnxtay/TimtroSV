/*
 * Copyright (c) 2020.  by kd1412
 */

/*
 * Copyright (c) 2020.  by kd1412
 */

package com.dev.kd1412.timtrosv.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.util.Log;
import android.widget.Toast;

import com.dev.kd1412.timtrosv.R;
import com.dev.kd1412.timtrosv.databinding.ActivityDebugBinding;
import com.dev.kd1412.timtrosv.model.User;
import com.dev.kd1412.timtrosv.network.RoomServiceApi;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DebugActivity extends AppCompatActivity {
    private ActivityDebugBinding debugBinding;
    public static final int REQUEST_CODE = 1;
    private static final int CALL_PERMISSION_CODE = 100;
    private static final int STORAGE_PERMISSION_CODE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        debugBinding = DataBindingUtil.setContentView(this, R.layout.activity_debug);
        checkUser();
        debugBinding.btnUpload.setOnClickListener(v -> {
            if(checkPermission(Manifest.permission.READ_EXTERNAL_STORAGE,STORAGE_PERMISSION_CODE)){
                uploadImagetoImgur();
            }
        });
    }

    private void uploadImagetoImgur() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                Uri selectedImage = data.getData();
                Log.d("TAG", "onActivityResult: " + getPath(selectedImage));
                debugBinding.imgDebug.setImageURI(selectedImage);
                try {
                    ParcelFileDescriptor fileDescriptor = getApplicationContext().getContentResolver()
                            .openFileDescriptor(selectedImage,"r",null);
                    InputStream inputStream = new FileInputStream(fileDescriptor.getFileDescriptor());
                    File file = new File(getApplicationContext().getCacheDir(),getPath(selectedImage));

                    FileOutputStream outputStream = new FileOutputStream(file);

                    byte[] buffer = new byte[8 * 1024];
                    int bytes = inputStream.read(buffer);
                    while (bytes >= 0){
                        outputStream.write(buffer,0,inputStream.read(buffer));
                        bytes = inputStream.read(buffer);
                    }

                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
    public String getPath(@NonNull Uri uri) {
        String name = "";
        Cursor cursor = getApplicationContext().getContentResolver().query(uri, null, null, null, null);
        assert cursor != null;
        cursor.moveToFirst();
        name = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
        cursor.close();

        return name;
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
                            if (response.body() != null) {
                                Snackbar.make(debugBinding.getRoot(), R.string.text_user_uploaded, Snackbar.LENGTH_SHORT).show();
                                debugBinding.tvDebug.setText(R.string.text_user_uploaded);
                            } else {
                                Snackbar.make(debugBinding.getRoot(), R.string.text_user_already_exists, Snackbar.LENGTH_SHORT).show();
                                debugBinding.tvDebug.setText(R.string.text_user_already_exists);
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

    public boolean checkPermission(String permission, int requestCode) {
        if (ContextCompat.checkSelfPermission(DebugActivity.this, permission)
                == PackageManager.PERMISSION_DENIED) {
            // Requesting the permission
            ActivityCompat.requestPermissions(DebugActivity.this,
                    new String[] { permission },
                    requestCode);
            return false;
        }
        else {
            return true;
        }
    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == CALL_PERMISSION_CODE) {
//            if (grantResults.length > 0
//                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                Toast.makeText(DebugActivity.this,
//                        "Call Permission Granted",
//                        Toast.LENGTH_SHORT)
//                        .show();
//            }
//            else {
//                Toast.makeText(DebugActivity.this,
//                        "Call Permission Denied",
//                        Toast.LENGTH_SHORT)
//                        .show();
//            }
//        }
//        else if (requestCode == STORAGE_PERMISSION_CODE) {
//            if (grantResults.length > 0
//                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                Toast.makeText(DebugActivity.this,
//                        "Storage Permission Granted",
//                        Toast.LENGTH_SHORT)
//                        .show();
//            }
//            else {
//                Toast.makeText(DebugActivity.this,
//                        "Storage Permission Denied",
//                        Toast.LENGTH_SHORT)
//                        .show();
//            }
//        }
//    }
}