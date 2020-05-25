package uz.mahmudxon.tictactoe.di.module.ui

import dagger.Module
import dagger.android.ContributesAndroidInjector
import uz.mahmudxon.tictactoe.ui.MainActivity

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(modules = [MainActivityModule::class, MainActivityFragmentProvider::class])
    abstract fun contributeMainActivity(): MainActivity

}