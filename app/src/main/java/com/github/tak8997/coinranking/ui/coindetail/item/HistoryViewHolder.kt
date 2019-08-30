package com.github.tak8997.coinranking.ui.coindetail.item

import androidx.recyclerview.widget.RecyclerView
import com.github.tak8997.coinranking.databinding.ItemHistoryBinding

class HistoryViewHolder(private val binding: ItemHistoryBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: String) {
        binding.item = item
    }
}
