package com.gib.pokemon_bagus.data.api

class ApiRepository (private val apiInterface: ApiInterface) {

    suspend fun getAbility() = apiInterface.getAbility()

    suspend fun getAbilityById(id: String) = apiInterface.getAbilityById(id)

}