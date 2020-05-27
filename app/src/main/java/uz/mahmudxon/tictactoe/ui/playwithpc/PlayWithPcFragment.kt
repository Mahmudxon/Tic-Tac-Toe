package uz.mahmudxon.tictactoe.ui.playwithpc

import android.view.View
import android.view.animation.AnimationUtils
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_play_with_pc.*
import uz.mahmudxon.tictactoe.R
import uz.mahmudxon.tictactoe.ui.base.BaseFragment
import javax.inject.Inject


class PlayWithPcFragment : BaseFragment(R.layout.fragment_play_with_pc) {

    @Inject
    lateinit var providerFactory: ViewModelProvider.Factory
    private val viewModel: PLayWithPcViewModel by viewModels { providerFactory }


    override fun onCreate(view: View) {
        openBottomAnimate()
        setObservers()
    }

    private fun setObservers()
    {

    }

    override fun onPause() {
        closeBottomAnimate()
        super.onPause()
    }

    private fun openBottomAnimate() {
        val animBottomLayout =
            AnimationUtils.loadAnimation(requireContext(), R.anim.bottom_layout_open_anim)
        bottomLayout.animation = animBottomLayout
        bottomLayout.animate()
    }

    private fun closeBottomAnimate() {
        val animBottomLayout =
            AnimationUtils.loadAnimation(requireContext(), R.anim.bottom_layout_close_anim)
        bottomLayout.animation = animBottomLayout
        bottomLayout.animate()
    }

}
