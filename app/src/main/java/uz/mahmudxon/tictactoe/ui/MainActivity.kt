package uz.mahmudxon.tictactoe.ui

import android.view.View
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.Navigation
import uz.mahmudxon.tictactoe.R
import uz.mahmudxon.tictactoe.ui.base.BaseActivity

class MainActivity : BaseActivity(R.layout.activity_main) {

    override fun onAfterCreate() {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
    }


    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus)
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
    }
}
