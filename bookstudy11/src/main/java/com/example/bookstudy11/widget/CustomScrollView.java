package com.example.bookstudy11.widget;

import android.content.Context;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

import com.example.bookstudy11.util.Utils;

public class CustomScrollView extends ScrollView {
    private float mOffsetX,mOffsetY;//横纵方向上的偏移
    private PointF mLastPos;//上次落点的位置
    private int mInternal;//与边缘线的间距阈值

    public CustomScrollView(Context context){
        this(context,null);
    }
    public CustomScrollView(Context context, AttributeSet attr){
        super(context,attr);
        mInternal = Utils.dip2px(context,3);
    }

    //在拦截触摸事件时触发

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event){
        boolean result;
        //其余动作，包括手指一动、手指松开等等
        if(event.getAction()==MotionEvent.ACTION_DOWN){
            //按下手指
            mOffsetX=0.0F;
            mOffsetY=0.0F;
            mLastPos = new PointF(event.getX(),event.getY());
            result = super.onInterceptTouchEvent(event);
        }else {
            //移动/松开手指
            PointF thisPos = new PointF(event.getX(),event.getY());
            mOffsetX=Math.abs(thisPos.x- mLastPos.x);//x轴偏差
            mOffsetY = Math.abs(thisPos.y- mLastPos.y);//y轴偏差
            mLastPos = thisPos;
            if(mOffsetX<mInternal&&mOffsetY<mInternal){
                //较小的偏移--点击事件，传给子控件，点击广播条
                result = false;
            } else if (mOffsetX<mOffsetY) {
               //偏向于垂直方向滑动,不传给子控件
                result = true;
            }else {
                //偏向于水平方向滑动，传给子控件，滑动广播条
                result = false;
            }
        }
        return result;
    }
}
