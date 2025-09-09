package com.example.bookstudy12.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class GifView extends View {

    private Movie mMovie; //全局电影对象
    private float mScaleRadio = 1;//缩放比率
    private long mBeginTime = 0;//开始播放时间

    public GifView(Context context) {
        super(context);
    }

    public GifView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    //设置电影对象
    public void setMovie(Movie movie) {
        mMovie = movie;
        requestLayout();//请求重新调整视图位置
    }

    //计算缩放比率
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (mMovie != null) {
            int width = mMovie.width();//获取电影动图的宽度
            int height = mMovie.height();//获取电影动图的高度
            float widthRadio = 1.0f * getMeasuredWidth() / width;
            float heightRadio = 1.0f * getMeasuredHeight() / height;
            mScaleRadio = Math.min(widthRadio, heightRadio);
        }
    }

    //绘制该图形
    public void onDraw(Canvas canvas) {
        long now = SystemClock.uptimeMillis();
        if (mBeginTime == 0) {
            //第一帧，记录起始时间
            mBeginTime = now;
        }
        if (mMovie != null) {
            //获取电影动图的播放时长
            int duration = mMovie.duration() == 0 ? 1000 : mMovie.duration();
            //计算当前要显示第几帧图画
            int currentTime = (int) ((now - mBeginTime) % duration);
            mMovie.setTime(currentTime);//设置当前帧的相对时间
            canvas.scale(mScaleRadio,mScaleRadio);//将画布缩放到指定比率
            mMovie.draw(canvas,0,0);//把当前帧绘制到画布上
            postInvalidate();//立即刷新视图(线程安全方式)
        }
    }

}
