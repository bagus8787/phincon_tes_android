package com.gib.pokemon_bagus.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class DataModel(val image: Int, val title: String, val cat: String) : Parcelable