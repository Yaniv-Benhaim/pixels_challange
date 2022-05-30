package co.il.pixelschallange.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.il.pixelschallange.repo.MainRepository
import co.il.pixelschallange.utils.getGridSize
import co.il.pixelschallange.utils.isValidGridSize
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {

    //GRID PARAMETERS
    private val _selectedGridSize = MutableLiveData<Int>()
    private val _isGridSizeValid = MutableLiveData<Boolean>()
    val isGridSizeValid: LiveData<Boolean> = _isGridSizeValid

    //GENERATED GRID (SOLUTION & START)
    private val _grid = MutableLiveData<ArrayList<ArrayList<Int>>>()
    val grid: LiveData<ArrayList<ArrayList<Int>>> = _grid

    //NUMBER OF ISLANDS FOUND BY DFS ALGORITHM
    private val _numOfIslands = MutableLiveData<Int>()
    val numOfIslands: LiveData<Int> = _numOfIslands

    fun selectGridSize(gridSize: String) {
        if(gridSize.isValidGridSize()) {
            getRandomGrid(gridSize.getGridSize())
            _selectedGridSize.value = gridSize.getGridSize()
            _isGridSizeValid.value = true
        } else {
            _isGridSizeValid.value = false
        }
    }

    private fun getRandomGrid(gridSize: Int) {
        _grid.value = mainRepository.generateRandomGrid(gridSize)
    }

    fun getSolvedGrid() {
        grid.value?.let {  matrix ->
            val newGrid = mainRepository.solveIslandsGrid(matrix)
            newGrid.first.let {
                _grid.value = it
            }
            newGrid.second.let {
                _numOfIslands.value = it
            }
        }
    }

    fun getRandomColorList() = mainRepository.getRandomColors()

}