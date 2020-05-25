package uz.mahmudxon.tictactoe.ui.home

import android.view.View
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.fragment_home.*
import uz.mahmudxon.tictactoe.R
import uz.mahmudxon.tictactoe.ui.base.BaseFragment


class HomeFragment : BaseFragment(R.layout.fragment_home), View.OnClickListener {


    override fun onCreate(view: View) {
        animate()
        playWithFriend?.setOnClickListener(this)
        playWithJarvis?.setOnClickListener(this)
    }

    private fun animate() {
        val animBottomLayout =
            AnimationUtils.loadAnimation(requireContext(), R.anim.bottom_layout_anim)
        val animIcSettings =
            AnimationUtils.loadAnimation(requireContext(), R.anim.icon_settings_anim)
        bottomLayout.animation = animBottomLayout
        bottomLayout.animate()
        icSetting.animation = animIcSettings
        icSetting.animate()
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
