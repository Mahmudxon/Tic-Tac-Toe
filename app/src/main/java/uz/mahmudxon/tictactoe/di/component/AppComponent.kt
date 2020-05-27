package uz.mahmudxon.tictactoe.di.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import uz.mahmudxon.tictactoe.app.TicTacToe
import uz.mahmudxon.tictactoe.di.module.app.AppModule
import uz.mahmudxon.tictactoe.di.module.ui.ActivityBuilderModule
import uz.mahmudxon.tictactoe.di.module.viewmodel.ViewModelBindingModule

@Component(
    modules = [AndroidSupportInjectionModule::class,
        AppModule::class,
        ActivityBuilderModule::class,
        ViewModelBindingModule::class]
)
interface AppComponent : AndroidInjector<TicTacToe> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun withApp(application: Application): Builder
        fun build(): AppComponent
    }
}