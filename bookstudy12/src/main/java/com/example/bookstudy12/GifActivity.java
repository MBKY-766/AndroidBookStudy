package com.example.bookstudy12;

import android.graphics.ImageDecoder;
import android.graphics.Movie;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.bookstudy12.util.GifImage;
import com.example.bookstudy12.widget.GifView;

import java.io.IOException;
import java.io.InputStream;

public class GifActivity extends AppCompatActivity {
    private final static String TAG = "GifActivity";
    private TextView tv_info; // 声明一个文本视图对象
    private ImageView iv_gif; // 声明一个图像视图对象
    private GifView gv_gif; // 声明一个动图视图对象

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gif);
//        tv_info = findViewById(R.id.tv_info);
        iv_gif = findViewById(R.id.iv_gif);
//        gv_gif = findViewById(R.id.gv_gif);
//        showGifMovie(R.drawable.kittygif);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            showAnimateDrawable(R.drawable.kittygif);
        }
//        showGifAnimationOld(R.drawable.gif01);
//        initTypeSpinner();//初始化图像类型下拉框

    }

    //    //初始化图像类型下拉框
//    private void initTypeSpinner(){
//
//    }
    //通过movie类播放动画
    private void showGifMovie(int imageId) {
        //从资源图片中解码得到电影对象
        Movie movie = Movie.decodeStream(getResources().openRawResource(imageId));
        gv_gif.setMovie(movie);//设置电影对象
    }

    //利用ImageDecoder结合动画图形播放动画--最简单
    @RequiresApi(api = Build.VERSION_CODES.P)
    private void showAnimateDrawable(int imageId) {
        try {
            ImageDecoder.Source source = ImageDecoder.createSource(getResources(),imageId);
            //从数据源中解码得到图像数据
            Drawable drawable = ImageDecoder.decodeDrawable(source);
            iv_gif.setImageDrawable(drawable);
            if(drawable instanceof Animatable){
                ((Animatable)iv_gif.getDrawable()).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //显示指定来源的图像
    @RequiresApi(api = Build.VERSION_CODES.P)
    private void showImageSource(ImageDecoder.Source source) throws IOException {
        //从数据源解码得到图形信息
        Drawable drawable = ImageDecoder.decodeDrawable(source, (decoder, info, source1) -> {
            //获得图像信息的媒体类型与是否动图
            String desc = String.format("该图片类型为%s,它%s动图", "image/avif", info.isAnimated() ? "是" : "不是");
            tv_info.setText((desc));
        });
        iv_gif.setImageDrawable(drawable);//设置图像视图的图形对象
        if (drawable instanceof Animatable) {
            //如果是动画图形，则开始播放动画
            ((Animatable) iv_gif.getDrawable()).start();
        }
    }


    private void showGifAnimationOld(int imageId) {
        //从资源文件中获取Gif动图
        InputStream is = getResources().openRawResource(imageId);
        //创建一个Gif图像对象
        GifImage gifImage = new GifImage();
        //从输入流中读取gif数据
        int code = gifImage.read(is);
        if (code == GifImage.STATUS_OK) {
            //读取成功
            GifImage.GifFrame[] frameList = gifImage.getFrames();
            //创建一个帧动画
            AnimationDrawable ad_gif = new AnimationDrawable();
            for (GifImage.GifFrame frame : frameList) {
                //把Bitmap位图对象转换为Drawable图形格式
                BitmapDrawable drawable = new BitmapDrawable(getResources(), frame.image);
                //给帧动画添加指定图形，以及该帧的播放延迟
                ad_gif.addFrame(drawable, frame.delay);
            }
            //设置帧动画是否只播放一次，false表示循环播放
            ad_gif.setOneShot(false);
            iv_gif.setImageDrawable(ad_gif);
            //开始播放帧动画
            ad_gif.start();
        } else if (code == GifImage.STATUS_FORMAT_ERROR) {
            Toast.makeText(this, "该图片不是gif格式", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "gif图片读取失败:" + code, Toast.LENGTH_LONG).show();
        }
    }

}