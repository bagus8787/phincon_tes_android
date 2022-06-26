package com.gib.pokemon_bagus.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.gib.pokemon_bagus.R
import com.gib.pokemon_bagus.adapter.PokemonFavortiAdapter
import com.gib.pokemon_bagus.databinding.FragmentListFavoriteBinding
import com.gib.pokemon_bagus.databinding.FragmentMyPlansBinding
import com.gib.pokemon_bagus.helper.viewBinding
import com.gib.pokemon_bagus.model.PokemonEffect
import com.gib.pokemon_bagus.view.base.BaseFragment
import kotlinx.android.synthetic.main.toolbar_noback.view.*
import kotlinx.coroutines.runBlocking

class ListFavoriteFragment : BaseFragment(R.layout.fragment_list_favorite),
    PokemonFavortiAdapter.CellClickListener {

    private val binding by viewBinding(FragmentListFavoriteBinding::bind)

    private var dataFavorit: List<PokemonEffect>?= mutableListOf()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setView()
        setListener()
    }

    private fun setView(){
        binding.toolbar.tvTitleToolbar.text = "Favorit"
        binding.toolbar.ivFavorit.visibility = View.GONE
        binding.toolbar.ivBack.visibility = View.VISIBLE

        runBlocking {
            val getAll = dbHelper.getAllEffect()
            dataFavorit = getAll
        }

        if (dataFavorit!!.isNotEmpty()){
            binding.viewFlipper.displayedChild = 2
            binding.rvFavorit.adapter = PokemonFavortiAdapter(requireContext(), dataFavorit!!, this)
        } else {
            binding.viewFlipper.displayedChild = 1
        }
    }

    private fun setListener(){
        binding.toolbar.ivBack.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    override fun selectItem(data: PokemonEffect) {
        findNavController().navigate(R.id.actionToDetailPokemon,
            bundleOf(
                "name" to data.nameCategory,
                "url" to data.thisLink.toString()
            )
        )
    }
}