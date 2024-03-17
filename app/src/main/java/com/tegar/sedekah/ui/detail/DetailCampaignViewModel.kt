package com.tegar.sedekah.ui.detail

import androidx.lifecycle.ViewModel
import com.tegar.sedekah.core.domain.model.Campaign
import com.tegar.sedekah.core.domain.usecase.CampaignUseCase

class DetailCampaignViewModel(private val campaignUseCase: CampaignUseCase) : ViewModel() {
    fun setFavoriteCampaign(article: Campaign, newStatus:Boolean) =
        campaignUseCase.setFavoriteCampaign(article, newStatus)



}