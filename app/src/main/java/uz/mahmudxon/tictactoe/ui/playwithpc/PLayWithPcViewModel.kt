package uz.mahmudxon.tictactoe.ui.playwithpc

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.mahmudxon.tictactoe.data.model.Level
import uz.mahmudxon.tictactoe.utils.Prefs
import javax.inject.Inject

class PLayWithPcViewModel @Inject constructor() : ViewModel() {
    @Inject
    lateinit var prefs: Prefs
    private val level: MutableLiveData<Int> = MutableLiveData()

    private val isJarvisWithX: MutableLiveData<Boolean> = MutableLiveData()
    private val isCrossFirst: MutableLiveData<Boolean> = MutableLiveData()

    fun getLevel(): LiveData<Int> = level

    fun loadLevelFromCache() {
        val level = prefs.get(prefs.level, Level.EASY)
        this.level.value = level
    }

    fun setLevel(level: Int) {
        prefs.save(prefs.level, level)
        this.level.value = level
    }

    fun loadJarvisButtonFromCache() {
        val jb = prefs.get(prefs.isCrossFirst, false)
        isJarvisWithX.value = jb
    }

    fun getIsJarvisWithX(): LiveData<Boolean> = isJarvisWithX

    fun setJarvisWithX(isJarvisWithX: Boolean) {
        prefs.save(prefs.isJarvisWithX, isJarvisWithX)
        this.isJarvisWithX.value = isJarvisWithX
    }

    fun getIsCrossFirst(): LiveData<Boolean> = isCrossFirst

    fun setIsCrossFirst(isCrossFirst: Boolean) {
        prefs.save(prefs.isCrossFirst, isCrossFirst)
        this.isCrossFirst.value = isCrossFirst
    }

    fun loadWhoFistFromCache() {
        val jb = prefs.get(prefs.isJarvisWithX, false)
        isCrossFirst.value = jb
    }



}