package com.tegar.sedekah.core.domain.usecase

import com.tegar.sedekah.core.data.Resource
import com.tegar.sedekah.core.domain.model.Campaign
import com.tegar.sedekah.core.domain.model.DonateItem
import kotlinx.coroutines.flow.Flow

interface CampaignUseCase {
    fun getAllCampaign() : Flow<Resource<List<Campaign>>>

    fun getFavoriteCampaign() : Flow<List<Campaign>>

    fun setFavoriteCampaign(article: Campaign, state: Boolean)

    fun donate(idDonate: Int, amount: Int) : Flow<Resource<DonateItem>>

}