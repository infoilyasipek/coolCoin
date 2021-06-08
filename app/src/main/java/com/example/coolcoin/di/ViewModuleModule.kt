package com.example.coolcoin.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.coolcoin.ui.coindetail.CoinDetailViewModel
import com.example.coolcoin.ui.favorites.FavoriteCoinsViewModel
import com.example.coolcoin.ui.home.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.multibindings.IntoMap

@Module
@InstallIn(ApplicationComponent::class)
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindMainViewModel(viewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CoinDetailViewModel::class)
    abstract fun bindCoinDetailViewModel(viewModel: CoinDetailViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FavoriteCoinsViewModel::class)
    abstract fun bindFavoritesCoinsViewModel(viewModel: FavoriteCoinsViewModel): ViewModel

}
