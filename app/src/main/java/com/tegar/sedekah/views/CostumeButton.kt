package com.tegar.sedekah.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.Gravity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import com.tegar.sedekah.R

class CostumeButton : AppCompatButton {
    private lateinit var enabledBackground: Drawable
    private lateinit var disabledBackground: Drawable
    private var txtColor: Int = 0

    constructor(context: Context) : super(context) {
        init()

    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()

    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()


    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        background = if (isEnabled) enabledBackground else disabledBackground
        setTextColor(txtColor)
        gravity = Gravity.CENTER
        elevation = 0f
    }

    private fun init() {
        txtColor = ContextCompat.getColor(context, android.R.color.background_light)
        enabledBackground =
            ContextCompat.getDrawable(context, R.drawable.primary_button) as Drawable
        disabledBackground =
            ContextCompat.getDrawable(context, R.drawable.disabled_button) as Drawable
    }


}