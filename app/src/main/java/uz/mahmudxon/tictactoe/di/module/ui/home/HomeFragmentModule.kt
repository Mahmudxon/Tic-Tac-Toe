package uz.mahmudxon.tictactoe.di.module.ui.home

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import uz.mahmudxon.tictactoe.di.module.viewmodel.ViewModelKey
import uz.mahmudxon.tictactoe.ui.home.HomeViewModel

@Module(includes = [HomeViewModelModule::class])
abstract class HomeFragmentModule {
    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(hvm : HomeViewModel) : ViewModel
}