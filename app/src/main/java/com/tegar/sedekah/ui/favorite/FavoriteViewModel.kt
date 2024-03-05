package com.tegar.sedekah.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.tegar.sedekah.core.domain.usecase.CampaignUseCase

class FavoriteCampaignViewModel(private val campaignUseCase: CampaignUseCase) : ViewModel() {

    val favoriteCampaign = campaignUseCase.getFavoriteCampaign().asLiveData()

}