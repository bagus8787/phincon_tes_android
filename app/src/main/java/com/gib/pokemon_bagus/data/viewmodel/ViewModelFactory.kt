package com.gib.pokemon_bagus.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gib.pokemon_bagus.data.api.ApiInterface
import com.gib.pokemon_bagus.data.api.ApiRepository

class ViewModelFactory (private val apiInterface: ApiInterface) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PokemonViewModel::class.java)) {
            return PokemonViewModel(ApiRepository(apiInterface)) as T
        }
//        else if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
//            return AuthViewModel(ApiRepository(apiInterface)) as T
//        }

        throw IllegalArgumentException("Unknown class name")
    }

}
