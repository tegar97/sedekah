package com.tegar.sedekah.core.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.tegar.sedekah.core.data.local.entity.CampaignEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CampaignDao {

    @Query("SELECT * FROM campaigns")
    fun getAllCampaign(): Flow<List<CampaignEntity>>
    @Query("SELECT * FROM campaigns where isFavorite = 1")
    fun getFavoriteCampaign(): Flow<List<CampaignEntity>>



    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCampaign(campaigns: List<CampaignEntity>)

    @Update
    fun updateCampaign(campaign: CampaignEntity)
}