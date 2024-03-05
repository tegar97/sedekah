package com.tegar.sedekah.core.domain.usecase

import com.tegar.sedekah.core.data.Resource
import com.tegar.sedekah.core.domain.model.Campaign
import com.tegar.sedekah.core.domain.repository.ICampaignRepository
import kotlinx.coroutines.flow.Flow

class CampaignInteractor(private val campaignRepository : ICampaignRepository) : CampaignUseCase{
    override fun getAllCampaign() = campaignRepository.getAllCampaign()
    override fun getFavoriteCampaign()= campaignRepository.getFavoriteCampaign()

    override fun setFavoriteCampaign(article: Campaign, state: Boolean) = campaignRepository.setFavoriteCampaign(article,state)

}