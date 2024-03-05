package com.tegar.sedekah.ui.adapter

import android.view.View
import androidx.core.view.marginRight
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2

class ViewPagerMarginPageTransformer(private val marginOffset: Int) : ViewPager2.PageTransformer {

    override fun transformPage(view: View, position: Float) {
        val offset = position * marginOffset
        view.translationX = -offset


    }
}