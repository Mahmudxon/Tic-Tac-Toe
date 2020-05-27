package uz.mahmudxon.tictactoe.ui.playwithpc

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class PLayWithPcViewModel @Inject constructor() : ViewModel()
{
    private val lavel : MutableLiveData<Int> = MutableLiveData()

}