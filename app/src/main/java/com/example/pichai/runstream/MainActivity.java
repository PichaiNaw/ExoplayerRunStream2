package com.example.pichai.runstream;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.util.Util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.TimeUnit;


public class MainActivity extends Activity {

    private SimpleExoPlayerView mPlayerView;
    private SimpleExoPlayer player;
    private Timeline.Window window;
    private DataSource.Factory mediaDataSourceFactory;
    private DefaultTrackSelector trackSelector;
    private boolean shouldAutoPlay;
    private BandwidthMeter bandwidthMeter;
    private ImageView ivHideControllerButton;
    private int currentWindow;
    private long playbackPosition;
    Button btn;
    int a = 0;
    int b = 1;
    int c,x;
    int y,z,p;
    Handler handler = new Handler();
    String[] num={"http://96.30.124.232:1935/EDGE_T1/smil:5HD_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T3/smil:NBT_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T1/smil:TPBS_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T1/smil:3Family_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T3/smil:Mcot_Fam_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T1/smil:TNN24_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T1/smil:NEW_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T1/smil:Springnews_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T1/smil:BRIGHT_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T1/smil:Nation_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T1/smil:Workpoint_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T1/smil:True4U_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T1/smil:Now_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T1/smil:CH8_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T1/smil:3SD_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T1/smil:Mono_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T3/smil:McotHD_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T1/smil:OneHD_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T4/smil:ThairathHD_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T1/smil:3HD_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T1/smil:AmarinHD_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T1/smil:7HD_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T1/smil:PPTV_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T2/smil:MVTV_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T2/smil:Mix_major_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T2/smil:MVLAO_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T3/smil:EsanTV_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T3/smil:five_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T2/smil:Asian_Mix_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T3/smil:Muancheon_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T2/smil:TVD3_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T5/smil:Wish68_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T3/smil:Ch6_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T3/smil:DoChannel_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T5/smil:DooNee_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T5/smil:DTV_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T3/smil:Happy_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T5/smil:BungDeeTV_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T5/smil:GoodTV_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T2/smil:Cartoon_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T2/smil:CNS_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T2/smil:Dragon_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T2/smil:DTF_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T2/smil:FONG_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T2/smil:HORROR_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T2/smil:HORROR_Major_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T2/smil:INM_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T2/smil:MHONG_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T2/smil:NonStop_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T2/smil:Oscar_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T2/smil:Parata_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T2/smil:SEOUL_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T2/smil:TVBL_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T2/smil:WSM_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T3/smil:AtTV_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T3/smil:CH2_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T3/smil:DaraDaily_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T3/smil:Mangorn_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T3/smil:Money_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T3/smil:NhangDunk_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T3/smil:PloenTV_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T3/smil:PlookPanya_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T3/smil:S_Channel_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T3/smil:Sabaidee_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T4/smil:40_50_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T4/smil:A_film_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T4/smil:Ani_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T4/smil:BOOMERANG_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T4/smil:CARTOON_CLUB_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T4/smil:CoolChannel_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T4/smil:FanTV_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T4/smil:I_Asia_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T4/smil:JaTingJa_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T4/smil:JJ_Channel_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T4/smil:JKN_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T4/smil:M_Channel_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T4/smil:Mono_plus_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T4/smil:MovieSat_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T4/smil:MovieSat_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T4/smil:PsiHD_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T4/smil:SmartMovie_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T4/smil:TimeTV_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T4/smil:UmmTV_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T4/smil:ZeeNung_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T5/smil:13SiamThai_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T5/smil:CatHD_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T5/smil:CHOKDEE_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T5/smil:France24_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T5/smil:INTV_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T8/smil:News1_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T5/smil:STOU_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T5/smil:Suwannaphumi_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T5/smil:WBTV_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T3/smil:Aktion_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T3/smil:SMMTV_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T3/smil:Thaiban_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T3/smil:YouChannel_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T5/smil:MeTV_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T5/smil:Chic_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T7/smil:MITV_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T7/smil:MVM_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T7/smil:MWD_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T7/smil:MWD_Doc_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T7/smil:MWD_Movies_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T7/smil:MWD_Series_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T7/smil:MWD_Variety_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T7/smil:NRC_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T7/smil:MWD_Shop_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T7/smil:MWD_Music_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T6/smil:CCTV2_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T6/smil:CCTV7_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T6/smil:CCTV10_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T6/smil:CCTV11_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T6/smil:CCTV12_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T6/smil:CCTV13_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T6/smil:CCTV14_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T6/smil:CCTV15_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T6/smil:CGTN_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T6/smil:CGTN_Doc_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T6/smil:Channel_NewsAsia_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T8/smil:DW_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T8/smil:TV5_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T8/smil:Russia_Today_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T8/smil:SunTV_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T7/smil:VTV1_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T7/smil:VTV4_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T7/smil:VTV5_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T7/smil:VTV7_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T7/smil:VTV8_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T6/smil:Aljazeera_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T6/smil:NHK_World_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T6/smil:TRT_World_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T6/smil:Phoenix_InfoNews_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T6/smil:Phoenix_Chinese_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T6/smil:Phoenix_HK_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T8/smil:TV3_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T8/smil:ChannelV_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T8/smil:Xing_Kong_ABR.smil/playlist.m3u8","http://96.30.124.232:1935/EDGE_T8/smil:HKS_ABR.smil/playlist.m3u8"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = this.getIntent ( );
        int ft = intent.getIntExtra ("St",x);
        int fs = intent.getIntExtra ("BackM",z);
        y = ft;
        p = fs;
        btn = (Button)findViewById(R.id.button);
        shouldAutoPlay = true;
        bandwidthMeter = new DefaultBandwidthMeter();
        mediaDataSourceFactory = new DefaultDataSourceFactory(this,
                Util.getUserAgent(this, "mediaPlayerSample"), (TransferListener<? super DataSource>) bandwidthMeter);
        window = new Timeline.Window();
        // Permission StrictMode

        checkstream();

    }


