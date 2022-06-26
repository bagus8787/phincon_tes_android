package com.gib.pokemon_bagus.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gib.pokemon_bagus.helper.GlobalVar
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = GlobalVar.TABEL_POKEMON_EFFECT)
data class PokemonEffect (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nameCategory: String,
    val thisLink: String,
    val effect : String,
    val name: String,
    val url: String
    ) : Parcelable