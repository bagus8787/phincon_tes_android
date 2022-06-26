package com.gib.pokemon_bagus.db

import com.gib.pokemon_bagus.model.PokemonEffect

class DatabaseHelperImpl(private val appDatabase: StokRoomDatabase) : DatabaseHelper {

    /**
     * Master Effect
     */
    override suspend fun getAllEffect(): List<PokemonEffect> =
        appDatabase.masterPokemonEffect().getAll()

    override suspend fun insertEffect(effect: PokemonEffect) =
        appDatabase.masterPokemonEffect().insert(effect)

    override suspend fun getEffectById(id: Int): List<PokemonEffect> =
        appDatabase.masterPokemonEffect().getItemById(id)

    override suspend fun getItemByUrl(thisLink: String): List<PokemonEffect> =
        appDatabase.masterPokemonEffect().getItemByUrl(thisLink)

    override suspend fun deleteById(id: Int) =
        appDatabase.masterPokemonEffect().deleteById(id)
}