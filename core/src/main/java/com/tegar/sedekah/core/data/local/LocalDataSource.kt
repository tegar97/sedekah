package com.tegar.sedekah.core.data.local

import com.tegar.sedekah.core.data.local.entity.CampaignEntity
import com.tegar.sedekah.core.data.local.room.CampaignDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private  val campaignDao: CampaignDao ){
    fun getAllCampaign(): Flow<List<CampaignEntity>> = campaignDao.getAllCampaign()

    fun getFavoriteCampaign(): Flow<List<CampaignEntity>> = campaignDao.getFavoriteCampaign()

    suspend fun insertCampaign(articleList: List<CampaignEntity>) = campaignDao.insertCampaign(articleList)

    fun setFavoriteCampaign(campaign: CampaignEntity, newState: Boolean) {
        campaign.isFavorite = newState
        campaignDao.updateCampaign(campaign)
    }
}