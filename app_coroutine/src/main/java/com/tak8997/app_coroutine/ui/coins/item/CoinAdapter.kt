package com.tak8997.app_coroutine.ui.coins.item

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.tak8997.app_coroutine.R
import com.tak8997.app_coroutine.data.model.Coin
import com.tak8997.app_coroutine.databinding.ItemCoinBinding

class CoinAdapter: PagedListAdapter<Coin, CoinViewHolder>(object : DiffUtil.ItemCallback<Coin>() {

    override fun areItemsTheSame(oldItem: Coin, newItem: Coin): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Coin, newItem: Coin): Boolean {
        return oldItem.id == newItem.id
    }
}) {

    interface OnItemClickListener {
        fun itemClicked(id: Int?, favorite: Boolean? = false)
    }

    private var listener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        val binding = DataBindingUtil.inflate<ItemCoinBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_coin,
            parent,
            false
        )

//        binding.clickListener = View.OnClickListener {
//            val adapterPosition = viewHolder.adapterPosition
//
//            if (adapterPosition != RecyclerView.NO_POSITION) {
//                val item = getItem(adapterPosition)
//                listener?.itemClicked(item?.id, item?.favorite)
//            }
//        }

        return CoinViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int, payloads: MutableList<Any>) {
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position)
        } else {
            val favorite = payloads[0] as? Boolean
//            holder.binding.favorite.isSelected = favorite == true
        }
    }

    fun setOnItemListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    fun changeState(item: Coin?) {
        currentList?.forEachIndexed { index, coin ->
            if (coin.id == item?.id) {
                coin.favorite = item.favorite
                notifyItemChanged(index, item.favorite)
                return
            }
        }
    }
}