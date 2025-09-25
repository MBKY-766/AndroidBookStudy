package com.example.firstline4.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.firstline4.R
import com.example.firstline4.bean.Msg
import com.example.firstline4.holder.MsgViewHolder
import com.example.firstline4.holder.RightViewHolder

class MessageAdapter(val msgList: List<Msg>) : RecyclerView.Adapter<MsgViewHolder>() {

    // ViewHolder 类


    override fun getItemViewType(position: Int): Int {
        val msg = msgList[position]
        return msg.type
    }

    // 1. 创建 ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        if (viewType == Msg.TYPE_SENT) {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.msg_right_item, parent, false)
            RightViewHolder(view)
        } else {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.msg_right_item, parent, false)
            RightViewHolder(view)
        }

    // 2. 绑定数据到 ViewHolder
    override fun onBindViewHolder(holder: MsgViewHolder, position: Int) {
        val msg = msgList[position]
        when (holder) {
            is RightViewHolder -> holder.rightMsg.text = msg.content
        }
    }

    // 3. 返回数据项数量
    override fun getItemCount() = msgList.size

}