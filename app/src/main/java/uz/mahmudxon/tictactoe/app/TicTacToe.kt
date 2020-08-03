package uz.mahmudxon.tictactoe.app

import com.google.android.gms.ads.MobileAds
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import uz.mahmudxon.tictactoe.di.component.DaggerAppComponent

class TicTacToe : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().withApp(this).build()
    }

    override fun onCreate() {
        super.onCreate()
        MobileAds.initialize(this)
    }
}