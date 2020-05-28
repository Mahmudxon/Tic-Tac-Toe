package uz.mahmudxon.tictactoe.ui.playwithfriend

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.mahmudxon.tictactoe.utils.Prefs
import javax.inject.Inject

class PlayWithFriendViewModel @Inject constructor() : ViewModel() {
    @Inject
    lateinit var prefs: Prefs
    private val isCrossFirst: MutableLiveData<Boolean> = MutableLiveData()
    fun getIsCrossFirst(): LiveData<Boolean> = isCrossFirst

    fun setIsCrossFirst(isCrossFirst: Boolean) {
        prefs.save(prefs.isCrossFirst, isCrossFirst)
        this.isCrossFirst.value = isCrossFirst
    }

    fun loadWhoFistFromCache() {
        val jb = prefs.get(prefs.isCrossFirst, false)
        isCrossFirst.value = jb
    }

}