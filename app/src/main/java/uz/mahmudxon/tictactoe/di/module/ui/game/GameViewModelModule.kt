package uz.mahmudxon.tictactoe.di.module.ui.game

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import uz.mahmudxon.tictactoe.di.module.viewmodel.ViewModelKey
import uz.mahmudxon.tictactoe.ui.game.GameViewModel

@Module
abstract class GameViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(GameViewModel::class)
    abstract fun bindHomeViewModel(hvm: GameViewModel): ViewModel
}