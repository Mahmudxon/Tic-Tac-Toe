package uz.mahmudxon.tictactoe.ui.game

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.mahmudxon.tictactoe.utils.Prefs
import javax.inject.Inject

class GameViewModel @Inject constructor() : ViewModel() {
    @Inject
    lateinit var prefs: Prefs
    private val isCrossFirst: MutableLiveData<Boolean> = MutableLiveData()
    private val isJarvisWithX: MutableLiveData<Boolean> = MutableLiveData()
    private val players = MutableLiveData<List<String>>()
    private var isSingleMode: Boolean = false
    private val buttons = MutableLiveData<List<Int>>()
    fun getIsCrossTurn(): LiveData<Boolean> = isCrossFirst

    private fun changeTurn() {
        val isCrossFirst = this.isCrossFirst.value
        this.isCrossFirst.value = isCrossFirst != true
    }

    fun getButtons(): LiveData<List<Int>> = buttons

    private fun loadWhoFistFromCache() {
        val jb = prefs.get(prefs.isCrossFirst, false)
        isCrossFirst.value = jb
    }

    fun getPlayers(): LiveData<List<String>> = players

    private fun setupSingleMode() {
        loadJarvisJarvisFromCache()
        isSingleMode = true

        val players = ArrayList<String>()
        players.add("You")
        players.add("Jarvis")
        if (isJarvisWithX.value != true) {
            players.reverse()
        }
        this.players.value = players
    }

    private fun setupDoubleMode(playerO: String?, playerX: String?) {
        isSingleMode = false
        val players = ArrayList<String>()
        players.add(playerO ?: "Player-O")
        players.add(playerX ?: "Player-X")
        this.players.value = players
    }

    fun setBundle(bundle: Bundle?) {
        // restore screen
        if (this.buttons.value != null)
            return

        if (bundle == null) {
            setupSingleMode()
        } else {
            val playerO = bundle.getString("playerO")
            val playerX = bundle.getString("playerX")
            setupDoubleMode(playerO, playerX)
        }
        loadWhoFistFromCache()

        val buttons = ArrayList<Int>()
        for (x in 1..10)
            buttons.add(-1)
        this.buttons.value = buttons
    }

    private fun loadJarvisJarvisFromCache() {
        val jb = prefs.get(prefs.isJarvisWithX, false)
        isJarvisWithX.value = jb
    }

    private fun isJarvisTurn() = isJarvisWithX.value == isCrossFirst.value && isSingleMode

    fun setOnClickGameButton(which: Int) {

    }
}