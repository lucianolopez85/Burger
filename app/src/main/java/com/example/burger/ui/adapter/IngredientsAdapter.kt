package com.example.burger.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.burger.R
import com.example.burger.databinding.ItemIngredientsBinding
import com.example.burger.domain.vo.IngredientVO
import com.squareup.picasso.Picasso

class IngredientsAdapter : ListAdapter<IngredientVO, IngredientsAdapter.ItemViewHolder>(DiffUtil) {

    inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemIngredientsBinding.bind(view)
        fun bind(data: IngredientVO) {
            binding.ingredientTitle.text = data.name
            Picasso.get()
                .load(data.img)
                .into(binding.ingredientImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder =
        ItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_ingredients, parent, false)
        )

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        getItem(position)?.let { ingredient ->
            holder.bind(ingredient)
        }
    }
}

private val DiffUtil = object : DiffUtil.ItemCallback<IngredientVO>() {
    override fun areItemsTheSame(oldItem: IngredientVO, newItem: IngredientVO) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: IngredientVO, newItem: IngredientVO) =
        oldItem == newItem
}