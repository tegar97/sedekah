package com.tegar.sedekah.core.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tegar.sedekah.core.data.local.entity.CampaignEntity

@Database(entities = [CampaignEntity::class], version = 1, exportSchema = false)

abstract  class SedekahDatabase : RoomDatabase() {

    abstract fun campaignDao():  CampaignDao
}