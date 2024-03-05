package com.tegar.sedekah.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.tegar.sedekah.R

class BannerAdapter(private val bannerListItem: List<BannerModel>) : RecyclerView.Adapter<BannerViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.banner_item_layout, parent, false)

        return BannerViewHolder(view)
    }


    override fun getItemCount(): Int {
        return bannerListItem.size
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        val bannerItem = bannerListItem[position]
        holder.bind(bannerItem)
    }

}

class BannerViewHolder(private val view: View): RecyclerView.ViewHolder(view){
    val bannerImageView: ImageView = view.findViewById(R.id.banner_image_view)

    fun bind(bannerItem: BannerModel){
        Glide.with(view.context)
            .load(bannerItem.imageUrl)
            .into(bannerImageView)
    }
}