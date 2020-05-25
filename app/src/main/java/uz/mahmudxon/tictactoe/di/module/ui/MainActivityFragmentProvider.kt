package uz.mahmudxon.tictactoe.di.module.ui

import dagger.Module
import dagger.android.ContributesAndroidInjector
import uz.mahmudxon.tictactoe.di.module.ui.game.GameFragmentModule
import uz.mahmudxon.tictactoe.di.module.ui.home.HomeFragmentModule
import uz.mahmudxon.tictactoe.di.module.ui.playwithfriend.PlayWithFriendFragmentModule
import uz.mahmudxon.tictactoe.di.module.ui.playwithpc.PlayWithPcFragmentModule
import uz.mahmudxon.tictactoe.ui.game.GameFragment
import uz.mahmudxon.tictactoe.ui.home.HomeFragment
import uz.mahmudxon.tictactoe.ui.playwithfriend.PlayWithFriendFragment
import uz.mahmudxon.tictactoe.ui.playwithpc.PlayWithPcFragment

@Module
abstract class MainActivityFragmentProvider {

    @ContributesAndroidInjector(modules = [HomeFragmentModule::class])
    abstract fun contributeHomeFragment(): HomeFragment

    @ContributesAndroidInjector(modules = [PlayWithPcFragmentModule::class])
    abstract fun contributePlayWithPcFragment(): PlayWithPcFragment

    @ContributesAndroidInjector(modules = [PlayWithFriendFragmentModule::class])
    abstract fun contributePlayWithFriendFragment(): PlayWithFriendFragment

    @ContributesAndroidInjector(modules = [GameFragmentModule::class])
    abstract fun contributeGameFragment(): GameFragment
}