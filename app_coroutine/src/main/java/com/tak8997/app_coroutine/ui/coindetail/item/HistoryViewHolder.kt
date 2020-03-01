package com.tak8997.app_coroutine.ui.coindetail.item

import androidx.recyclerview.widget.RecyclerView
import com.tak8997.app_coroutine.databinding.ItemHistoryBinding

class HistoryViewHolder(private val binding: ItemHistoryBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: String) {
        binding.item = item
    }
}
