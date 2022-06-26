package com.gib.pokemon_bagus.model.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class AbilityPokemonResponse(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<Result>
) {
    @Parcelize
    data class Result(
        val name: String,
        val url: String
    ): Parcelable
}