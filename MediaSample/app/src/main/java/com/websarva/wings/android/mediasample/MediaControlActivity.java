package com.websarva.wings.android.mediasample;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class MediaControlActivity extends AppCompatActivity {

    private MediaPlayer _player;
    private Button _btPlay;
    private Button _btBack;
    private Button _btForward;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_control);

        _btPlay = findViewById(R.id.btPlay);
        _btBack = findViewById(R.id.btBack);
        _btForward = findViewById(R.id.btForward);

        _player = new MediaPlayer();
        String mediaFileUriStr = "android.resource://" + getPackageName() + "/" + R.raw.mountain_stream;
        Uri mediaFileUri = Uri.parse(mediaFileUriStr);
        try {
            _player.setDataSource(MediaControlActivity.this, mediaFileUri);
            // Set Listener when prepare for media player finished
            _player.setOnPreparedListener(new PlayerPreparedListener());
            // Set Listener when media play finish
            _player.setOnCompletionListener(new PlayerCompletionListener());
            // Prepare media play
            _player.prepareAsync();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(_player.isPlaying()) {
            _player.stop();
        }

        _player.release();
        _player = null;
    }

    private class PlayerPreparedListener implements MediaPlayer.OnPreparedListener {

        @Override
        public void onPrepared(MediaPlayer mp) {
            _btPlay.setEnabled(true);
            _btBack.setEnabled(true);
            _btForward.setEnabled(true);
        }
    }

    private class PlayerCompletionListener implements MediaPlayer.OnCompletionListener {

        @Override
        public void onCompletion(MediaPlayer mp) {
            _btPlay.setText(R.string.bt_play_play);
        }
    }

    public void onPlayButtonClick(View view) {
        if(_player.isPlaying()) {
            _player.pause();
            _btPlay.setText(R.string.bt_play_play);
        }
        else {
            _player.start();
            _btPlay.setText(R.string.bt_play_pause);
        }
    }
}
