package com.gib.pokemon_bagus.view.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.gib.pokemon_bagus.R
import com.gib.pokemon_bagus.adapter.EffectPokemonAdapter
import com.gib.pokemon_bagus.data.viewmodel.PokemonViewModel
import com.gib.pokemon_bagus.data.viewmodel.ViewModelFactory
import com.gib.pokemon_bagus.databinding.FragmentDetailPokemonBinding
import com.gib.pokemon_bagus.helper.viewBinding
import com.gib.pokemon_bagus.model.PokemonEffect
import com.gib.pokemon_bagus.model.Status
import com.gib.pokemon_bagus.view.base.BaseFragment
import kotlinx.coroutines.runBlocking

class DetailPokemonFragment : BaseFragment(R.layout.fragment_detail_pokemon),
    EffectPokemonAdapter.CellClickListener {

    private val binding by viewBinding(FragmentDetailPokemonBinding::bind)

    private var idUrl = 0
    private var thisLinkAbility = ""
    private var name = ""

    private lateinit var pokemonViewModel: PokemonViewModel

    private var dataEffect: MutableList<PokemonEffect>?= mutableListOf()

    private var getItemByUrl: List<PokemonEffect>?= mutableListOf()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
        setView()
        loadPokemonById()
        setListener()
    }

    private fun initViewModel(){
        pokemonViewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(apiInterface)
        ).get(PokemonViewModel::class.java)
    }

    private fun loadPokemonById(){
        pokemonViewModel.getAbilityById(
            idUrl.toString()
        ).observe(viewLifecycleOwner){
            it?.let { resource ->
                when(resource.status){
                    Status.SUCCESS -> {
                        if (resource.data?.effect_changes!!.isNotEmpty()){
                            val list = resource.data.effect_changes

                            dataEffect = mutableListOf()
                            for (data in list){
                                for (lists in data.effect_entries){
                                    dataEffect?.add(
                                        PokemonEffect(
                                            idUrl,
                                            name,
                                            thisLinkAbility,
                                            lists.effect,
                                            lists.language.name,
                                            lists.language.url
                                        )
                                    )
                                }
                            }
                            binding.rvEffectChanges.adapter = EffectPokemonAdapter(requireContext(), dataEffect!!, this)
                        }

                        if (resource.data?.flavor_text_entries!!.isNotEmpty()){
                            val list = resource.data.flavor_text_entries

                            dataEffect = mutableListOf()
                            for (data in list){
                                dataEffect?.add(
                                    PokemonEffect(
                                        idUrl,
                                        name,
                                        thisLinkAbility,
                                        data.flavor_text,
                                        data.language.name,
                                        data.language.url
                                    )
                                )
                            }
                            binding.rvFlavorTextEntries.adapter = EffectPokemonAdapter(requireContext(), dataEffect!!, this)
                        }

                        if (resource.data?.effect_entries!!.isNotEmpty()){
                            val list = resource.data.effect_entries

                            dataEffect = mutableListOf()
                            for (data in list){
                                dataEffect?.add(
                                    PokemonEffect(
                                        idUrl,
                                        name,
                                        thisLinkAbility,
                                        data.effect,
                                        data.language.name,
                                        data.language.url
                                    )
                                )
                            }

                            binding.rvEffectEntries.adapter = EffectPokemonAdapter(requireContext(), dataEffect!!, this)
                        }
                        pLoading.dismissDialog()
                    }
                    Status.ERROR -> {
                        pLoading.dismissDialog()
                    }

                    Status.LOADING -> {
                        pLoading.showLoading()
                    }
                }
            }
        }
    }

    private fun setView(){
        name = arguments?.getString("name")?: ""

        val url = arguments?.getString("url")?: ""
        val ids = method(url)

        thisLinkAbility = ids

        idUrl = ids.substring(ids.lastIndexOf("/") + 1).toInt()
//        idUrl = 1
//        toast(idUrl.toString())

        binding.toolbar.tvTitleToolbar.text = "Detail Pokemon"
        binding.toolbar.ivBack.visibility = View.VISIBLE
//        binding.toolbar.ivFavorit.visibility = View.GONE

        runBlocking {
            getItemByUrl = dbHelper.getItemByUrl(thisLinkAbility)

            if (getItemByUrl!!.isEmpty()){
                binding.toolbar.ivFavorit.setImageResource(R.drawable.ic_baseline_favorite_border_24)
            } else {
                binding.toolbar.ivFavorit.setImageResource(R.drawable.ic_baseline_favorite_24)
            }
        }

    }

    fun method(str: String): String {
        var str = str
        if (str != null && str.length > 0 && str[str.length - 1] == '/') {
            str = str.substring(0, str.length - 1)
        }
        return str
    }

    private fun setListener(){
        binding.toolbar.ivBack.setOnClickListener {
            activity?.onBackPressed()
        }

        binding.toolbar.ivFavorit.setOnClickListener {
            runBlocking {
                for (datas in dataEffect!!){
                    val dataGejalas = PokemonEffect(
                        id = datas.id,
                        nameCategory = name,
                        thisLink = thisLinkAbility ,
                        effect = "",
                        name = "",
                        url = ""
                    )

                    if (getItemByUrl!!.isEmpty()){
                        dbHelper.insertEffect(dataGejalas)
                        binding.toolbar.ivFavorit.setImageResource(R.drawable.ic_baseline_favorite_24)
                        toast("Berhasil menambahkan ke favorit!")

                    } else {
                        for (data in getItemByUrl!!){
                            dbHelper.deleteById(data.id)
                        }

                        toast("Effect diihapus dari favorit!")
                        binding.toolbar.ivFavorit.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                    }

                }
            }

            setView()

            return@setOnClickListener
        }
    }

    override fun selectItem(data: PokemonEffect) {

    }
}