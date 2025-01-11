package com.raju.androidcameraandmedia.di

import com.raju.androidcameraandmedia.player.data.VideoPlayerRepository
import com.raju.androidcameraandmedia.player.data.VideoPlayerRepositoryImpl
import com.raju.androidcameraandmedia.player.domain.PreparePlayerUseCase
import com.raju.androidcameraandmedia.player.domain.ReleasePlayerUseCase
import com.raju.androidcameraandmedia.player.presentation.VideoPlayerViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val videoPlayerModule = module {
    //provide the VideoPlayerRepository
    single<VideoPlayerRepository> { VideoPlayerRepositoryImpl(get()) }

    //provide the Usecase
    factory { PreparePlayerUseCase(get()) }
    factory { ReleasePlayerUseCase(get()) }

    //provide the viewmodel
    viewModel { VideoPlayerViewModel(get(), get()) }
}