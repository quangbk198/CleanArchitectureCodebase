package com.example.dictionaryapp.features.main.ui

import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.lifecycle.lifecycleScope
import com.example.dictionaryapp.R
import com.example.dictionaryapp.databinding.ActivityMainBinding
import com.example.dictionaryapp.features.history.ui.HistoryActivity
import com.example.dictionaryapp.features.main.viewmodel.MainViewModel
import com.example.dictionaryapp.features.settings.ui.SettingsActivity
import com.google.android.material.navigation.NavigationView
import com.quangnh.core.utils.extension.delayFunction
import com.quangnh.core.utils.extension.observe
import com.quangnh.core.utils.extension.safeOnClickListener
import com.quangnh.core.base.view.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity :
    BaseActivity<ActivityMainBinding, MainViewModel>(ActivityMainBinding::inflate),
    NavigationView.OnNavigationItemSelectedListener
{

    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle

    override val viewModel: MainViewModel by viewModels()

    override fun onCommonViewLoaded() {
        setupNavigationDrawer()
    }

    override fun addViewListener() {
        binding.apply {
            btnSearch.safeOnClickListener {
                viewModel.searchWord(edtSearch.text.toString().trim())
            }
        }
    }

    override fun addDataObserver() {
        super.addDataObserver()

        viewModel.apply {
            observe(wordInfo) { word ->
                binding.tvDefinition.text = word.definitions?.get(0) ?: ""
            }
        }
    }

    /**
     * Setup navigation drawer
     */
    private fun setupNavigationDrawer() {
        actionBarDrawerToggle = ActionBarDrawerToggle(this, binding.myDrawerLayout, R.string.nav_open, R.string.nav_close)

        // pass the Open and Close toggle for the drawer layout listener
        // to toggle the button
        binding.myDrawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
        binding.navigationView.setNavigationItemSelectedListener(this)

        // to make the Navigation drawer icon always appear on the action bar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    // The item click listener callback to open and close the navigation drawer when the icon is clicked
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            true
        } else super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        binding.myDrawerLayout.closeDrawer(GravityCompat.START)

        when (item.itemId) {
            R.id.nav_settings -> {
                delayFunction(lifecycleScope, 300) {
                    openActivity(SettingsActivity::class.java)
                }
            }

            R.id.nav_history -> {
                delayFunction(lifecycleScope, 300) {
                    openActivity(HistoryActivity::class.java)
                }
            }
        }

        return true
    }

    override fun onBackPressed() {
        if (binding.myDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.myDrawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}