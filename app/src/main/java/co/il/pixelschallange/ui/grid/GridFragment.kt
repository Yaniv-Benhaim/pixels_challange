package co.il.pixelschallange.ui.grid

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import co.il.pixelschallange.R
import co.il.pixelschallange.data.Constants.GRID_FRAGMENT_TAG
import co.il.pixelschallange.databinding.FragmentGridBinding
import co.il.pixelschallange.utils.ToastUtil
import co.il.pixelschallange.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GridFragment : Fragment(R.layout.fragment_grid) {

    private var _binding: FragmentGridBinding? = null
    private val binding get() = _binding!!
    private val mainViewModel by activityViewModels<MainViewModel>()
    private var showResult = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGridBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToObservers()
        initGrid()
    }

    private fun initGrid() = binding.pixelGridView.apply {
        setRandomColorList(mainViewModel.getRandomColorList())
    }

    private fun subscribeToObservers() {

        mainViewModel.grid.observe(viewLifecycleOwner) { grid ->
            try {
                binding.pixelGridView.setGrid(grid)
                initClickListeners()
            } catch (e: Exception) {
                Log.e(GRID_FRAGMENT_TAG, "$e")
                ToastUtil.showToast(requireContext(), getString(R.string.out_of_memory_error))
            }
        }

        mainViewModel.numOfIslands.observe(viewLifecycleOwner) { numOfIslands ->
            if(numOfIslands > 0 && showResult) {
                val islandText = String.format(getString(R.string.found_islands), numOfIslands)
                binding.tvIslandsFound.text = islandText
                binding.btnContinue.isVisible = false
                binding.btnContinue.isEnabled = false
                binding.btnRestart.isVisible = true
            }
        }
    }

    private fun initClickListeners() {
        binding.btnContinue.setOnClickListener {
            showResult = true
            mainViewModel.getSolvedGrid()
        }

        binding.btnRestart.setOnClickListener {
            findNavController().navigate(R.id.action_global_to_splash_fragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}