package com.example.burger.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.burger.commons.formattedCurrency
import com.example.burger.databinding.ItemBurgerBinding
import com.example.burger.domain.vo.BurgerVO
import com.squareup.picasso.Picasso

class BurgerAdapter(
    private val listBurger: List<BurgerVO>,
//    private val onClick: (Int) -> Unit

): RecyclerView.Adapter<BurgerAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemBurgerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun getItemCount() = listBurger.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val burger = listBurger[position]

        holder.binding.textNameBurger.text = burger.name
        holder.binding.textDescriptionBurger.text = burger.desc
        holder.binding.textValueBurger.text = burger.price?.formattedCurrency()

        holder.itemView.setOnClickListener {
        }

    }

    inner class MyViewHolder(val binding: ItemBurgerBinding) : RecyclerView.ViewHolder(binding.root)
}