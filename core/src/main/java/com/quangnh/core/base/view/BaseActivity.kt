package com.quangnh.core.base.view

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Window
import android.view.inputmethod.InputMethodManager
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.viewbinding.ViewBinding
import com.quangnh.core.R
import com.quangnh.core.base.utils.ConstantsCore
import com.quangnh.core.base.utils.ErrorUtils
import com.quangnh.core.base.utils.extension.observe
import com.quangnh.core.base.utils.extension.showToastShort
import com.quangnh.core.base.viewmodel.BaseViewModel
import com.quangnh.core.resource.sharepref.AppPreference
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import javax.inject.Inject

/**
 * Created by quangnh
 * Date: 29/1/2023
 * Time: 5:35 PM
 * Project DictionaryApp
 */
abstract class BaseActivity<VB : ViewBinding, VM : BaseViewModel>(
    val bindingFactory: (LayoutInflater) -> VB
) : AppCompatActivity(), BaseBehavior {

    @Inject
    lateinit var appPreference: AppPreference

    lateinit var binding: VB

    abstract val viewModel: VM

    /**
     * Dialog loading view
     */
    private val dialogLoading: Dialog by lazy {
        Dialog(this, R.style.AppTheme_FullScreen_LightStatusBar).apply {
            window?.setBackgroundDrawableResource(R.color.white_50)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = bindingFactory(layoutInflater)
        setContentView(binding.root)

        createLoadingDialog()
        setUpViewModel()
        addViewListener()
        addDataObserver()
        onCommonViewLoaded()

    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase?.let { ViewPumpContextWrapper.wrap(it) })
    }

    override fun addDataObserver() {
        viewModel.apply {
            observe(errorThrowable) { error ->
                onError(error)
            }

            observe(loadingState) { isLoading ->
                onLoading(isLoading)
            }
        }
    }

    override fun onError(error: Any) {
        onLoading(false)
        when (error) {
            is Throwable -> {
                showToastShort(ErrorUtils.errorMessage(error, this))
            }
        }
    }

    override fun onLoading(isLoading: Boolean) {
        if (isLoading) dialogLoading.show()
        else dialogLoading.dismiss()
    }

    override fun onBecomeVisible() {

    }

    override fun onBecomeInvisible() {

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
     * Register for activity result
     * StartActivityForResult is deprecated
     */
    private val launchSomeActivity =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            onDataReceiverActivityForResult(result)
        }

    open fun onDataReceiverActivityForResult(activityResult: ActivityResult) {}

    /**
     * Open activity for result
     */
    fun openActivityForResult(cla: Class<*>, vararg flags: Int) {
        val intent = Intent(this, cla)
        for (flag in flags) {
            intent.addFlags(flag)
        }
        launchSomeActivity.launch(intent)
    }

    /**
     * Open activity for result with bundle data
     */
    fun openActivityForResult(cla: Class<*>, data: Bundle, vararg flags: Int) {
        val intent = Intent(this, cla)
        intent.putExtra(ConstantsCore.KEY_BUNDLE, data)
        for (flag in flags) {
            intent.addFlags(flag)
        }
        launchSomeActivity.launch(intent)
    }

    /**
     * Open activity
     */
    fun openActivity(cla: Class<*>, vararg flags: Int) {
        val intent = Intent(this, cla)
        for (flag in flags) {
            intent.addFlags(flag)
        }
        startActivity(intent)
    }

    /**
     * Open activity with bundle data
     */
    fun openActivity(cla: Class<*>, data: Bundle, vararg flags: Int) {
        val intent = Intent(this, cla)
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

    /**
     * Open fragment
     */
    fun openFragment(
        fragment: BaseFragment<*, *>,
        animate: Boolean,
        tag: String? = null
    ) {
        val tagFrag = tag ?: fragment.getTagFragment()

        addFragment(
            fragment = fragment,
            animate = animate,
            clearStack = false,
            tag = tagFrag
        )
    }

    private fun addFragment(
        fragment: BaseFragment<*, *>,
        animate: Boolean = false,
        clearStack: Boolean = false,
        tag: String? = null
    ) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()

        if (clearStack) {
            while (supportFragmentManager.backStackEntryCount > 0) {
                supportFragmentManager.popBackStackImmediate()
            }
        } else {
            fragmentTransaction.addToBackStack(tag)
        }

        if (supportFragmentManager.findFragmentByTag(tag) != null) {
            return
        }

        if (!animate) {
            fragmentTransaction.setCustomAnimations(0, 0)
        }

        hideKeyboard()

        fragmentTransaction.apply {
            setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            add(android.R.id.content, fragment, tag)
            commitAllowingStateLoss()
        }
    }

    /**
     * Fragment onBecome to Activity
     */
    private fun setFragmentOnBecomeVisible() {
        val fragmentList = supportFragmentManager.fragments.filterIsInstance<BaseFragment<*, *>>()
        if (fragmentList.isNotEmpty()) {
            val fragmentVisible = fragmentList.last()
            fragmentVisible.onBecomeVisible()
        }
    }

    /**
     * Pop and remove fragment
     */
    private fun popFragment(tag: String? = null) {
        hideKeyboard()

        if (supportFragmentManager.backStackEntryCount > 0) {
            if (tag == null) {
                supportFragmentManager.popBackStackImmediate()
            } else {
                supportFragmentManager.popBackStackImmediate(tag, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            }
            setFragmentOnBecomeVisible()
        } else {
            finish()
        }
    }

    /**
     * Hide keyboard android Os
     */
    private fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    /**
     * Create loading dialog
     */
    private fun createLoadingDialog() {
        dialogLoading.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setContentView(R.layout.dialog_loading_view)
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            popFragment()
            return
        }

        super.onBackPressed()
    }

    override fun onDestroy() {
        launchSomeActivity.unregister()
        super.onDestroy()
    }
}