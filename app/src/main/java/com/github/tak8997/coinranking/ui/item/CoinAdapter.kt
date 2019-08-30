package com.github.tak8997.coinranking.ui.item

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.github.tak8997.coinranking.data.model.Coin
import com.github.tak8997.coinranking.databinding.ItemCoinBinding

class CoinAdapter: PagedListAdapter<Coin, CoinViewHolder>(object : DiffUtil.ItemCallback<Coin>() {

    override fun areItemsTheSame(oldItem: Coin, newItem: Coin): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Coin, newItem: Coin): Boolean {
        return oldItem.id == newItem.id
    }
}) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        val binding = ItemCoinBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        binding.clickListener = View.OnClickListener {

        }
        return CoinViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}