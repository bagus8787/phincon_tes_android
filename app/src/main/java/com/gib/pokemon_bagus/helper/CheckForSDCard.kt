package com.gib.pokemon_bagus.helper

import android.os.Environment

class CheckForSDCard {
    //Check If SD Card is present or not method
    fun isSDCardPresent(): Boolean {
        return if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED
            )
        ) {
            true
        } else false
    }
}