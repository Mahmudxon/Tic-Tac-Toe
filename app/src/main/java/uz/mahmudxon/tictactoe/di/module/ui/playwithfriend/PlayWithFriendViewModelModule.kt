package uz.mahmudxon.tictactoe.di.module.ui.playwithfriend

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import uz.mahmudxon.tictactoe.di.module.viewmodel.ViewModelKey
import uz.mahmudxon.tictactoe.ui.playwithfriend.PlayWithFriendViewModel

@Module
abstract class PlayWithFriendViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(PlayWithFriendViewModel::class)
    abstract fun bindHomeViewModel(vm: PlayWithFriendViewModel): ViewModel
}