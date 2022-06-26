package com.gib.pokemon_bagus.helper

import android.content.Context
import com.gib.pokemon_bagus.data.api.ApiInterface

class DialogHelper(
    private val ctx: Context,
    val apiInterface: ApiInterface,
    val session: SessionManager
) {

    private val TAG = "DialogHelper"

    interface ClickListener {
        fun addSubMember(idSiswa: String)
    }


}