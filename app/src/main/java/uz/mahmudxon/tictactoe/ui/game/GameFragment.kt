package uz.mahmudxon.tictactoe.ui.game

import android.view.View
import kotlinx.android.synthetic.main.fragment_game.*
import uz.mahmudxon.tictactoe.R
import uz.mahmudxon.tictactoe.ui.base.BaseFragment


class GameFragment : BaseFragment(R.layout.fragment_game) {

    override fun onCreate(view: View) {
        quit?.setOnClickListener { activity?.onBackPressed() }
    }

    override fun onBackPressed() {

    }
}
