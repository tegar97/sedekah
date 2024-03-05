package com.tegar.sedekah.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.tegar.sedekah.R
import com.tegar.sedekah.core.domain.model.Campaign
import com.tegar.sedekah.databinding.ActivityDetailCampaignBinding
import com.tegar.sedekah.utils.toRupiah
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.NumberFormat
import java.util.Locale

class DetailCampaign : AppCompatActivity() {

    companion object {
        const val CAMPAIGN_DATA = "campaign_data    "
    }
    private lateinit var binding: ActivityDetailCampaignBinding
    private val detailCampaignViewModel: DetailCampaignViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailCampaignBinding.inflate(layoutInflater)
        val actionbar = supportActionBar

        actionbar?.setDisplayHomeAsUpEnabled(true)

        setContentView(binding.root)
        val detailCampaign = intent.getParcelableExtra<Campaign>(CAMPAIGN_DATA)
        if (detailCampaign != null) {
            setDetailCampaign(detailCampaign)
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
    private fun setDetailCampaign(detailCampaign: Campaign) {
        detailCampaign.let {
            supportActionBar?.title = detailCampaign.judul


            binding.fundTarget.text = detailCampaign.targetDonasi.toRupiah()
            binding.campaignDescription.text = detailCampaign.deskripsi
            binding.campaignFundCollected.text = detailCampaign.danaTerkumpul?.toRupiah()

            val fundCollected = detailCampaign.danaTerkumpul ?: 0
            val fundTarget = detailCampaign.targetDonasi ?: 0

            val progressPercentage = (fundCollected.toDouble() / fundTarget.toDouble() * 100).toInt()
            binding.campaignFundCollected.text = fundCollected.toRupiah()

            binding.campaignProgressBar.progress = progressPercentage


            Glide.with(this@DetailCampaign)
                .load(detailCampaign.foto)
                .into(binding.campaignImage)

            var statusFavorite = detailCampaign.isFavorite
            setStatusFavorite(statusFavorite)
            binding.fabFavorite.setOnClickListener {
                statusFavorite = !statusFavorite ?: false
                detailCampaignViewModel.setFavoriteCampaign(detailCampaign, statusFavorite)
                setStatusFavorite(statusFavorite)
            }

        }

    }
    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fabFavorite.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.baseline_favorite_24))
        } else {
            binding.fabFavorite.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.baseline_favorite_border_24))
        }
    }
}