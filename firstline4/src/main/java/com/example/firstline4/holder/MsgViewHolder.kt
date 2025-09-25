package com.example.firstline4.holder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.firstline4.R

//密封类
sealed class MsgViewHolder(view: View) : RecyclerView.ViewHolder(view)
class RightViewHolder(view: View) : MsgViewHolder(view) {
    val rightMsg: TextView = view.findViewById(R.id.rightMsg)
}
