package com.gib.pokemon_bagus.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.gib.pokemon_bagus.data.api.ApiRepository
import com.gib.pokemon_bagus.model.Resource
import kotlinx.coroutines.Dispatchers

class PokemonViewModel (private val apiRepository: ApiRepository) : ViewModel() {

    fun getAbility() =
        liveData(Dispatchers.IO) {
            emit(Resource.loading(data = null))
            try {
                emit(Resource.success(data = apiRepository.getAbility()))
            } catch (exception: Exception) {
                emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
            }
        }

    fun getAbilityById(id: String) =
        liveData(Dispatchers.IO) {
            emit(Resource.loading(data = null))
            try {
                emit(Resource.success(data = apiRepository.getAbilityById(id)))
            } catch (exception: Exception) {
                emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
            }
        }

}