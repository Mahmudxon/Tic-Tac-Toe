package uz.mahmudxon.tictactoe.di.module.ui.home

import androidx.lifecycle.ViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import uz.mahmudxon.tictactoe.di.module.viewmodel.ViewModelKey
import uz.mahmudxon.tictactoe.ui.home.HomeViewModel

@Module
class HomeViewModelModule {
//    @Provides
//    @ViewModelKey(HomeViewModel::class)
//    @IntoMap
//    fun bindViewModel(homeViewModel: HomeViewModel): ViewModel = homeViewModel
}