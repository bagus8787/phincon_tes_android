package com.gib.pokemon_bagus.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gib.pokemon_bagus.databinding.ItemPokemonBinding
import com.gib.pokemon_bagus.helper.viewBinding
import com.gib.pokemon_bagus.model.response.AbilityPokemonResponse

class PokemonAdapter(
    private val context: Context,
    var data: List<AbilityPokemonResponse.Result>,
    val cellClickListener: CellClickListener
) :
    RecyclerView.Adapter<PokemonAdapter.MyViewHolder>() {

    inner class MyViewHolder(binding: ItemPokemonBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var name = binding.name
        var url = binding.url

        var parentLy = binding.parentLayout

        fun bind(data: AbilityPokemonResponse.Result, pos: Int) {

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
    ): PokemonAdapter.MyViewHolder {
        return MyViewHolder(parent.viewBinding(ItemPokemonBinding::inflate))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = data!![position]
        holder.bind(data, position)
    }

    override fun getItemCount(): Int {
        return data!!.size
    }

    interface CellClickListener {
        fun selectItem(data: AbilityPokemonResponse.Result)
    }
}