    public void checkstream(){
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        HttpURLConnection conn = null;
        try {

            Log.d("checkstream  :", "Check "+num[a]);
            conn = (HttpURLConnection) new URL(num[a]).openConnection();
            int resultServer = conn.getResponseCode();
            if (resultServer == HttpURLConnection.HTTP_OK) {
                Log.d("checkstream  :", "Connect");
                Log.d("checkstream  :", String.valueOf(a));
                runStream();
                CountDown();
            } else {
                Log.d("checkstream  :", "Error Code");
                changeAc();
            }
        } catch (IOException e) {
            Log.d("checkstream  :", String.valueOf(e));
            changeAc();
        }
    }

    public void CountDown() {

        CountDownTimer Count = new CountDownTimer(20000, 1000) {
            public void onTick(long millisUntilFinished) {

                long str = millisUntilFinished / 1000;
                Log.d("checkstream  :", String.valueOf(str));
            }
            public void onFinish() {
                a++;
                b++;
                c = a;
                if (a > num.length - 1) {
                    a = 0;
                }
                if (b == num.length + 1) {
                    b = 1;
                }
                player.stop();
                checkstream();
            }
        };
        Count.start();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        float currentvolume = player.getVolume();
        Log.d("debug", "we are here");
        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_CENTER:
                player.stop();
                Log.d("OnKey", "Key_Center");
                Log.d("NEW A", String.valueOf(c));
                Intent intent = new Intent(MainActivity.this, ManualActivity.class);
                intent.putExtra("MyA", c);
                startActivity(intent);
                System.exit(0);
                break;
            case KeyEvent.KEYCODE_BACK:
                System.exit(0);
                break;
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                player.setVolume((float) (currentvolume-0.1));
                if (currentvolume<0)
                    currentvolume = 0 ;
                Log.d("OnKey", String.valueOf(player.getVolume()));
                break;
            case KeyEvent.KEYCODE_VOLUME_UP:
                player.setVolume((float) (currentvolume+0.1));
                if (currentvolume>=1)
                    currentvolume = 1 ;
                Log.d("OnKey", String.valueOf(player.getVolume()));
                break;
            case KeyEvent.KEYCODE_VOLUME_MUTE:
                player.setVolume(0f);
                Log.d("OnKey", String.valueOf(player.getVolume()));
                break;
            default:    Log.d("OnKey", "No!!");
            return true;
        }
        return false;
    }



    public void changeAc(){
        Intent intent = new Intent(MainActivity.this, ManualActivity.class);
        intent.putExtra("MyA", c);
        intent = intent = intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        player.release();
        finish();
        Log.d("changeActivity", "Manual Mode");
    }

    public void runStream(){
        if (p == 1) {
            a = y;
            b = y + 1;
            Log.d("checkstream  :", "Comeback");
            p = 0;
        }
        String numb = Integer.toString(b);
        mPlayerView = findViewById(R.id.player_view);
        mPlayerView.requestFocus();

        TrackSelection.Factory videoTrackSelectionFactory =
                new AdaptiveTrackSelection.Factory(bandwidthMeter);

        trackSelector = new DefaultTrackSelector(videoTrackSelectionFactory);

        player = ExoPlayerFactory.newSimpleInstance(this, trackSelector);

        mPlayerView.setPlayer(player);

        player.setPlayWhenReady(shouldAutoPlay);
        Handler mainHandler = null;
        MediaSource mediaSource = new HlsMediaSource(Uri.parse(num[a]),
                mediaDataSourceFactory, mainHandler, null);

        boolean haveStartPosition = currentWindow != C.INDEX_UNSET;
        if (haveStartPosition) {
            player.seekTo(currentWindow, playbackPosition);
        }
        player.prepare(mediaSource, !haveStartPosition, false);
        btn.setText(numb + "/"+num.length);
    }

}
