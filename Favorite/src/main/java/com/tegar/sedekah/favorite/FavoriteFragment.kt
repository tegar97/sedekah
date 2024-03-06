package com.tegar.sedekah.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.tegar.sedekah.core.ui.CampaignAdapter
import com.tegar.sedekah.favorite.databinding.FragmentFavoriteBinding
import com.tegar.sedekah.ui.detail.DetailCampaign
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val favoriteCampaignViewModel: FavoriteCampaignViewModel by viewModel()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadKoinModules(favoriteModule)
        if (activity != null) {


            val campaignAdapter = CampaignAdapter(CampaignAdapter.Mode.FULL)
            campaignAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailCampaign::class.java)
                intent.putExtra(DetailCampaign.CAMPAIGN_DATA, selectedData)
                startActivity(intent)
            }


            favoriteCampaignViewModel.favoriteCampaign.observe(viewLifecycleOwner) { data ->
                campaignAdapter.setData(data)
            }

            with(binding.rvCampaign) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = campaignAdapter
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}