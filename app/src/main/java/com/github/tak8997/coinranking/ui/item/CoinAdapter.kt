package com.github.tak8997.coinranking.ui.item

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
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

    interface OnItemClickListener {
        fun itemClicked(id: Int?)
    }

    private var listener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        val binding = ItemCoinBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val viewHolder = CoinViewHolder(binding)

        binding.clickListener = View.OnClickListener {
            val adapterPosition = viewHolder.adapterPosition

            if (adapterPosition != RecyclerView.NO_POSITION) {
                listener?.itemClicked(getItem(adapterPosition)?.id)
            }
        }

        return viewHolder
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun setOnItemListener(listener: OnItemClickListener) {
        this.listener = listener
    }
}