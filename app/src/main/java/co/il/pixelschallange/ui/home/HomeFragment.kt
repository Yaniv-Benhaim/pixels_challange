package co.il.pixelschallange.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import co.il.pixelschallange.R
import co.il.pixelschallange.databinding.FragmentHomeBinding
import co.il.pixelschallange.utils.ToastUtil
import co.il.pixelschallange.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val homeViewModel by activityViewModels<MainViewModel>()
    private val binding get() = _binding!!
    private var showNextFragment = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClickListeners()
        subscribeToObservers()
    }

    private fun subscribeToObservers() {
        homeViewModel.isGridSizeValid.observe(viewLifecycleOwner) { isValid ->
            if(showNextFragment) {
                if (isValid) {
                    findNavController().navigate(R.id.action_navigation_home_to_navigation_dashboard)
                } else {
                    ToastUtil.showToast(
                        requireContext(),
                        getString(R.string.invalid_grid_size_error)
                    )
                }
            }
        }
    }

    private fun initClickListeners() {
        binding.btnContinue.setOnClickListener {
            showNextFragment = true
            homeViewModel.selectGridSize(binding.etGridSize.text.toString())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}