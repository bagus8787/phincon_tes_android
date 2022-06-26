package com.gib.pokemon_bagus.view.fragment

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.gib.pokemon_bagus.R
import com.gib.pokemon_bagus.adapter.PokemonAdapter
import com.gib.pokemon_bagus.data.viewmodel.PokemonViewModel
import com.gib.pokemon_bagus.data.viewmodel.ViewModelFactory
import com.gib.pokemon_bagus.databinding.FragmentHomeBinding
import com.gib.pokemon_bagus.helper.viewBinding
import com.gib.pokemon_bagus.model.Status
import com.gib.pokemon_bagus.model.response.AbilityPokemonResponse
import com.gib.pokemon_bagus.view.base.BaseFragment
import kotlinx.coroutines.runBlocking

class HomeFragment : BaseFragment(R.layout.fragment_home), PokemonAdapter.CellClickListener {

    private val binding by viewBinding(FragmentHomeBinding::bind)

    private lateinit var pokemonViewmodel: PokemonViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewmodel()
        loadPokemon()
        setView()
        setListener()
    }

    private fun initViewmodel(){
        pokemonViewmodel = ViewModelProviders.of(
            this,
            ViewModelFactory(apiInterface)
        ).get(PokemonViewModel::class.java)
    }

    private fun loadPokemon(){
        pokemonViewmodel.getAbility().observe(viewLifecycleOwner){
            it?.let { resource ->
                when(resource.status){
                    Status.SUCCESS -> {
                        val listPokemon = resource.data?.results
                        if (listPokemon!!.isNotEmpty()){
                            binding.rvPokemon.adapter = PokemonAdapter(requireContext(), listPokemon!!, this)
                        }
                    }
                    Status.ERROR -> {

                    }
                    Status.LOADING -> {

                    }
                }
            }
        }
    }

    private fun setView(){
        binding.toolbar.tvTitleToolbar.text = "Pokemon"

    }

    private fun setListener() {
        binding.toolbar.ivFavorit.setOnClickListener {
            findNavController().navigate(R.id.actionToListFavorit)
        }
    }

    override fun selectItem(data: AbilityPokemonResponse.Result) {
        findNavController().navigate(R.id.actionToDetailPokemon,
            bundleOf(
                "name" to data.name,
                "url" to data.url.toString()
            )
        )
    }


}