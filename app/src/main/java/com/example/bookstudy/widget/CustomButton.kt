package com.example.bookstudy.widget

import android.content.Context
import android.util.AttributeSet
import com.example.bookstudy.R

class CustomButton : androidx.appcompat.widget.AppCompatButton {
    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : this(
        context,
        attrs,
        R.attr.customButtonStyle
    )

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

}