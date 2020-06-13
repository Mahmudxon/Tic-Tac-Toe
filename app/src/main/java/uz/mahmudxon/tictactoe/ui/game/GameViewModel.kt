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
import uz.mahmudxon.tictactoe.data.model.Level
import uz.mahmudxon.tictactoe.utils.Prefs
import javax.inject.Inject
import kotlin.properties.Delegates

class GameViewModel @Inject constructor() : ViewModel() {
    @Inject
    lateinit var prefs: Prefs
    private val isCrossFirst: MutableLiveData<Boolean> = MutableLiveData()
    private val isJarvisWithX: MutableLiveData<Boolean> = MutableLiveData()
    private val players = MutableLiveData<List<String>>()
    private var isSingleMode: Boolean = false
    private val buttons = MutableLiveData<List<Int>>()
    private val score = MutableLiveData<List<Int>>()
    private val toastMessage: MutableLiveData<String> = MutableLiveData()
    private var isX = false
    private var scoreX = 0
    private var scoreO = 0
    var jarvisLevel by Delegates.notNull<Int>()

    private val btnArray: ArrayList<Int> = ArrayList()

    fun getIsCrossTurn(): LiveData<Boolean> = isCrossFirst

    private fun changeTurn() {
        if (isSomeBodyWin() != -1) {
            CoroutineScope(IO).launch {
                someoneWin()
            }
            return
        }

        if (isFullButtons()) {
            CoroutineScope(IO).launch {
                draw()
            }
            return
        }

        isX = !isX
        this.isCrossFirst.value = isX
        Log.d("TTT", "changeTurn: Check Jarvis Turn or not")
        if (isJarvisTurn())
            goJarvis()
    }

    fun getButtons(): LiveData<List<Int>> = buttons

    fun getToastMessage(): LiveData<String> = toastMessage

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
        jarvisLevel = prefs.get(prefs.level, Level.EASY)
    }

    private fun isJarvisTurn() =
        isJarvisWithX.value == isCrossFirst.value && isSingleMode || btnArray.size != 9

    fun setOnClickGameButton(which: Int, byUser: Boolean = true) {

        if (isJarvisTurn() && byUser)
            return

        val index = which - 1
        if (btnArray[index] != 0) {
            return
        }
        btnArray[index] = if (isX) 2 else 1
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
        Log.d("TTT", "setJarvisLevel: $jarvisLevel")
        val index =
            when (jarvisLevel) {
                Level.EASY -> {
                    easyMove()
                }
                Level.MEDIUM -> {
                    mediumMove()
                }
                Level.HARD -> {
                    hardMove()
                }
                else -> -1
            }
        withContext(Main) {
            setOnClickGameButton((index + 1), false)
        }
    }

    fun getScore(): LiveData<List<Int>> = score

    private suspend fun someoneWin() {
        delay(700)
        withContext(Main) {

            val winner = if (!isX) players.value?.get(0) else players.value?.get(1)
            toastMessage.value = "$winner is win!"

            if (isSomeBodyWin() == 1)
                scoreX++
            else scoreO++
            score.value = intArrayOf(scoreX, scoreO).toList()

            clearButtons()
            changeTurn()
        }

    }

    private suspend fun easyMove(): Int {

        val freeSpaces = ArrayList<Int>()

        for (i in 0 until btnArray.size)
            if (btnArray[i] == 0)
                freeSpaces.add(i)

        return freeSpaces.random()
    }

    private suspend fun mediumMove(): Int {
        val jarvis = if (isX) 2 else 1
        val win = finalMoveForWin(jarvis)
        return if (win == -1) easyMove() else win
    }

    private fun finalMoveForWin(x: Int): Int {

        var result = check(x, 0, 1, 2)
        if (result >= 0) return result

        result = check(x, 3, 4, 5)
        if (result >= 0) return result

        result = check(x, 6, 7, 8)
        if (result >= 0) return result

        result = check(x, 0, 3, 6)
        if (result >= 0) return result

        result = check(x, 1, 4, 7)
        if (result >= 0) return result

        result = check(x, 2, 5, 8)
        if (result >= 0) return result

        result = check(x, 0, 4, 8)
        if (result >= 0) return result

        result = check(x, 2, 4, 6)
        if (result >= 0) return result

        return -1
    }

    private fun check(x: Int, i1: Int, i2: Int, i3: Int): Int {
        if (btnArray[i1] == 0) {
            if (btnArray[i2] == x && btnArray[i3] == x)
                return i1
        }

        if (btnArray[i2] == 0) {
            if (btnArray[i1] == x && btnArray[i3] == x)
                return i2
        }

        if (btnArray[i3] == 0) {
            if (btnArray[i1] == x && btnArray[i2] == x)
                return i3
        }

        return -1
    }

    private suspend fun hardMove(): Int {
        val jarvis = if (isX) 2 else 1
        val cont = if (isX) 1 else 2
        val win = finalMoveForWin(jarvis)
        val contWin = finalMoveForWin(cont)
        return if (win != -1) win else if (contWin != -1) contWin else easyMove()
    }

    private suspend fun draw() {
        delay(700)
        CoroutineScope(Main).launch {
            toastMessage.value = "Draw"
            clearButtons()
            changeTurn()
        }

    }

}