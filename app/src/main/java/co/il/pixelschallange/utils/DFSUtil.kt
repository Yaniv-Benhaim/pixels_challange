package co.il.pixelschallange.utils

class DFSUtil {

    var matrix: ArrayList<ArrayList<Int>> = arrayListOf()

    fun getNumberOfIslands() : Pair<ArrayList<ArrayList<Int>>, Int> {

        val numColumns = matrix.size
        val numRows = matrix.size
        var numberOfIslands = 0

        for (i in 0 until numColumns) {
            for (j in 0 until numRows) {
                if(matrix[i][j] == 1) {
                    markCurrentIsland(i,j,numColumns,numRows, numberOfIslands+2)
                    numberOfIslands += 1
                }
            }
        }

        return Pair(matrix, numberOfIslands)
    }

    private fun markCurrentIsland(column: Int, row: Int, numColumns: Int, numRows: Int, colorInd: Int) {

        if(column < 0 || column >= numColumns || row < 0 || row>=numRows || matrix[column][row] != 1) {
            return
        }

        //Mark visited island
        matrix[column][row] = colorInd

        markCurrentIsland(column+1,row,numColumns,numRows, colorInd) // DOWN
        markCurrentIsland(column,row+1,numColumns,numRows, colorInd) // RIGHT
        markCurrentIsland(column-1,row,numColumns,numRows, colorInd) // TOP
        markCurrentIsland(column,row-1,numColumns,numRows, colorInd) // LEFT
        markCurrentIsland(column-1,row-1,numColumns,numRows, colorInd) // TOP-LEFT
        markCurrentIsland(column-1,row+1,numColumns,numRows, colorInd) // TOP-RIGHT
        markCurrentIsland(column+1,row+1,numColumns,numRows, colorInd) // DOWN-LEFT
        markCurrentIsland(column+1,row-1,numColumns,numRows, colorInd) // RIGHT-RIGHT
    }
}