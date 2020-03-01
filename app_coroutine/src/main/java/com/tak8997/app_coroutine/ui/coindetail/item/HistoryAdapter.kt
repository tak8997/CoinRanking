package com.tak8997.app_coroutine.ui.coindetail.item

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.tak8997.app_coroutine.R
import com.tak8997.app_coroutine.databinding.ItemHistoryBinding

class HistoryAdapter : RecyclerView.Adapter<HistoryViewHolder>() {

    companion object {
        private const val LIMIT_COUNT = 6
    }

    private val items = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val binding = DataBindingUtil.inflate<ItemHistoryBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_history,
            parent,
            false
        )

        return HistoryViewHolder(binding)
    }

    override fun getItemCount(): Int = items.count()

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun addItems(histories: List<String>?) {
        checkNotNull(histories)

        items.clear()
        histories.forEachIndexed { index, history ->
            if (index > LIMIT_COUNT) { return }
            items.add(history)
        }

        notifyDataSetChanged()
    }
}