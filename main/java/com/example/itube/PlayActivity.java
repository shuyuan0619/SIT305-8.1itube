package com.example.itube;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;


public class PlayActivity extends AppCompatActivity {

    private VideoView video_view_play;
    private MediaController mMediaController;
    private String intent_string_extra;
    
    
    public String[] playlist;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        ActionBar actionBar = getSupportActionBar();
        if(!(actionBar == null)){
            actionBar.hide();
        }
        video_view_play = (VideoView) findViewById(R.id.video);
        mMediaController = new MediaController(this);
        intent_string_extra = getIntent().getStringExtra("URL");
        video_view_play.setVideoURI(Uri.parse(intent_string_extra)); 
        mMediaController.setMediaPlayer(video_view_play);
        video_view_play.setMediaController(mMediaController);
        video_view_play.start();


    }
}