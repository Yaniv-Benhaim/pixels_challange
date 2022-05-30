package co.il.pixelschallange.utils

import co.il.pixelschallange.data.Constants
import kotlin.random.Random

object GridGenerator {

    fun generateRandomGrid(gridSize: Int) : ArrayList<ArrayList<Int>>{

        val numColumns = if(gridSize < 1000) (gridSize/100) * 50 else gridSize/100
        val numRows = if(gridSize < 1000) (gridSize/100) * 50 else gridSize/100

        val randomGrid = arrayListOf<ArrayList<Int>>()
        for (i in 0 until numColumns) {
            randomGrid.add(ArrayList())
            for (j in 0 until numRows) {
                val randomLand = if(Random.nextInt(100) > Constants.AUTO_GENERATE_PERCENTAGE) 1 else 0
                randomGrid[i].add(randomLand)
            }
        }
        return  randomGrid
    }
}