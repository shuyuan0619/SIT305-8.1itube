package com.example.itube;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

public class SignUpActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    public EditText edit_full_name_login;
    public EditText edit_text_user_name_login;
    public EditText edit_text_pw_login;
    public EditText edit_confirm_password_login;
    public Button button_cAccount;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        ActionBar actionBar = getSupportActionBar();
        if(!(actionBar == null)){
            actionBar.hide();
        }



        edit_full_name_login = findViewById(R.id.edit_full_name_login);
        edit_text_user_name_login = findViewById(R.id.edit_text_user_name_login);
        edit_text_pw_login = findViewById(R.id.edit_text_pw_login);
        edit_confirm_password_login = findViewById(R.id.edit_confirm_password_login);
        button_cAccount = findViewById(R.id.button_cAccount);


        dbHelper = new DatabaseHelper(this,"LocalDatabase.db",null,3);

        button_cAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!edit_text_pw_login.getText().toString().equals(edit_confirm_password_login.getText().toString())){
                    Toast.makeText(SignUpActivity.this,"password not match!",Toast.LENGTH_SHORT).show();
                    return;
                }
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                contentValues.put("u_id", UUID.randomUUID().toString());
                contentValues.put("fullName",edit_full_name_login.getText().toString());
                contentValues.put("username",edit_text_user_name_login.getText().toString());
                contentValues.put("password",edit_text_pw_login.getText().toString());
                db.insert("Person",null,contentValues);
                Intent i = new Intent(SignUpActivity.this,MainActivity.class);
                startActivity(i);
            }
        });

    }




}