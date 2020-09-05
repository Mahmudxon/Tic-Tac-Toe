package uz.mahmudxon.tictactoe.ui.playwithpc

import android.view.View
import android.view.animation.AnimationUtils
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.fragment_play_with_pc.*
import uz.mahmudxon.tictactoe.R
import uz.mahmudxon.tictactoe.data.model.Level
import uz.mahmudxon.tictactoe.ui.base.BaseFragment
import javax.inject.Inject


class PlayWithPcFragment : BaseFragment(R.layout.fragment_play_with_pc), View.OnClickListener {

    @Inject
    lateinit var providerFactory: ViewModelProvider.Factory
    private val viewModel: PLayWithPcViewModel by viewModels { providerFactory }


    override fun onCreate(view: View) {
        openBottomAnimate()
        setObservers()
        MobileAds.initialize(context)
        val adRequest = AdRequest.Builder().build()
        banner.loadAd(adRequest)
        viewModel.loadLevelFromCache()
        viewModel.loadJarvisButtonFromCache()
        viewModel.loadWhoFistFromCache()
        diff_low?.setOnClickListener(this)
        diff_medium?.setOnClickListener(this)
        diff_high?.setOnClickListener(this)
        wepon_circle?.setOnClickListener(this)
        wepon_cross?.setOnClickListener(this)
        cross_move?.setOnClickListener(this)
        circle_move?.setOnClickListener(this)
        btnPlay?.setOnClickListener(this)
        btnQuit?.setOnClickListener(this)
    }

    private fun setObservers() {
        viewModel.getLevel().observe(this, Observer { checkDiffButtons(it) })
        viewModel.getIsJarvisWithX().observe(this, Observer { checkWepon(it) })
        viewModel.getIsCrossFirst().observe(this, Observer { checkMoveButtons(it) })
    }

    private fun checkDiffButtons(level: Int) {
        diff_low?.setBackgroundResource(R.drawable.layout_difficulty_button)
        diff_medium?.setBackgroundResource(R.drawable.layout_difficulty_button)
        diff_high?.setBackgroundResource(R.drawable.layout_difficulty_button)

        when (level) {
            Level.EASY -> {
                diff_low?.setBackgroundResource(R.drawable.layout_difficulty_button_secondary)
            }
            Level.MEDIUM -> {
                diff_medium?.setBackgroundResource(R.drawable.layout_difficulty_button_secondary)
            }

            Level.HARD -> {
                diff_high?.setBackgroundResource(R.drawable.layout_difficulty_button_secondary)
            }
        }
    }

    private fun checkWepon(isCross: Boolean) {
        wepon_circle?.setImageResource(R.drawable.ic_circle_white)
        wepon_cross?.setImageResource(R.drawable.ic_cross_white)
        if (!isCross)
            wepon_circle?.setImageResource(R.drawable.ic_circle_secondary)
        else wepon_cross?.setImageResource(R.drawable.ic_cross_secondary)
    }

    private fun checkMoveButtons(isCross: Boolean) {
        circle_move?.setImageResource(R.drawable.ic_circle_white)
        cross_move?.setImageResource(R.drawable.ic_cross_white)
        if (isCross)
            cross_move?.setImageResource(R.drawable.ic_cross_secondary)
        else
            circle_move?.setImageResource(R.drawable.ic_circle_secondary)
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
            R.id.diff_low -> viewModel.setLevel(Level.EASY)
            R.id.diff_medium -> viewModel.setLevel(Level.MEDIUM)
            R.id.diff_high -> viewModel.setLevel(Level.HARD)
            R.id.wepon_circle -> viewModel.setJarvisWithX(false)
            R.id.wepon_cross -> viewModel.setJarvisWithX(true)
            R.id.cross_move -> viewModel.setIsCrossFirst(true)
            R.id.circle_move -> viewModel.setIsCrossFirst(false)
            R.id.btnPlay -> {
                // There Can Send Something to GameFragment
                navController.navigate(R.id.action_playWithPcFragment_to_gameFragment)
            }
            R.id.btnQuit -> activity?.onBackPressed()
        }
    }
}
