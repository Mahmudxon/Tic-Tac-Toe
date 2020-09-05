package uz.mahmudxon.tictactoe.ui.home

import android.view.View
import android.view.animation.AnimationUtils
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.fragment_home.*
import uz.mahmudxon.tictactoe.R
import uz.mahmudxon.tictactoe.ui.base.BaseFragment
import javax.inject.Inject


class HomeFragment : BaseFragment(R.layout.fragment_home), View.OnClickListener {

    @Inject
    lateinit var providerFactory: ViewModelProvider.Factory

    private val viewModel: HomeViewModel by viewModels { providerFactory }

    override fun onCreate(view: View) {
        openBottomAnimate()
        playWithFriend?.setOnClickListener(this)
        playWithJarvis?.setOnClickListener(this)
        MobileAds.initialize(context)
        val adRequest = AdRequest.Builder()
            .build()
        banner?.loadAd(adRequest)
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


