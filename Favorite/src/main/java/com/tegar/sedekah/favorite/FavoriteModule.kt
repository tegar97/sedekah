package com.tegar.sedekah.favorite

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module {
    viewModel<FavoriteCampaignViewModel>{FavoriteCampaignViewModel(get())}
}