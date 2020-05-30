package uz.mahmudxon.tictactoe.ui.game

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import uz.mahmudxon.tictactoe.R
import uz.mahmudxon.tictactoe.ui.base.BaseFragment
import javax.inject.Inject


class GameFragment : BaseFragment(R.layout.fragment_game), View.OnClickListener {

    @Inject
    lateinit var providerFactory: ViewModelProvider.Factory
    private val viewModel: GameViewModel by viewModels { providerFactory }


    override fun onCreate(view: View) {

    }

    override fun onBackPressed() {

    }

    override fun onClick(v: View?) {

    }
}
