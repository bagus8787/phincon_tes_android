package com.gib.pokemon_bagus.db

import com.gib.pokemon_bagus.model.PokemonEffect

interface DatabaseHelper {

    suspend fun getAllEffect( ): List<PokemonEffect>
    suspend fun insertEffect(effect: PokemonEffect)
    suspend fun getEffectById(id: Int) :List<PokemonEffect>
    suspend fun getItemByUrl(thisLink: String) :List<PokemonEffect>
    suspend fun deleteById(id: Int)

}