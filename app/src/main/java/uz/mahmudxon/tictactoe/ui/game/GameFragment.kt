package uz.mahmudxon.tictactoe.ui.game

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import uz.mahmudxon.tictactoe.R
import uz.mahmudxon.tictactoe.ui.base.BaseFragment
import javax.inject.Inject


class GameFragment : BaseFragment(R.layout.fragment_game), View.OnClickListener {

    @Inject
    lateinit var providerFactory: ViewModelProvider.Factory
    private val viewModel: GameViewModel by viewModels { providerFactory }


    override fun onCreate(view: View) {
        setObservers()
        viewModel.loadWhoFistFromCache()
    }

    private fun setObservers() {
        viewModel.getIsCrossFirst().observe(this, Observer { })
    }

    override fun onBackPressed() {

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.quit -> {
                onBackPressed()
            }
        }
    }
}
