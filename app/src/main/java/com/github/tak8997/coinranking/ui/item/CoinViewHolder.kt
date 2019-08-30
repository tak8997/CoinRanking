package com.github.tak8997.coinranking.ui.item

import androidx.recyclerview.widget.RecyclerView
import com.github.tak8997.coinranking.data.model.Coin
import com.github.tak8997.coinranking.databinding.ItemCoinBinding

class CoinViewHolder(private val binding: ItemCoinBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(coin: Coin?) {
        binding.item = coin
    }
}