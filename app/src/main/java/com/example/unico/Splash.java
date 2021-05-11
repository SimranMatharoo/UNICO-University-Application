package com.example.unico;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.VideoView;

public class Splash extends AppCompatActivity {
VideoView vv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        getSupportActionBar().hide();

        vv=findViewById(R.id.videoView);

        Uri video=Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.splash1);
        vv.setVideoURI(video);
        vv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
              startActivity(new Intent(Splash.this,MainActivity.class));
              finish();
            }
        });
        vv.start();
    }
}
