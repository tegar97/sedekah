package com.tegar.sedekah.core.data.repository

import com.tegar.sedekah.core.data.NetworkBoundResource
import com.tegar.sedekah.core.data.Resource
import com.tegar.sedekah.core.data.local.LocalDataSource
import com.tegar.sedekah.core.data.remote.RemoteDataSource
import com.tegar.sedekah.core.data.remote.network.ApiResponse
import com.tegar.sedekah.core.data.remote.response.CampaignResponse
import com.tegar.sedekah.core.domain.model.Campaign
import com.tegar.sedekah.core.domain.model.DonateItem
import com.tegar.sedekah.core.domain.repository.ICampaignRepository
import com.tegar.sedekah.core.utils.AppExecutors
import com.tegar.sedekah.core.utils.toDomain
import com.tegar.sedekah.core.utils.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CampaignRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : ICampaignRepository {
    override fun getAllCampaign(): Flow<Resource<List<Campaign>>> =
        object : NetworkBoundResource<List<Campaign>, List<CampaignResponse>>() {
            override fun loadFromDB(): Flow<List<Campaign>> {
                return localDataSource.getAllCampaign().map { campaigns ->
                    campaigns.map { it.toDomain() }
                }
            }

            override fun shouldFetch(data: List<Campaign>?): Boolean =
//                data == null || data.isEmpty()
                true

            override suspend fun createCall(): Flow<ApiResponse<List<CampaignResponse>>> =
                remoteDataSource.getAllCampaign()

            override suspend fun saveCallResult(data: List<CampaignResponse>) {
                val campaignList = data.map { it.toEntity() }
                localDataSource.insertCampaign(campaignList)
            }
        }.asFlow()


    override fun getFavoriteCampaign(): Flow<List<Campaign>> {
        return localDataSource.getFavoriteCampaign().map { campaigns ->
            campaigns.map { it.toDomain() }
        }
    }

    override fun setFavoriteCampaign(article: Campaign, state: Boolean) {
        val campaignEntity = article.toEntity()
        appExecutors.diskIO().execute { localDataSource.setFavoriteCampaign(campaignEntity, state) }

    }

    override fun donate(idDonate: Int, amount: Int)  : Flow<Resource<DonateItem>> {
        return remoteDataSource.donate(idDonate, amount).map { apiResponse ->
            when (apiResponse) {
                is ApiResponse.Success -> {
                    val data = apiResponse.data.toDomain()
                    Resource.Success(data)
                }

                is ApiResponse.Error -> {
                    Resource.Error(apiResponse.errorMessage)
                }

                else -> {
                    Resource.Loading()
                }
            }

        }


    }

}