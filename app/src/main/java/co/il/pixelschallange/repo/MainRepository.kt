package co.il.pixelschallange.repo

import co.il.pixelschallange.utils.ColorGenerator
import co.il.pixelschallange.utils.DFSUtil
import co.il.pixelschallange.utils.GridGenerator
import javax.inject.Inject


class MainRepository @Inject constructor(private val dfsUtil: DFSUtil) {

    fun generateRandomGrid(gridSize: Int) = GridGenerator.generateRandomGrid(gridSize)

    fun getRandomColors() = ColorGenerator.getRandomColorList()

    fun solveIslandsGrid(matrix: ArrayList<ArrayList<Int>>) : Pair<ArrayList<ArrayList<Int>>, Int> {
        dfsUtil.matrix = matrix
        return dfsUtil.getNumberOfIslands()
    }
}