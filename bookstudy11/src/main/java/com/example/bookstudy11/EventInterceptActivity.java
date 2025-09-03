package com.example.bookstudy11;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.bookstudy11.util.DateUtil;
import com.example.bookstudy11.widget.InterceptLayout;

public class EventInterceptActivity extends AppCompatActivity implements InterceptLayout.InterceptListener {
    private TextView tv_intercept_no; // 声明一个文本视图对象
    private TextView tv_intercept_yes; // 声明一个文本视图对象
    private String desc_yes = "",desc_no = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_intercept);
        tv_intercept_no = findViewById(R.id.tv_intercept_no);
        tv_intercept_yes = findViewById(R.id.tv_intercept_yes);
        InterceptLayout il_yes = findViewById(R.id.il_yes);
        //设置拦截布局的事件拦截监听器
        il_yes.setInterceptListener(this);
        findViewById(R.id.btn_intercept_no).setOnClickListener(v->{
            desc_no = String.format("%s%s 您点击了按钮\n",desc_no, DateUtil.getNowDateTime());
            tv_intercept_no.setText(desc_no);
        });
        findViewById(R.id.btn_intercept_yes).setOnClickListener(v->{
            desc_yes = String.format("%s%s 您点击了按钮\n",desc_yes,DateUtil.getNowTime());
            tv_intercept_yes.setText(desc_yes);
        });

    }

    @Override
    public void onIntercept() {
        //拦截触摸事件时触发
        desc_yes = String.format("%s%s 触摸动作被拦截，按钮点击不了\n",desc_yes,DateUtil.getNowTime());
        tv_intercept_yes.setText(desc_yes);
    }
}