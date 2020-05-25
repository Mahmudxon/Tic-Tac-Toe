package uz.mahmudxon.tictactoe.app

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import uz.mahmudxon.tictactoe.di.component.DaggerAppComponent

class TicTacToe : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().withApp(this).build()
    }
}