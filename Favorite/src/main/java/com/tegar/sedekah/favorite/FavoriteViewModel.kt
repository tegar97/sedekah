package com.tegar.sedekah.favorite


import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.tegar.sedekah.core.domain.usecase.CampaignUseCase

class FavoriteCampaignViewModel(campaignUseCase: CampaignUseCase) : ViewModel() {

    val favoriteCampaign = campaignUseCase.getFavoriteCampaign().asLiveData()

}