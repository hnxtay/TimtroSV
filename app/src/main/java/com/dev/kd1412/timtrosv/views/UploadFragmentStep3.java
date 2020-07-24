/*
 * Copyright (c) 2020.  by kd1412
 */

package com.dev.kd1412.timtrosv.views;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dev.kd1412.timtrosv.R;
import com.dev.kd1412.timtrosv.activities.DebugActivity;
import com.dev.kd1412.timtrosv.activities.MainActivity;
import com.dev.kd1412.timtrosv.databinding.FragmentUploadStep3Binding;
import com.dev.kd1412.timtrosv.model.Room;
import com.dev.kd1412.timtrosv.network.RoomServiceApi;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.core.content.ContextCompat.checkSelfPermission;

public class UploadFragmentStep3 extends Fragment {
    private FragmentUploadStep3Binding uploadStep3Binding;
    private static final int REQUEST_CODE = 1;
    private static final int CALL_PERMISSION_CODE = 100;
    private static final int STORAGE_PERMISSION_CODE = 101;
    private Room room;
    private File file;
    private FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requireActivity().getOnBackPressedDispatcher().addCallback(new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Navigation.findNavController(requireView()).navigateUp();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        uploadStep3Binding = DataBindingUtil.inflate(inflater, R.layout.fragment_upload_step3, container, false);

        room = UploadFragmentStep3Args.fromBundle(requireArguments()).getRoom();
        room.setmUserID(firebaseUser.getUid());
        room.setmImg("https://i.imgur.com/wMN8Met.jpg");
        Log.d("TAG", "onClick: " + room.mLocation + " " + room.mAcreage
                + " " + room.mPrice + " " + room.mUtilities.toString() + " " + room.mDescription + " " + room.mUserID);


        uploadStep3Binding.fabBack.setOnClickListener(v -> {
            Navigation.findNavController(requireView()).navigateUp();
        });

        uploadStep3Binding.fabUpload.setOnClickListener(v -> {
            RoomServiceApi.getInstance().postRoom(room, MultipartBody.Part.createFormData("image", file.getName(),
                    RequestBody.create(
                            MediaType.parse(String.format("image/%s", getFileExtension(file.getName()))),
                            file
                    )
            )).enqueue(new Callback<Room>() {
                @Override
                public void onResponse(Call<Room> call, Response<Room> response) {
                    Toast.makeText(requireContext(), "Đăng thành công", Toast.LENGTH_SHORT).show();
                    Navigation.findNavController(requireView()).navigate(R.id.action_uploadFragmentStep3_to_navigation_home);
                    Log.d("TAG", "onFailure: " + call.request().body().toString());
                }

                @Override
                public void onFailure(Call<Room> call, Throwable t) {
                    Log.d("TAG", "onFailure: " + t);
                }
            });
        });

        uploadStep3Binding.btnUpload.setOnClickListener(v -> {
            if (checkPermission(Manifest.permission.READ_EXTERNAL_STORAGE, STORAGE_PERMISSION_CODE)) {
                uploadImagetoServer();
            }

        });


        return uploadStep3Binding.getRoot();
    }

    private void uploadImagetoServer() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, false);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                Uri selectedImage = data.getData();
                Log.d("TAG", "onActivityResult: " + getPath(selectedImage));
                uploadStep3Binding.imgUpload.setImageURI(selectedImage);
                try {
                    ParcelFileDescriptor fileDescriptor = requireContext().getContentResolver()
                            .openFileDescriptor(selectedImage, "r", null);
                    InputStream inputStream = new FileInputStream(fileDescriptor.getFileDescriptor());
                    file = new File(requireContext().getCacheDir(), getPath(selectedImage));

                    FileOutputStream outputStream = new FileOutputStream(file);

                    byte[] buffer = new byte[8 * 1024];
                    int bytes = inputStream.read(buffer);
                    while (bytes >= 0) {
                        outputStream.write(buffer, 0, bytes);
                        bytes = inputStream.read(buffer);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String getPath(@NonNull Uri uri) {
        String name = "";
        Cursor cursor = requireContext().getContentResolver().query(uri, null, null, null, null);
        assert cursor != null;
        cursor.moveToFirst();
        name = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
        cursor.close();

        return name;
    }

    private String getFileExtension(String fileName) {
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        else return "";
    }

    public boolean checkPermission(String permission, int requestCode) {
        if (ContextCompat.checkSelfPermission(requireContext(), permission)
                == PackageManager.PERMISSION_DENIED) {
            // Requesting the permission
            ActivityCompat.requestPermissions(requireActivity(),
                    new String[]{permission},
                    requestCode);
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case CALL_PERMISSION_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(requireContext(), "CALL_PERMISSION_CODE", Toast.LENGTH_SHORT).show();
                }
                break;
            case STORAGE_PERMISSION_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(requireContext(), "STORAGE_PERMISSION_CODE", Toast.LENGTH_SHORT).show();
                }
        }
        if (requestCode == CALL_PERMISSION_CODE) {

        } else if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            }
        }
    }
}


