package com.github.tak8997.coinranking.ui.coindetail

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.github.tak8997.coinranking.BaseActivity
import com.github.tak8997.coinranking.R
import com.github.tak8997.coinranking.databinding.ActivityCoinDetailBinding
import com.github.tak8997.coinranking.ui.coindetail.item.HistoryAdapter
import com.github.tak8997.coinranking.util.databinding.BindingAdapter
import kotlinx.android.synthetic.main.activity_coin_detail.*

class CoinDetailActivity : BaseActivity<ActivityCoinDetailBinding, CoinDetailViewModel>() {

    private val historyAdapter by lazy {
        HistoryAdapter()
    }

    override fun getLayoutRes(): Int = R.layout.activity_coin_detail
    override fun getModelClass(): Class<CoinDetailViewModel> = CoinDetailViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val id = intent.getIntExtra(DEFAULT_PARAM, -1)
        viewModel.fetchCoin(id)
        viewModel.run {
            result.observe(this@CoinDetailActivity, Observer { coin ->
                coin?.let {
                    Glide.with(this@CoinDetailActivity)
                        .load(it.iconUrl)
                        .placeholder(BindingAdapter.circularProgressDrawable(this@CoinDetailActivity))
                        .into(icon)
                    description.text = it.description
                    volume.text = it.volume.toString()
                    price.text = it.price.toString()
                    change.text = it.change.toString()
                    historyAdapter.addItems(it.history)
                }
            })
            networkState.observe(this@CoinDetailActivity, Observer {
                Toast.makeText(this@CoinDetailActivity, it.msg, Toast.LENGTH_SHORT).show()
            })
        }

        setupRecycler()
    }

    private fun setupRecycler() {
        with(historyRecycler) {
            layoutManager = LinearLayoutManager(this@CoinDetailActivity)
            adapter = historyAdapter
        }
    }
}