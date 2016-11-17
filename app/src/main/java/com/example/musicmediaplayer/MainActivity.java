package com.example.musicmediaplayer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

    Player player;
    Button btn;
    SeekBar seekBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.player);
        seekBar = (SeekBar) findViewById(R.id.seek_bar);
        player = new Player(seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBarChange());
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
                        player.playUrl("http://abv.cn/music/光辉岁月.mp3");
//                    }
//                }).start();
            }
        });
    }

    class SeekBarChange implements SeekBar.OnSeekBarChangeListener{
        int progress;
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            this.progress = progress * player.mediaPlayer.getDuration() / seekBar.getMax();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            player.mediaPlayer.seekTo(progress);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (player != null) {
            player.stop();
            player = null;
        }
    }
}
