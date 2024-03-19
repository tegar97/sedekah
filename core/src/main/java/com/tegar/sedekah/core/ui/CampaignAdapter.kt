package com.tegar.sedekah.core.ui

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tegar.sedekah.core.R
import com.tegar.sedekah.core.databinding.ItemCampaignBinding
import com.tegar.sedekah.core.domain.model.Campaign
import com.tegar.sedekah.core.utils.toRupiah

class CampaignAdapter(private val mode: Mode) : RecyclerView.Adapter<CampaignAdapter.ListViewHolder>() {

    private var listData = ArrayList<Campaign>()
    var onItemClick: ((Campaign) -> Unit)? = null

    enum class Mode {
        FULL,
        HORIZONTAL
    }

    fun setData(newListData: List<Campaign>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val layoutResId =  R.layout.item_campaign
        val view = LayoutInflater.from(parent.context).inflate(layoutResId, parent, false)

        if (mode == Mode.HORIZONTAL) {
            val screenWidth = Resources.getSystem().displayMetrics.widthPixels
            val layoutParams = view.layoutParams
            layoutParams.width = (screenWidth * 0.8).toInt() // Set to 80% of screen width
            view.layoutParams = layoutParams
        }

        return ListViewHolder(view)
    }

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding: ItemCampaignBinding = ItemCampaignBinding.bind(itemView)

        fun bind(data: Campaign) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(data.foto)
                    .into(imageView)
                campaignTitle.text = data.judul
                textFundCollected.text = data.danaTerkumpul.toRupiah()
                val fundCollected = data.danaTerkumpul
                val fundTarget = data.targetDonasi

                val progressPercentage = (fundCollected.toDouble() / fundTarget.toDouble() * 100).toInt()
                binding.progressBar.progress = progressPercentage
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}
