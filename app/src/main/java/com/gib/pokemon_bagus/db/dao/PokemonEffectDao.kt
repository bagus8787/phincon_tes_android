package com.gib.pokemon_bagus.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gib.pokemon_bagus.helper.GlobalVar
import com.gib.pokemon_bagus.model.PokemonEffect

@Dao
interface PokemonEffectDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(data: PokemonEffect)

    @Query("DELETE FROM " + GlobalVar.TABEL_POKEMON_EFFECT)
    suspend fun deleteAll()

    @Query("SELECT * from " + GlobalVar.TABEL_POKEMON_EFFECT)
    suspend fun getAll(): MutableList<PokemonEffect>

    @Query("SELECT * from ${GlobalVar.TABEL_POKEMON_EFFECT} WHERE id= :id")
    suspend fun getItemById(id: Int): List<PokemonEffect>

    @Query("SELECT * from ${GlobalVar.TABEL_POKEMON_EFFECT} WHERE thisLink= :thisLink")
    suspend fun getItemByUrl(thisLink: String): List<PokemonEffect>

    @Query("DELETE FROM ${GlobalVar.TABEL_POKEMON_EFFECT} WHERE id= :id")
    suspend fun deleteById(id: Int)

    suspend fun insertOrUpdate(item: PokemonEffect) {
        val itemsFromDB : List<PokemonEffect> = getItemById(item.id)
        if (itemsFromDB.isEmpty())
            insert(item)
//        else
//            updateQuantity(item.id)
    }

}