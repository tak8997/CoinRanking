package com.tak8997.app_coroutine.ui.coins.item

import androidx.recyclerview.widget.RecyclerView
import com.tak8997.app_coroutine.data.model.Coin
import com.tak8997.app_coroutine.databinding.ItemCoinBinding

class CoinViewHolder(val binding: ItemCoinBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(coin: Coin?) {
        binding.item = coin
    }
}