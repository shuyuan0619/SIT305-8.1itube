package com.example.itube;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.ContentValues;
import android.widget.EditText;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Toast;

import java.util.UUID;

public class HomeActivity extends AppCompatActivity {

    public EditText edit_text_video_url;
    public Button button_play_video;
    public Button btn_addToList;
    public Button play_list_videos;
    public String username;
    private DatabaseHelper dbHelper;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        ActionBar actionBar = getSupportActionBar();
        if(!(actionBar == null)){
            actionBar.hide();
        }
        edit_text_video_url = findViewById(R.id.edit_text_video_url);
        button_play_video = findViewById(R.id.button_play_video);
        btn_addToList = findViewById(R.id.btn_addToList);
        play_list_videos = findViewById(R.id.play_list_videos);
        username = getIntent().getStringExtra("username");
        dbHelper = new DatabaseHelper(this,"LocalDatabase.db",null,3);
        button_play_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(HomeActivity.this,PlayActivity.class);
                i.putExtra("URL",edit_text_video_url.getText().toString());
                startActivity(i);
            }
        });

        btn_addToList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                contentValues.put("u_id", UUID.randomUUID().toString());
                contentValues.put("username",username);
                contentValues.put("url",edit_text_video_url.getText().toString());
                db.insert("PlayList",null,contentValues);
                Toast.makeText(HomeActivity.this, "Add successfully", Toast.LENGTH_SHORT).show();
            }
        });

        play_list_videos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this,PlayListActivity.class);
                i.putExtra("username",username);
                startActivity(i);
            }
        });

    }
}