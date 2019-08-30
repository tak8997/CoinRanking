package com.github.tak8997.coinranking.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.tak8997.coinranking.BaseActivity
import com.github.tak8997.coinranking.R
import com.github.tak8997.coinranking.databinding.ActivityCoinsBinding
import com.github.tak8997.coinranking.ui.coindetail.CoinDetailActivity
import com.github.tak8997.coinranking.ui.item.CoinAdapter
import kotlinx.android.synthetic.main.activity_coins.*

class CoinsActivity : BaseActivity<ActivityCoinsBinding, CoinsViewModel>(), CoinAdapter.OnItemClickListener {

    private val coinAdapter = CoinAdapter()

    override fun getLayoutRes(): Int = R.layout.activity_coins
    override fun getModelClass(): Class<CoinsViewModel> = CoinsViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.fetchCoins()
        viewModel.run {
            pages.observe(this@CoinsActivity, Observer (coinAdapter::submitList))
            returnedItem.observe(this@CoinsActivity, Observer {
                coinAdapter.changeState(it)
            })
            networkErrors.observe(this@CoinsActivity, Observer {
                Toast.makeText(this@CoinsActivity, it.msg, Toast.LENGTH_SHORT).show()
            })
        }

        setupListener()
        setupRecycler()
    }

    override fun itemClicked(id: Int?) {
        startActivityForResult(Intent(this, CoinDetailActivity::class.java).apply {
            putExtra(DEFAULT_PARAM, id)
        }, REQUEST_DETAIL_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        viewModel.onActivityResult(requestCode, resultCode, data)
    }

    private fun setupListener() {
        coinAdapter.setOnItemListener(this)
    }

    private fun setupRecycler() {
        with(coinsRecycler) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = coinAdapter
        }
    }
}
