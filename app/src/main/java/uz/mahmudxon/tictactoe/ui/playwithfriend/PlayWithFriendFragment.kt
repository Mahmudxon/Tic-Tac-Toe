package uz.mahmudxon.tictactoe.ui.playwithfriend

import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_play_with_friend.*
import kotlinx.android.synthetic.main.fragment_play_with_friend.banner
import kotlinx.android.synthetic.main.fragment_play_with_friend.bottomLayout
import uz.mahmudxon.tictactoe.R
import uz.mahmudxon.tictactoe.ui.base.BaseFragment
import javax.inject.Inject

class PlayWithFriendFragment : BaseFragment(R.layout.fragment_play_with_friend),
    View.OnClickListener {

    @Inject
    lateinit var providerFactory: ViewModelProvider.Factory
    private val viewModel: PlayWithFriendViewModel by viewModels { providerFactory }

    override fun onCreate(view: View) {
        openBottomAnimate()
        setObservers()
        MobileAds.initialize(context)
        val adRequest = AdRequest.Builder().build()
        banner.loadAd(adRequest)
        viewModel.loadWhoFistFromCache()
        cross?.setOnClickListener(this)
        circle?.setOnClickListener(this)
        btnPlay?.setOnClickListener(this)
        btnQuit?.setOnClickListener(this)
    }


    private fun setObservers() {
        viewModel.getIsCrossFirst().observe(this, Observer { checkMoveButtons(it) })
    }


    private fun checkMoveButtons(isCross: Boolean) {
        circle?.setImageResource(R.drawable.ic_circle_white)
        cross?.setImageResource(R.drawable.ic_cross_white)
        if (isCross)
            cross?.setImageResource(R.drawable.ic_cross_secondary)
        else
            circle?.setImageResource(R.drawable.ic_circle_secondary)
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

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.circle -> viewModel.setIsCrossFirst(false)
            R.id.cross -> viewModel.setIsCrossFirst(true)
            R.id.btnPlay -> {
                var playerO: String =
                    player_one?.text.toString()
                if (playerO.isNullOrEmpty())
                    playerO = requireContext().getString(R.string.circle_hint_text)

                var playerX: String = player_two?.text.toString()
                if (playerX.isNullOrEmpty())
                    playerX = requireContext().getString(R.string.cross_hint_text)
                val bundle = Bundle()
                bundle.putString("playerO", playerO)
                bundle.putString("playerX", playerX)
                navController.navigate(R.id.action_playWithFriendFragment_to_gameFragment, bundle)
            }
            R.id.btnQuit -> activity?.onBackPressed()
        }
    }
}
