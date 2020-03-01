package com.tak8997.app_coroutine.ui.coindetail

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.tak8997.app_coroutine.ui.coindetail.item.HistoryAdapter
import com.tak8997.app_coroutine.BaseActivity
import com.tak8997.app_coroutine.R
import com.tak8997.app_coroutine.databinding.ActivityCoinDetailBinding

class CoinDetailActivity : BaseActivity<ActivityCoinDetailBinding, CoinDetailViewModel>(), View.OnClickListener {

    private val historyAdapter by lazy {
        HistoryAdapter()
    }

    override fun getLayoutRes(): Int = R.layout.activity_coin_detail
    override fun getModelClass(): Class<CoinDetailViewModel> = CoinDetailViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val id = intent.getIntExtra(DEFAULT_PARAM, -1)
        val favorite = intent.getBooleanExtra(DEFAULT_PARMA2, false)

        viewModel.setFavorite(favorite)
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
            putExtra(DEFAULT_PARAM, viewModel.result.value.apply { this@apply?.favorite = binding.favorite.isSelected })
        })
        finish()
    }

    override fun onClick(view: View?) {
        when(view?.id) {
            R.id.favorite -> viewModel.setFavorite(!view.isSelected)
            R.id.icon -> viewModel.onIconClicked(!view.isSelected)
        }
    }

    private fun setupRecycler() {
        with(binding.historyRecycler) {
            layoutManager = LinearLayoutManager(this@CoinDetailActivity)
            adapter = historyAdapter
        }
    }
}