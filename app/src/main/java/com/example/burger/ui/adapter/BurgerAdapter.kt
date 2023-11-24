package com.example.burger.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.burger.R
import com.example.burger.commons.formattedCurrency
import com.example.burger.databinding.ItemBurgerBinding
import com.example.burger.domain.vo.BurgerVO
import com.squareup.picasso.Picasso

class BurgerAdapter(
    private val listBurger: List<BurgerVO>,
    private val onItemClick: (BurgerVO) -> Unit

) : ListAdapter<BurgerVO, BurgerAdapter.ItemViewHolder>(DiffUtil) {

    inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemBurgerBinding.bind(view)
        fun bind(data: BurgerVO) {
            binding.textNameBurger.text = data.name
            binding.textDescriptionBurger.text = data.desc
            binding.textPriceBurger.text = data.price?.formattedCurrency()

            Picasso
                .get()
                .load(data.imageVO?.get(1)?.lg)
                .into(binding.imageBurger)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder =
        ItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_burger, parent, false)
        )


    override fun getItemCount() = listBurger.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val data = listBurger.getOrNull(position)

        data?.let { data ->
            holder.bind(data)
            holder.itemView.setOnClickListener {
                onItemClick(data)
            }
        }
    }
}

private val DiffUtil = object : DiffUtil.ItemCallback<BurgerVO>() {
    override fun areItemsTheSame(oldItem: BurgerVO, newItem: BurgerVO) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: BurgerVO, newItem: BurgerVO) =
        oldItem == newItem

}