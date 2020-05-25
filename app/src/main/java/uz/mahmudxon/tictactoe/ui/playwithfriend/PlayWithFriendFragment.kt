package uz.mahmudxon.tictactoe.ui.playwithfriend

import android.view.View
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.fragment_play_with_friend.*
import uz.mahmudxon.tictactoe.R
import uz.mahmudxon.tictactoe.ui.base.BaseFragment

class PlayWithFriendFragment : BaseFragment(R.layout.fragment_play_with_friend) {
    override fun onCreate(view: View) {
        openBottomAnimate()
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
