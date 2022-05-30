package co.il.pixelschallange.utils

import android.graphics.Paint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlin.random.Random

object ColorGenerator {

    fun getRandomColorList() : ArrayList<Paint> {
        val colorSet = mutableSetOf<Paint>()
        val colors = arrayListOf<Paint>()
        for(i in 1 until 1000) {
            val randomColor = Paint()
            randomColor.style = Paint.Style.FILL_AND_STROKE
            randomColor.setARGB(255, Random.nextInt(255), Random.nextInt(255), Random.nextInt(255))
            colorSet.add(randomColor)
        }
        colors.addAll(colorSet)
        return colors
    }
}