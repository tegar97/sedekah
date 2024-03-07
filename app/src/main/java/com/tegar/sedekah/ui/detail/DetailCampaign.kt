package com.tegar.sedekah.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textview.MaterialTextView
import com.tegar.sedekah.R
import com.tegar.sedekah.core.data.Resource
import com.tegar.sedekah.core.domain.model.Campaign
import com.tegar.sedekah.databinding.ActivityDetailCampaignBinding
import com.tegar.sedekah.databinding.BottomSheetBinding
import com.tegar.sedekah.utils.toRupiah
import org.koin.androidx.viewmodel.ext.android.viewModel


class DetailCampaign : AppCompatActivity() {

    companion object {
        const val CAMPAIGN_DATA = "campaign_data"
    }
    private lateinit var binding: ActivityDetailCampaignBinding

    private val detailCampaignViewModel: DetailCampaignViewModel by viewModel()
    private  var idCampaign : Int = 0
    private var isFavorite : Boolean = false
    private lateinit var campaign : Campaign

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailCampaignBinding.inflate(layoutInflater)
        val actionbar = supportActionBar

        actionbar?.setDisplayHomeAsUpEnabled(true)

        setContentView(binding.root)


        val detailCampaign = intent.getParcelableExtra<Campaign>(CAMPAIGN_DATA)
        if (detailCampaign != null) {
            setDetailCampaign(detailCampaign)
            campaign = detailCampaign
            isFavorite = detailCampaign.isFavorite
        }
        val modalBottomSheet = ModalBottomSheet()
        binding.btnDonate.setOnClickListener {
            modalBottomSheet.show(supportFragmentManager, ModalBottomSheet.TAG)
        }







    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_app_bar, menu)
        val favoriteMenuItem = menu?.findItem(R.id.favorite)
        if (isFavorite) {
            favoriteMenuItem?.setIcon(ContextCompat.getDrawable(this,R.drawable.baseline_favorite_24))

        } else {
            favoriteMenuItem?.setIcon(ContextCompat.getDrawable(this,R.drawable.baseline_favorite_border_24))

        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        return if (id == R.id.favorite) {

            if (isFavorite) {
                item.setIcon(ContextCompat.getDrawable(this,R.drawable.baseline_favorite_border_24))
            } else {
                item.setIcon(ContextCompat.getDrawable(this,R.drawable.baseline_favorite_24))
            }
            isFavorite = !isFavorite

            detailCampaignViewModel.setFavoriteCampaign(campaign, isFavorite)
            true
        } else super.onOptionsItemSelected(item)
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
    private fun setDetailCampaign(detailCampaign: Campaign) {
        detailCampaign.let {
            supportActionBar?.title = detailCampaign.judul

            idCampaign = detailCampaign.id.toInt()
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

//            var statusFavorite = detailCampaign.isFavorite
//            setStatusFavorite(statusFavorite)
//            binding.fabFavorite.setOnClickListener {
//                statusFavorite = !statusFavorite ?: false
//                detailCampaignViewModel.setFavoriteCampaign(detailCampaign, statusFavorite)
//                setStatusFavorite(statusFavorite)
//            }

        }

    }
//    private fun setStatusFavorite(statusFavorite: Boolean) {
//        if (statusFavorite) {
//            binding.fabFavorite.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.baseline_favorite_24))
//        } else {
//            binding.fabFavorite.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.baseline_favorite_border_24))
//        }
//    }
}

class ModalBottomSheet : BottomSheetDialogFragment() {
    private lateinit var buttonBottomSheet: Button
    private lateinit var donateAmount: EditText

    private val detailCampaignViewModel: DetailCampaignViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.bottom_sheet, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonBottomSheet = view.findViewById(R.id.btn_donate_now)
        donateAmount = view.findViewById(R.id.input_donate_amount)
        buttonBottomSheet.setOnClickListener {
            detailCampaignViewModel.donate( 2   ,donateAmount.text.toString().toInt()).observe(this) {
                when (it) {
                    is Resource.Success ->  {
                        Log.d("Donate", it.data?.date.toString())

                    }
                    is Resource.Loading ->  {
                        Log.d("Loading ...", it.data?.date.toString())

                    }
                    is Resource.Error ->  {
                        Log.d("Error", it.message.toString())

                    }
                }
            }
            Log.d("Donate" ,donateAmount.text.toString())

        }
    }




    companion object {
        const val TAG = "ModalBottomSheet"
    }
}