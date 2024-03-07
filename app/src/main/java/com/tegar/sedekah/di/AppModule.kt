package com.tegar.sedekah.di

import com.tegar.sedekah.core.domain.usecase.AuthInteractor
import com.tegar.sedekah.core.domain.usecase.AuthUseCase
import com.tegar.sedekah.core.domain.usecase.CampaignInteractor
import com.tegar.sedekah.core.domain.usecase.CampaignUseCase
import com.tegar.sedekah.ui.auth.login.LoginViewModel
import com.tegar.sedekah.ui.auth.register.RegisterViewModel
import com.tegar.sedekah.ui.detail.DetailCampaignViewModel
import com.tegar.sedekah.ui.home.HomeViewModel
import com.tegar.sedekah.ui.main.MainViewModel
import com.tegar.sedekah.ui.profile.ProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.scope.get
import org.koin.dsl.module

val useCaseModule = module {
    factory<CampaignUseCase> { CampaignInteractor(get()) }
    factory<AuthUseCase> { AuthInteractor(get()) }

}

val viewModelModule = module {
    viewModel <HomeViewModel>{ HomeViewModel(get()) }
    viewModel <DetailCampaignViewModel>{ DetailCampaignViewModel(get()) }
    viewModel <ProfileViewModel>{ ProfileViewModel(get(),get()) }
    viewModel <MainViewModel>{ MainViewModel(get()) }
    viewModel <LoginViewModel>{ LoginViewModel(get()) }
    viewModel <RegisterViewModel>{ RegisterViewModel(get()) }

}
