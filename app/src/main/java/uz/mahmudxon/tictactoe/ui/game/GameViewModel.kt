package uz.mahmudxon.tictactoe.ui.game

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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
    private val score = MutableLiveData<List<Int>>()
    private var isX = false
    private var scoreX = 0
    private var scoreY = 0

    private val btnArray: ArrayList<Int> = ArrayList()

    fun getIsCrossTurn(): LiveData<Boolean> = isCrossFirst

    private fun changeTurn() {
        isX = !isX
        this.isCrossFirst.value = isX
        if (isSomeBodyWin() != -1) {
            // somebody is won
            Log.d("TTT", "changeTurn: ${isSomeBodyWin()}")
            clearButtons()
        }

        if (isFullButtons()) {
            // Draw ;-)
            clearButtons()
        }
        Log.d("TTT", "changeTurn: Check Jarvis Turn or not")
        if (isJarvisTurn())
            goJarvis()
    }

    fun getButtons(): LiveData<List<Int>> = buttons

    private fun loadWhoFistFromCache() {
        val jb = prefs.get(prefs.isCrossFirst, false)
        isX = jb
        isCrossFirst.value = jb
        clearButtons()
        if (isJarvisTurn())
            goJarvis()
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
    }

    private fun loadJarvisJarvisFromCache() {
        val jb = prefs.get(prefs.isJarvisWithX, false)
        isJarvisWithX.value = jb
    }

    private fun isJarvisTurn() =
        isJarvisWithX.value == isCrossFirst.value && isSingleMode || btnArray.size != 9

    fun setOnClickGameButton(which: Int, byUser: Boolean = true) {

        if (isJarvisTurn() && byUser)
            return

        val index = which - 1
        if (btnArray[index] == 0) {
            btnArray[index] = if (isX) 2 else 1
        }
        buttons.value = btnArray
        changeTurn()
    }

    private fun clearButtons() {
        val data = IntArray(9) { 0 }.asList()
        btnArray.clear()
        btnArray.addAll(data)
        this.buttons.value = btnArray
        Log.d("TTT", "clearButtons: $data")
    }

    private fun isFullButtons() = btnArray.indexOf(0) < 0

    private fun isSomeBodyWin(): Int {
        if (btnArray[0] > 0 && btnArray[0] == btnArray[1] && btnArray[1] == btnArray[2])
            return btnArray[0]

        if (btnArray[3] > 0 && btnArray[3] == btnArray[4] && btnArray[4] == btnArray[5])
            return btnArray[3]

        if (btnArray[6] > 0 && btnArray[6] == btnArray[7] && btnArray[7] == btnArray[8])
            return btnArray[6]

        if (btnArray[0] > 0 && btnArray[0] == btnArray[3] && btnArray[3] == btnArray[6])
            return btnArray[6]

        if (btnArray[1] > 0 && btnArray[1] == btnArray[4] && btnArray[4] == btnArray[7])
            return btnArray[7]

        if (btnArray[2] > 0 && btnArray[2] == btnArray[5] && btnArray[5] == btnArray[8])
            return btnArray[2]

        if (btnArray[0] > 0 && btnArray[0] == btnArray[4] && btnArray[4] == btnArray[8])
            return btnArray[0]

        if (btnArray[2] > 0 && btnArray[2] == btnArray[4] && btnArray[4] == btnArray[6])
            return btnArray[2]
        return -1
    }

    private fun goJarvis() {
        CoroutineScope(IO).launch {
            setJarvisLevel()
        }
    }

    private suspend fun setJarvisLevel() {
        delay(500)
        val freeSpaces = btnArray.indexOf(0)
        withContext(Main) {
            setOnClickGameButton(freeSpaces + 1, false)
        }
    }

    fun getScore(): LiveData<List<Int>> = score

}