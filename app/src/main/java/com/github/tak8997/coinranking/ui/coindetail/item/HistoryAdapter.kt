package com.github.tak8997.coinranking.ui.coindetail.item

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.tak8997.coinranking.databinding.ItemHistoryBinding

class HistoryAdapter : RecyclerView.Adapter<HistoryViewHolder>() {

    companion object {
        private const val LIMIT_COUNT = 6
    }

    private val items = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        return HistoryViewHolder(
            ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
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