package com.github.tak8997.coinranking.ui.coindetail

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.tak8997.coinranking.BaseActivity
import com.github.tak8997.coinranking.R
import com.github.tak8997.coinranking.databinding.ActivityCoinDetailBinding
import com.github.tak8997.coinranking.ui.coindetail.item.HistoryAdapter
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
            result.observe(this@CoinDetailActivity, Observer {
                historyAdapter.addItems(it.history)
            })
            networkState.observe(this@CoinDetailActivity, Observer {
                Toast.makeText(this@CoinDetailActivity, it.msg, Toast.LENGTH_SHORT).show()
            })
        }

        setupRecycler()
    }

    override fun onBackPressed() {
        setResult(Activity.RESULT_OK, Intent().apply {
            putExtra(DEFAULT_PARAM, viewModel.result.value)
        })
        finish()
    }

    private fun setupRecycler() {
        with(historyRecycler) {
            layoutManager = LinearLayoutManager(this@CoinDetailActivity)
            adapter = historyAdapter
        }
    }
}