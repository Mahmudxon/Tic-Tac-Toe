package uz.mahmudxon.tictactoe.di.module.viewmodel

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import uz.mahmudxon.tictactoe.viewmodels.ViewModelProviderFactory

@Module
abstract class ViewModelBindingModule {
    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelProviderFactory): ViewModelProvider.Factory
}