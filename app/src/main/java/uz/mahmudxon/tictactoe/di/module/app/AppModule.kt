package uz.mahmudxon.tictactoe.di.module.app

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides

@Module
object AppModule {

    @Provides
    fun provideContext(app: Application): Context = app

}