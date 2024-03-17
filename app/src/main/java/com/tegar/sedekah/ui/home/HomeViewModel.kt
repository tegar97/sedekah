package com.tegar.sedekah.ui.home


import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.tegar.sedekah.core.domain.usecase.CampaignUseCase

class HomeViewModel(campaignUseCase: CampaignUseCase): ViewModel() {

    val campaigns = campaignUseCase.getAllCampaign().asLiveData()

}