package co.il.pixelschallange.utils

import android.content.Context
import android.util.Log
import android.widget.Toast
import co.il.pixelschallange.data.Constants.TOAST_UTIL_TAG

object ToastUtil {

    fun showToast(context: Context, message: String) {
        try {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Log.e(TOAST_UTIL_TAG, "$e")
        }
    }
}