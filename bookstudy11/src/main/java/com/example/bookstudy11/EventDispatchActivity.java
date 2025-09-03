package com.example.bookstudy11;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.bookstudy11.util.DateUtil;
import com.example.bookstudy11.widget.NotDispatchLayout;

public class EventDispatchActivity extends AppCompatActivity implements NotDispatchLayout.NotDispatchListener {

    private TextView tv_dispatch_yes;//声明一个文本视图对象
    private TextView tv_dispatch_no;//声明一个文本视图对象
    private String desc_yes = "",desc_no = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_dispatch);
        tv_dispatch_yes = findViewById(R.id.tv_dispatch_yes);
        tv_dispatch_no = findViewById(R.id.tv_dispatch_no);
        NotDispatchLayout ndl_no = findViewById(R.id.ndl_no);
        ndl_no.setNotDispatchListener(this);
        findViewById(R.id.btn_dispatch_yes).setOnClickListener(v -> {
            desc_yes = String.format("%s%s 您点击了按钮\n", desc_yes, DateUtil.getNowTime());
            tv_dispatch_yes.setText(desc_yes);
        });
        findViewById(R.id.btn_dispatch_no).setOnClickListener(v -> {
            desc_no = String.format("%s%s 您点击了按钮\n", desc_no, DateUtil.getNowTime());
            tv_dispatch_no.setText(desc_no);
        });
    }

    // 在分发触摸事件时触发
    @Override
    public void onNotDispatch() {
        desc_no = String.format("%s%s 触摸动作未分发，按钮点击不了了\n"
                , desc_no, DateUtil.getNowTime());
        tv_dispatch_no.setText(desc_no);
    }
}