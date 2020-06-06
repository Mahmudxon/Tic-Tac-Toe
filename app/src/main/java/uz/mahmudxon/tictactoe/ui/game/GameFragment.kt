package uz.mahmudxon.tictactoe.ui.game

import android.view.View
import android.widget.ImageView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_game.*
import uz.mahmudxon.tictactoe.R
import uz.mahmudxon.tictactoe.ui.base.BaseFragment
import javax.inject.Inject


class GameFragment : BaseFragment(R.layout.fragment_game), View.OnClickListener {

    @Inject
    lateinit var providerFactory: ViewModelProvider.Factory
    private val viewModel: GameViewModel by viewModels { providerFactory }


    override fun onCreate(view: View) {
        setObservers()
        viewModel.setBundle(arguments)
        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        quit?.setOnClickListener(this)
        btn1?.setOnClickListener(this)
        btn2?.setOnClickListener(this)
        btn3?.setOnClickListener(this)
        btn4?.setOnClickListener(this)
        btn5?.setOnClickListener(this)
        btn6?.setOnClickListener(this)
        btn7?.setOnClickListener(this)
        btn8?.setOnClickListener(this)
        btn9?.setOnClickListener(this)
    }

    private fun setObservers() {
        viewModel.getIsCrossTurn().observe(this, Observer { if (it) crossTurn() else circleTurn() })
        viewModel.getPlayers().observe(this, Observer { setPlayerNames(it) })
        viewModel.getButtons().observe(this, Observer { setDataToGameButtons(it) })
        viewModel.getScore().observe(this, Observer { setScore(it) })
    }


    private fun setScore(data: List<Int>) {
        p1_winning?.text = "${data[0]}"
        p2_winning?.text = "${data[1]}"
    }

    private fun setPlayerNames(players: List<String>) {
        Player1?.text = players[0]
        Player2?.text = players[1]
    }

    private fun crossTurn() {
        player2_trophy?.setImageResource(R.drawable.ic_trophy_golden)
        player1_trophy?.setImageResource(R.drawable.ic_trophy_grey)
    }

    private fun circleTurn() {
        player1_trophy?.setImageResource(R.drawable.ic_trophy_golden)
        player2_trophy?.setImageResource(R.drawable.ic_trophy_grey)
    }


    private fun setDataToGameButtons(data: List<Int>) {
        for (position in 1..data.size) {
            val resId =
                if (data[position - 1] == 2) R.drawable.ic_cross_secondary else if (data[position - 1] == 1) R.drawable.ic_circle_secondary else 0
            val imageView: ImageView? = when (position) {
                1 -> btn1
                2 -> btn2
                3 -> btn3
                4 -> btn4
                5 -> btn5
                6 -> btn6
                7 -> btn7
                8 -> btn8
                9 -> btn9
                else -> null
            }
            imageView?.setImageResource(resId)
        }
    }

    override fun onBackPressed() {
        activity?.onBackPressed()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.quit -> {
                onBackPressed()
            }

            R.id.btn1 -> {
                viewModel.setOnClickGameButton(1)
            }
            R.id.btn2 -> {
                viewModel.setOnClickGameButton(2)
            }
            R.id.btn3 -> {
                viewModel.setOnClickGameButton(3)
            }
            R.id.btn4 -> {
                viewModel.setOnClickGameButton(4)
            }
            R.id.btn5 -> {
                viewModel.setOnClickGameButton(5)
            }
            R.id.btn6 -> {
                viewModel.setOnClickGameButton(6)
            }
            R.id.btn7 -> {
                viewModel.setOnClickGameButton(7)
            }
            R.id.btn8 -> {
                viewModel.setOnClickGameButton(8)
            }
            R.id.btn9 -> {
                viewModel.setOnClickGameButton(9)
            }
        }
    }
}
