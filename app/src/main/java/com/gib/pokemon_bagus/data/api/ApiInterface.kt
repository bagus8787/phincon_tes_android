package com.gib.pokemon_bagus.data.api

import com.gib.pokemon_bagus.helper.GlobalVar
import com.gib.pokemon_bagus.model.request.BannerRequest
import com.gib.pokemon_bagus.model.response.AbilityPokemonByIdResponse
import com.gib.pokemon_bagus.model.response.AbilityPokemonResponse
import com.gib.pokemon_bagus.model.response.BannerResponse
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiInterface {
    @POST(GlobalVar.ENDPOINT)
    fun banner(
        @Body request: BannerRequest
    ): Observable<Response<BannerResponse>>

    @GET("ability/")
    suspend fun getAbility(
    ): AbilityPokemonResponse

    @GET("ability/{id}")
    suspend fun getAbilityById(
        @Path("id") id: String
    ): AbilityPokemonByIdResponse
}