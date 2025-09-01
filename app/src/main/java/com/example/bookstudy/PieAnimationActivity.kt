package com.example.bookstudy

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bookstudy.widget.PieAnimation

class PieAnimationActivity : AppCompatActivity() {
    private lateinit var paCircle: PieAnimation
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pie_animation)
        paCircle = findViewById(R.id.pa_circle)
        paCircle.start()
    }
}