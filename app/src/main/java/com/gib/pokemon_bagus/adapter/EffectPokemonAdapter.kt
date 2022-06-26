package com.gib.pokemon_bagus.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gib.pokemon_bagus.databinding.ItemEffectPokemonBinding
import com.gib.pokemon_bagus.helper.viewBinding
import com.gib.pokemon_bagus.model.PokemonEffect

class EffectPokemonAdapter(
    private val context: Context,
    var data: List<PokemonEffect>,
    val cellClickListener: CellClickListener
) :
    RecyclerView.Adapter<EffectPokemonAdapter.MyViewHolder>() {

    inner class MyViewHolder(binding: ItemEffectPokemonBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var effect = binding.effect

        var name = binding.name
        var url = binding.url

        var parentLy = binding.parentLayout

        fun bind(data: PokemonEffect, pos: Int) {

            effect.text = data.effect
            name.text = data.name
            url.text = data.url

            parentLy.setOnClickListener {
                cellClickListener.selectItem(data)

            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EffectPokemonAdapter.MyViewHolder {
        return MyViewHolder(parent.viewBinding(ItemEffectPokemonBinding::inflate))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = data!![position]
        holder.bind(data, position)
    }

    override fun getItemCount(): Int {
        return data!!.size
    }

    interface CellClickListener {
        fun selectItem(data: PokemonEffect)
    }
}
