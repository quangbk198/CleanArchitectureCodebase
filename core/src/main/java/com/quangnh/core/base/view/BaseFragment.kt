package com.quangnh.core.base.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.quangnh.core.base.utils.ConstantsCore
import com.quangnh.core.base.utils.extension.observe
import com.quangnh.core.base.viewmodel.BaseViewModel
import com.quangnh.core.resource.sharepref.AppPreference
import javax.inject.Inject

/**
 * Created by quangnh
 * Date: 29/1/2023
 * Time: 5:35 PM
 * Project DictionaryApp
 */
abstract class BaseFragment<VB : ViewBinding, VM : BaseViewModel>(
    val bindingFactory: (LayoutInflater) -> VB
) : Fragment(), BaseBehavior {

    @Inject
    lateinit var appPreference: AppPreference

    lateinit var binding: VB

    abstract val viewModel: VM

    abstract fun getTagFragment(): String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = bindingFactory(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addViewListener()
        addDataObserver()
        onCommonViewLoaded()
    }

    override fun addDataObserver() {
        viewModel.apply {
            activity?.observe(errorThrowable) {
                onError(it)
            }

            activity?.observe(loadingState) {
                onLoading(it)
            }
        }
    }

    /**
     * Calling onLoading in Activity with default
     */
    override fun onLoading(isLoading: Boolean) {
        if (activity is BaseActivity<*, *>) {
            (activity as BaseActivity<*, *>).onLoading(isLoading)
        }
    }

    /**
     * Calling onError in Activity with default
     */
    override fun onError(error: Any) {
        if (activity is BaseActivity<*, *>) {
            (activity as BaseActivity<*, *>).onError(error)
        }
    }

    override fun onBecomeInvisible() {
        if (activity is BaseActivity<*, *>) {
            (activity as BaseActivity<*, *>).onBecomeInvisible()
        }
    }

    override fun onBecomeVisible() {
        if (activity is BaseActivity<*, *>) {
            (activity as BaseActivity<*, *>).onBecomeVisible()
        }
    }

    /**
     * Set up viewmodel when onCreate activity
     */
    private fun setUpViewModel() {
        if (!viewModel.isInitialized) {
            viewModel.initViewModel(appPreference)
            viewModel.onDidBindViewModel()
        }
    }

    /**
     * Open activity
     */
    fun openActivity(cla: Class<*>, vararg flags: Int) {
        val intent = Intent(activity, cla)
        for (flag in flags) {
            intent.addFlags(flag)
        }
        startActivity(intent)
    }

    /**
     * Open activity with bundle data
     */
    fun openActivity(cla: Class<*>, data: Bundle, vararg flags: Int) {
        val intent = Intent(activity, cla)
        intent.putExtra(ConstantsCore.KEY_BUNDLE, data)
        for (flag in flags) {
            intent.addFlags(flag)
        }
        startActivity(intent)
    }

    /**
     * Open activity with action
     */
    fun openActivityWithAction(action: String) {
        val intent = Intent(action)
        startActivity(intent)
    }
}