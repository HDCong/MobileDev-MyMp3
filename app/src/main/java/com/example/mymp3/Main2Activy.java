package com.example.mymp3;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.logging.SimpleFormatter;

import de.hdodenhof.circleimageview.CircleImageView;

public class Main2Activy extends AppCompatActivity {

    TextView textViewDuration, textViewCurrentTime, textViewName,textViewSinger2;

    ImageButton imageButtonPlay, imageButtonFast, imageButtonSlow;
    CircleImageView circleImageView;
    Button back;
    ObjectAnimator objectAnimator;
    MediaPlayer mediaPlayer;
    SeekBar seekBar;
    Handler threadHandler = new Handler();
    boolean state = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_playing);
        getSupportActionBar().hide();
        init();
        fillDataByGetIntent();
        TimeSong();
        startPlaySong();
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.start();
            }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(b){
                    mediaPlayer.seekTo(i);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mediaPlayer.isPlaying())
            mediaPlayer.stop();
    }

    private void fillDataByGetIntent(){
        Intent intent = getIntent();
        int id = Integer.parseInt(intent.getStringExtra("imageid"));
        setImageForCircle(id);
        textViewName.setText(intent.getStringExtra("song"));
        textViewSinger2.setText(intent.getStringExtra("singer"));
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        int idSong=this.getRawResIdByName(intent.getStringExtra("resource"));
        this.mediaPlayer = MediaPlayer.create(this,idSong);


    }
    private void startPlaySong() {
        int duration = this.mediaPlayer.getDuration();
        int currentPosition = this.mediaPlayer.getCurrentPosition();
        if(currentPosition== 0)  {
            this.seekBar.setMax(duration);
        } else if(currentPosition== duration)  {
            this.mediaPlayer.reset();
        }
        mediaPlayer.start();
        UpdateSeekBarThread updateSeekBarThread= new UpdateSeekBarThread();
        threadHandler.postDelayed(updateSeekBarThread,50);
    }

    public void slowForward(View view) {
        int cur = mediaPlayer.getCurrentPosition();
        if(cur >5000){
            mediaPlayer.seekTo(cur-5000);
        }
    }

    public void playAndPause(View view) {
        if(state==true){
            imageButtonPlay.setImageResource(R.drawable.play);
            mediaPlayer.pause();
            objectAnimator.pause();
            state= false;
        }
        else{
            imageButtonPlay.setImageResource(R.drawable.pause);
            mediaPlayer.start();
            objectAnimator.resume();
            state =true;
        }
    }

    public void fastForward(View view) {
        int duration = mediaPlayer.getDuration();
        int cur = mediaPlayer.getCurrentPosition();
        if(cur +5000 < duration){
            mediaPlayer.seekTo(cur+5000);
        }
    }

    //
    class UpdateSeekBarThread implements Runnable {

        public void run()  {
            int currentPosition = mediaPlayer.getCurrentPosition();
            String currentPositionStr = createFormatTime(currentPosition);
            textViewCurrentTime.setText(currentPositionStr);
            seekBar.setProgress(currentPosition);
            threadHandler.postDelayed(this, 50);
        }
    }
    private void TimeSong() {
        int duration = mediaPlayer.getDuration();

        textViewDuration.setText(createFormatTime(duration));
        seekBar.setMax(duration);

    }

    private String createFormatTime(int value)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        return simpleDateFormat.format(value);
    }

    private void setImageForCircle(int id) {
        circleImageView = this.findViewById(R.id.circleImageView);
        circleImageView.setImageResource(id);

        objectAnimator = ObjectAnimator.ofFloat(circleImageView,"rotation",0f, 360f);
        objectAnimator.setDuration(10000);
        objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        objectAnimator.setRepeatMode(ValueAnimator.RESTART);
        objectAnimator.setInterpolator(new LinearInterpolator());
        objectAnimator.start();
    }

    private void init() {
        textViewName = this.findViewById(R.id.textViewName);
        textViewSinger2 = this.findViewById(R.id.textViewSingerMain2);
        back = (Button)findViewById(R.id.buttonBack);
        textViewCurrentTime = this.findViewById(R.id.currentDuration);
        textViewDuration = this.findViewById(R.id.songDuration);
        imageButtonFast = this.findViewById(R.id.imageButtonFast);
        imageButtonPlay = this.findViewById(R.id.imageButtonPlay);
        imageButtonSlow= this.findViewById(R.id.imageButtonSlow);
        seekBar = findViewById(R.id.seekbarSong);

    }
    private int getRawResIdByName(String resName)  {
        String pack = this.getPackageName();
        // Return 0 if not found.
        int re = this.getResources().getIdentifier(resName, "raw", pack);
        return re;
    }
}
