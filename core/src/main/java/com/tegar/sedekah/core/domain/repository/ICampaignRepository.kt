package com.tegar.sedekah.core.domain.repository

import com.tegar.sedekah.core.data.Resource
import com.tegar.sedekah.core.domain.model.Campaign
import com.tegar.sedekah.core.domain.model.DonateItem
import kotlinx.coroutines.flow.Flow

interface ICampaignRepository {

    fun getAllCampaign() : Flow<Resource<List<Campaign>>>

    fun getFavoriteCampaign() : Flow<List<Campaign>>

    fun setFavoriteCampaign(article: Campaign, state: Boolean)




}