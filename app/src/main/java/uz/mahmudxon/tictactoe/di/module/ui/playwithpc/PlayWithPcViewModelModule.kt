package uz.mahmudxon.tictactoe.di.module.ui.playwithpc

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import uz.mahmudxon.tictactoe.di.module.viewmodel.ViewModelKey
import uz.mahmudxon.tictactoe.ui.playwithpc.PLayWithPcViewModel

@Module
abstract class PlayWithPcViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(PLayWithPcViewModel::class)
    abstract fun bindHomeViewModel(vm: PLayWithPcViewModel): ViewModel
}