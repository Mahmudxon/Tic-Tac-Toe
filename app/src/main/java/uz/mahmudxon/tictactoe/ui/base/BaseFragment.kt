package uz.mahmudxon.tictactoe.ui.base

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.navigation.NavController
import androidx.navigation.Navigation
import dagger.android.support.DaggerFragment

abstract class BaseFragment(@LayoutRes val layout: Int) : DaggerFragment() {

    lateinit var navController: NavController
    private var isUseBackPress = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.isFocusableInTouchMode = true
        view.requestFocus()
        view.setOnKeyListener { _, keyCode, _ ->
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                isUseBackPress = true
                onBackPressed()
                return@setOnKeyListener isUseBackPress
            } else return@setOnKeyListener false
        }
        navController = Navigation.findNavController(view)
        onCreate(view)
    }

    abstract fun onCreate(view: View)

    open fun onBackPressed() {
        isUseBackPress = false
    }
}