package com.veryworks.android.soundplayer;

import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.veryworks.android.soundplayer.domain.Sound;

import java.util.List;

public class PlayerActivity extends AppCompatActivity {
    // 음악 데이터
    List<Sound> datas;
    // 뷰페이저
    ViewPager viewPager;
    PlayerAdapter adapter;
    // 위젯
    ImageButton btnRew, btnPlay, btnFf;
    SeekBar seekBar;
    TextView txtDuration,txtCurrent;
    // 리스트 타입
    String list_type = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        // 볼륨 조절 버튼으로 미디어 음량만 조절하기 위한 설정
        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        seekBar = (SeekBar) findViewById(R.id.seekBar);
        // seekBar 의 변경사항을 체크하는 리스너 등록
        //seekBar.setOnSeekBarChangeListener(seekBarChangeListener);

        txtDuration = (TextView) findViewById(R.id.txtDuration);
        txtCurrent = (TextView) findViewById(R.id.txtCurrent);

        btnRew = (ImageButton) findViewById(R.id.btnRew);
        btnPlay = (ImageButton) findViewById(R.id.btnPlay);
        btnFf = (ImageButton) findViewById(R.id.btnFf);

        btnRew.setOnClickListener(clickListener);
        btnPlay.setOnClickListener(clickListener);
        btnFf.setOnClickListener(clickListener);

        // 0. 데이터 가져오기
        datas = DataLoader.getSounds(this);

        // 1. 뷰페이저 가져오기
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        // 2. 뷰페이저용 아답터 생성
        adapter = new PlayerAdapter(datas ,this);
        // 3. 뷰페이저 아답터 연결
        viewPager.setAdapter( adapter );
        // 4. 뷰페이지 리스너 연결
        //viewPager.addOnPageChangeListener(viewPagerListener);

        // 5. 특정 페이지 호출
        Intent intent = getIntent();
        if(intent != null){
            Bundle bundle = intent.getExtras();
            list_type = bundle.getString( ListFragment.ARG_LIST_TYPE );
            int position = bundle.getInt( ListFragment.ARG_POSITION );
            // 첫페이지일 경우만 init 호출
            // 이유 : 첫페이지가 아닐경우 위의 setCurrentItem 에 의해서 ViewPager의 onPageSelected가 호출된다.
            if(position == 0) {
                init();
            } else {
                // 0 페이지가 아닐경우 해당페이지로 이동한다. 이동후 listener 에서 init 이 자동으로 호출된다.
                viewPager.setCurrentItem(position);
            }
        }
    }

    // 컨트롤러 정보 초기화
    private void init(){

    }

    private void playerInit(){

    }

    private void controllerInit(){

    }

    private void play() {

    }

    private void playStop(){

    }

    private void playPlay(){

    }

    private void playPause(){

    }

    private void prev() {

    }

    private void next() {

    }

    // 버튼 클릭 리스너
    View.OnClickListener clickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btnPlay:
                    play();
                    break;
                case R.id.btnRew:
                    prev();
                    break;
                case R.id.btnFf:
                    next();
                    break;
            }
        }
    };
}


