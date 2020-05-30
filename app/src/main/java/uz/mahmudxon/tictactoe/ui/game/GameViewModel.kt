package uz.mahmudxon.tictactoe.ui.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.mahmudxon.tictactoe.utils.Prefs
import javax.inject.Inject

class GameViewModel @Inject constructor() : ViewModel() {
    @Inject
    lateinit var prefs: Prefs
    private val isCrossFirst: MutableLiveData<Boolean> = MutableLiveData()

    fun getIsCrossFirst(): LiveData<Boolean> = isCrossFirst

    fun setIsCrossFirst(isCrossFirst: Boolean) {
        this.isCrossFirst.value = isCrossFirst
    }

    fun loadWhoFistFromCache() {
        val jb = prefs.get(prefs.isCrossFirst, false)
        isCrossFirst.value = jb
    }
}