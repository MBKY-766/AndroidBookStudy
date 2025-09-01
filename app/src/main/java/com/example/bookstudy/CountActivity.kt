package com.example.bookstudy

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CountActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var btn_count: Button
    private lateinit var tv_result: TextView
    private var isStarted: Boolean = false
    private var mHandler: Handler = Handler(Looper.getMainLooper())
    private var mCount: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_count)
        tv_result = findViewById(R.id.tv_result)
        btn_count = findViewById(R.id.btn_count)
        btn_count.setOnClickListener(this)


    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_count -> {
                if (!isStarted){
                    //开启计数
                    btn_count.text = "停止计数"
                    mHandler.post(mCounter)

                }else {
                    btn_count.text = "开始计数"
                    mHandler.removeCallbacks(mCounter)
                }
                isStarted=!isStarted
            }
        }
    }
    //定义一个计数任务
    private val mCounter : Runnable = object: Runnable{
        override fun run() {
            mCount++
            tv_result.text = mCount.toString()
            mHandler.postDelayed(this, 1000)
        }

    }


}