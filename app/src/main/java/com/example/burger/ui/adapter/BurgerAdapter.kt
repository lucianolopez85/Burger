package com.example.burger.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.burger.R
import com.example.burger.commons.formattedCurrency
import com.example.burger.databinding.ItemBurgerBinding
import com.example.burger.domain.vo.BurgerItem
import com.squareup.picasso.Picasso

class BurgerAdapter(
    private var itemList: List<BurgerItem>,
    private val onItemClick: (BurgerItem) -> Unit
) : RecyclerView.Adapter<BurgerAdapter.ItemViewHolder>() {

    fun updateData(newItemList: List<BurgerItem>) {
        itemList = newItemList
        notifyDataSetChanged()
    }

    inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemBurgerBinding.bind(view)
        fun bind(data: BurgerItem) {
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

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: BurgerAdapter.ItemViewHolder, position: Int) {
        val item = itemList[position]
        holder.bind(item)
        holder.itemView.setOnClickListener {
            onItemClick(item)
        }
    }
}