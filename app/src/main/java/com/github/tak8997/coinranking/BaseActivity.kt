package com.github.tak8997.coinranking

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.github.tak8997.coinranking.util.SchedulerProvider
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject


abstract class BaseActivity<VB : ViewDataBinding, VM : ViewModel>: HasSupportFragmentInjector, AppCompatActivity() {

    companion object {
        const val DEFAULT_PARAM = "DEFAULT_PARAM"
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var binding: VB
    lateinit var viewModel: VM

    @Inject
    lateinit var supportFragmentInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var schedulerProvider: SchedulerProvider

    private var lastClickTime = 0L
    private val disposables by lazy {
        CompositeDisposable()
    }

    @LayoutRes
    protected abstract fun getLayoutRes(): Int
    protected abstract fun getModelClass(): Class<VM>

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = supportFragmentInjector
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(getModelClass())

        binding = DataBindingUtil.setContentView(this, getLayoutRes())
        binding.lifecycleOwner = this
        binding.setVariable(BR.view, this)
        binding.setVariable(BR.viewModel, viewModel)
    }

//    override fun onClick(view: View?) {
//        val currentClickTime = SystemClock.uptimeMillis()
//        val elapsedTime = currentClickTime - lastClickTime
//        lastClickTime = currentClickTime
//
//        if (elapsedTime <= OnSingleClickListener.CLICK_INTERVAL) {
//            return
//        }
//        onSingleClick(view)
//    }

    override fun onDestroy() {
        disposables.clear()
        super.onDestroy()
    }
}