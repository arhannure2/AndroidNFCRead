package com.example.anroidnfcread

/*


on 15/Dec/2020.
 */

import android.util.Log



/*
*
* Ensure every Log.d you made by calling LogUtils.debug instead.
* every Log.e you made by calling LogUtils.error instead.
* every Log.w you made by calling LogUtils.warnings instead.
*
*/

object LogUtils {
    var TAG = "OGOCABDriver"
    var TAG_FCM = "OGOCABDriverFCM"


    fun debug(tag: String, message: String) {
        if (BuildConfig.DEBUG) {
            Log.d(tag, message)
        }
    }

    fun error(tag: String, message: String) {
        if (BuildConfig.DEBUG) {
            Log.e(tag, message)
        }
    }

    fun warnings(tag: String, message: String) {
        if (BuildConfig.DEBUG) {
           Log.w(tag, message)
        }
    }

    fun info(tag: String, message: String) {
        if (BuildConfig.DEBUG) {
            Log.i(tag, message)
        }
    }

}
