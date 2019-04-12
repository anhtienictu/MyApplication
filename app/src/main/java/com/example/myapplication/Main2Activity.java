package com.example.myapplication;

import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.Annotation;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    TextView tvtbh, tvtgbt , tvtgkt;
    ImageButton btnnext, btnql, btnplay, btnstop;
    ImageView ivhinh;
    SeekBar seebar1;


    ArrayList<song> arraysong;

    int position=0;
    MediaPlayer mediaPlayer;
    Animation  animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        position=getIntent().getIntExtra("pos",0);
        Anhxa();
        addsong();
        khoitao();
        settime();
        updatetime();

        animation=AnimationUtils.loadAnimation(this,R.anim.xoaydia);




        seebar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seebar1.getProgress());
            }
        });


        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position++;
                if (position>arraysong.size() - 1){
                    position=0;
                }

                if (mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                }
                khoitao();
                mediaPlayer.start();
                btnplay.setImageResource(R.drawable.iconpause);
                settime();
                updatetime();
                ivhinh.startAnimation(animation);

            }
        });

        btnql.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position--;
                if (position <0){
                    position=arraysong.size()-1;
                }

                if (mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                }
                khoitao();
                mediaPlayer.start();
                btnplay.setImageResource(R.drawable.iconpause);
                settime();
                updatetime();
                ivhinh.startAnimation(animation);
            }
        });

        btnstop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                btnplay.setImageResource(R.drawable.iconplay);
                khoitao();
            }
        });

        btnplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    btnplay.setImageResource(R.drawable.iconplay);
                } else {
                    mediaPlayer.start();
                    btnplay.setImageResource(R.drawable.iconpause);
                    settime();
                    updatetime();
                    ivhinh.startAnimation(animation);

                }

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mediaPlayer.isPlaying()){
            mediaPlayer.pause();
            btnplay.setImageResource(R.drawable.iconplay);
        } else {
            mediaPlayer.start();
            btnplay.setImageResource(R.drawable.iconpause);
            settime();
            updatetime();
            ivhinh.startAnimation(animation);

        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        mediaPlayer.stop();
        btnplay.setImageResource(R.drawable.iconplay);
        khoitao();
    }

    private void updatetime(){
        final Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat dinhDangGio=new SimpleDateFormat("mm:ss");
                tvtgbt.setText(dinhDangGio.format(mediaPlayer.getCurrentPosition()));
                seebar1.setProgress(mediaPlayer.getCurrentPosition());

                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        position++;
                        if (position>arraysong.size() - 1){
                            position=0;
                        }

                        if (mediaPlayer.isPlaying()){
                            mediaPlayer.stop();
                        }
                        khoitao();
                        mediaPlayer.start();
                        btnplay.setImageResource(R.drawable.iconpause);
                        settime();
                        updatetime();
                    }
                });
                handler.postDelayed(this,500);
            }
        },100);
    }

    private  void  settime(){
        SimpleDateFormat dinhDangGio=new SimpleDateFormat("mm:ss");
        tvtgkt.setText(dinhDangGio.format(mediaPlayer.getDuration()));
        seebar1.setMax(mediaPlayer.getDuration());
    }

    private  void khoitao(){
        mediaPlayer=MediaPlayer.create(Main2Activity.this,arraysong.get(position).getFile());
        tvtbh.setText(arraysong.get(position).getTbh());
    }

    private void addsong() {
        arraysong=new ArrayList<>();
        arraysong.add(new song("Như Có bác hồ", R.raw.nhucobacho));
        arraysong.add(new song("Đúng người đúng thời điểm", R.raw.dungnguoidungthoidiem));
        arraysong.add(new song("Em Đã Thấy Anh Cùng Người Ấy", R.raw.emdathayanhcungnguoiay));
        arraysong.add(new song("Hồng nhân", R.raw.hongnhan));

    }

    private void Anhxa() {
        tvtbh       =(TextView)     findViewById(R.id.tvtbh);
        tvtgbt      =(TextView)     findViewById(R.id.tvtgbt);
        tvtgkt      =(TextView)     findViewById(R.id.tvtgkt);
        btnnext     =(ImageButton)  findViewById(R.id.btnnext);
        btnstop     =(ImageButton)  findViewById(R.id.btnstop);
        btnql       =(ImageButton)  findViewById(R.id.btnql);
        btnplay     =(ImageButton)  findViewById(R.id.btnplay);
        seebar1     =(SeekBar)      findViewById(R.id.seebar1);
        ivhinh      =(ImageView)    findViewById(R.id.ivhinh);

    }
}