package uz.mahmudxon.tictactoe.ui.home

import android.view.View
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.fragment_home.*
import uz.mahmudxon.tictactoe.R
import uz.mahmudxon.tictactoe.ui.base.BaseFragment


class HomeFragment : BaseFragment(R.layout.fragment_home), View.OnClickListener {


    override fun onCreate(view: View) {
        openBottomAnimate()
        playWithFriend?.setOnClickListener(this)
        playWithJarvis?.setOnClickListener(this)
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

    override fun onPause() {
        closeBottomAnimate()
        super.onPause()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.playWithJarvis -> {
                navController.navigate(R.id.action_homeFragment_to_playWithPcFragment)
            }
            R.id.playWithFriend -> {
                navController.navigate(R.id.action_homeFragment_to_playWithFriendFragment)
            }
        }
    }
}
