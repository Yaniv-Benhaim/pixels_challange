package co.il.pixelschallange.ui.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import co.il.pixelschallange.R
import kotlinx.coroutines.delay


class SplashFragment : Fragment(R.layout.fragment_splash) {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        goToApp()
    }

    //Instead of the delay we would normally load and setup initial content etc.
    private fun goToApp() = lifecycleScope.launchWhenCreated {
        delay(2000)
        findNavController().navigate(R.id.action_splashFragment_to_navigation_home)
    }
}