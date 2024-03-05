package com.tegar.sedekah.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.tegar.sedekah.core.domain.usecase.CampaignUseCase

class HomeViewModel(campaignUseCase: CampaignUseCase): ViewModel() {

    val articles = campaignUseCase.getAllCampaign().asLiveData()

}