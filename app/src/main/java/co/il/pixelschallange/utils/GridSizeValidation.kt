package co.il.pixelschallange.utils

import android.util.Log
import co.il.pixelschallange.data.Constants.VALIDATION_TAG

fun String.isValidGridSize() : Boolean {
    return try {
        val gridSize = this.toInt()
        gridSize >= 1000
    } catch (e: Exception) {
        Log.e(VALIDATION_TAG, "Validation error: $e")
        false
    }
}

fun String.getGridSize() : Int {
    return if(this.isValidGridSize()) {
        val gridSize = this.toInt()
        gridSize
    } else {
        -1
    }
}