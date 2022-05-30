package co.il.pixelschallange.ui.customviews

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import co.il.pixelschallange.data.Constants.GRID_FRAGMENT_TAG
import co.il.pixelschallange.utils.ToastUtil

class PixelGridView(context: Context?, attrs: AttributeSet?) :
    View(context, attrs) {

    private var numColumns = 0
    private var numRows = 0
    private var cellWidth = 0
    private var cellHeight = 0
    private val blackPaint: Paint = Paint()
    private var colors = arrayListOf<Paint>()
    private var matrix = arrayListOf<ArrayList<Int>>()

    constructor(context: Context?) : this(context, null) {}

    fun setGrid(grid: ArrayList<ArrayList<Int>>) {
        this.numColumns = grid.size
        this.numRows = grid.size
        this.matrix = grid
        calculateDimensions()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        calculateDimensions()
    }

    fun setRandomColorList(colors: ArrayList<Paint>) {
        this.colors = colors
    }

    private fun calculateDimensions() {
        if (numColumns < 1 || numRows < 1) {
            return
        }
        cellWidth = width / numColumns
        cellHeight = height / numRows
        invalidate()
    }


     override fun onDraw(canvas: Canvas) {

         canvas.drawColor(Color.WHITE)
         if (numColumns == 0 || numRows == 0) {
            return
         }
         paintGrid(canvas)
        try {
            for (i in 0 until numColumns) {
                for (j in 0 until numRows) {
                    if (matrix[i][j] == 1) {
                        paintPixel(i, j, canvas)
                    } else if (matrix[i][j] != 0) {
                        //val color = colors[matrix[i][j]]
                        paintColoredPixel(i, j, canvas, colors[matrix[i][j]])
                    }
                }
            }
        } catch (e: Exception) {
            Log.e(GRID_FRAGMENT_TAG, "matrix size: ${matrix.size} $e")
            ToastUtil.showToast(context, "Something went wrong please try a lower grid number")
        }
    }

    private fun paintGrid(canvas: Canvas) {
        for (i in 1 until numColumns) {
            canvas.drawLine((i * cellWidth).toFloat(), 0F,
                (i * cellWidth).toFloat(), height.toFloat(), blackPaint)
        }
        for (i in 1 until numRows) {
            canvas.drawLine(
                0F, (i * cellHeight).toFloat(),
                width.toFloat(), (i * cellHeight).toFloat(), blackPaint)
        }
    }

    private fun paintPixel(i: Int, j: Int, canvas: Canvas) {
        canvas.drawRect(
            (i * cellWidth).toFloat(), (j * cellHeight).toFloat(),
            ((i + 1) * cellWidth).toFloat(), ((j + 1) * cellHeight).toFloat(),
            blackPaint
        )
    }

    private fun paintColoredPixel(i: Int, j: Int, canvas: Canvas, color: Paint) {
            canvas.drawRect(
                (i * cellWidth).toFloat(), (j * cellHeight).toFloat(),
                ((i + 1) * cellWidth).toFloat(), ((j + 1) * cellHeight).toFloat(),
                color
            )
    }

    init {
        blackPaint.style = Paint.Style.FILL_AND_STROKE
    }
}

