package com.example.bookstudy12;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class FadeAnimActivity extends AppCompatActivity {

    private ImageView iv_fade_anim;//声明一个图像视图
    private TransitionDrawable td_fade;
    private Handler mHandler = new Handler();
    private static final int DURATION = 3000;//单次过渡时长(毫秒)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fade_anim);
        iv_fade_anim = findViewById(R.id.iv_fade_anim);
        showFadeAnimation();
    }

    //播放淡入淡出动画
    private void showFadeAnimation() {
        //淡入淡出动画需要先定义一个图形资源数组，用于变换图片
        Drawable[] drawableArray = {getDrawable(R.drawable.fade_begin), getDrawable(R.drawable.fade_end)};
        //创建一个用于淡入淡出动画的过渡图形
        td_fade = new TransitionDrawable(drawableArray);
        td_fade.setCrossFadeEnabled(true);//是否启用交叉淡入，启用后淡入效果更柔和
        td_fade.startTransition(3000);//开始时长为3秒的过渡转换
        //将过渡图形赋值给图像视图用于显示
        iv_fade_anim.setImageDrawable(td_fade);
        //启动第一次正向过渡
        startForward();

    }
    private void startForward(){
        td_fade.startTransition(DURATION);
        mHandler.postDelayed(this::startReverse,DURATION);

    }

    private void startReverse() {
        td_fade.reverseTransition(DURATION);
        mHandler.postDelayed(this::startForward,DURATION);
    }

    @Override
    protected  void onDestroy(){
        super.onDestroy();
        //防止内存泄漏
        mHandler.removeCallbacksAndMessages(null);
    }

}