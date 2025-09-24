package com.example.firstline3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.jvm.java

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.btn1).setOnClickListener {
            intent = Intent(this, FirstActivity::class.java)
            startActivity(intent)
        }
        findViewById<Button>(R.id.btn2).setOnClickListener {
            intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }

    }
}