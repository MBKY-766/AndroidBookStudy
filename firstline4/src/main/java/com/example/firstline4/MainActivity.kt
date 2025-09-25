package com.example.firstline4

import android.os.Bundle
import android.os.Message
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firstline4.adapter.MessageAdapter
import com.example.firstline4.bean.Msg

class MainActivity : AppCompatActivity() {
    private val msgList = ArrayList<Msg>()
    private var adapter: MessageAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView = findViewById<RecyclerView>(R.id.rv_content)
        initMsg()
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        adapter = MessageAdapter(msgList)
        recyclerView.adapter = adapter
    }
    fun initMsg(){
        val msg1 = Msg("Hello guy.", Msg.TYPE_SENT)
        msgList.add(msg1)
        val msg2 = Msg("Hello. Who is that?", Msg.TYPE_SENT)
        msgList.add(msg2)
        val msg3 = Msg("This is Tom. Nice talking to you. ", Msg.TYPE_SENT)
        msgList.add(msg3)
    }
}