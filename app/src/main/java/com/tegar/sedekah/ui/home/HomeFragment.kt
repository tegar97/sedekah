package com.tegar.sedekah.ui.home

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.tegar.sedekah.R
import com.tegar.sedekah.core.data.Resource
import com.tegar.sedekah.core.data.local.dummy.bannerList
import com.tegar.sedekah.core.domain.model.Campaign
import com.tegar.sedekah.core.ui.CampaignAdapter
import com.tegar.sedekah.databinding.FragmentHomeBinding
import com.tegar.sedekah.ui.adapter.BannerAdapter
import com.tegar.sedekah.ui.detail.DetailCampaign
import org.koin.androidx.viewmodel.ext.android.activityViewModel
import kotlin.math.abs


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val homeViewModel: HomeViewModel by activityViewModel<HomeViewModel>()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var handler : Handler

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        init()
        setupBannerViewPager()
        setupCampaignRecyclerView()
        observeCampaign()










    }

    private fun setupBannerViewPager() {
        binding.bannerViewPager.adapter = BannerAdapter(bannerList)
        binding.bannerViewPager.offscreenPageLimit = 3
        binding.bannerViewPager.clipToPadding = false
        binding.bannerViewPager.clipChildren = false
        binding.bannerViewPager.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        binding.bannerViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                handler.removeCallbacks(runnable)
                handler.postDelayed(runnable, 2000)
            }
        })
        setUpTransformer()
    }

    private val runnable = Runnable {
        binding.bannerViewPager.currentItem = binding.bannerViewPager.currentItem + 1
    }
    private fun init() {
        handler = Handler(Looper.myLooper()!!)





    }

    private fun setupCampaignRecyclerView() {
        with(binding.rvCampaign) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = CampaignAdapter(CampaignAdapter.Mode.HORIZONTAL).apply {
                onItemClick = { selectedData ->
                    val intent = Intent(activity, DetailCampaign::class.java)
                    intent.putExtra(DetailCampaign.CAMPAIGN_DATA, selectedData)
                    startActivity(intent)
                }
            }
        }
    }

    private fun observeCampaign() {
        homeViewModel.campaigns.observe(viewLifecycleOwner) { campaigns ->
            handleCampaignResource(campaigns)
        }
    }

    private fun handleCampaignResource(campaigns: Resource<List<Campaign>>) {
        val articleAdapter =  CampaignAdapter(CampaignAdapter.Mode.HORIZONTAL)
        articleAdapter.onItemClick = { selectedData ->
            val intent = Intent(activity, DetailCampaign::class.java)
            intent.putExtra(DetailCampaign.CAMPAIGN_DATA, selectedData)
            startActivity(intent)

        }
        when (campaigns) {
            is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
            is Resource.Success -> {
                binding.progressBar.visibility = View.GONE
                articleAdapter.setData(campaigns.data)
            }
            is Resource.Error -> {
                binding.progressBar.visibility = View.GONE
                binding.viewError.root.visibility = View.VISIBLE
                binding.viewError.tvError.text = campaigns.message ?: getString(R.string.something_wrong)
            }
        }

        with(binding.rvCampaign) {
            layoutManager = LinearLayoutManager(context , LinearLayoutManager.HORIZONTAL,false )
            setHasFixedSize(true)
            adapter = articleAdapter
        }
    }


    private fun setUpTransformer(){
        val transformer = CompositePageTransformer()
        transformer.addTransformer(MarginPageTransformer(40))
        transformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = 0.85f + r * 0.14f
        }

        binding.bannerViewPager.setPageTransformer(transformer)
    }

    override fun onPause() {
        super.onPause()

        handler.removeCallbacks(runnable)
    }

    override fun onResume() {
        super.onResume()

        handler.postDelayed(runnable , 2000)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}