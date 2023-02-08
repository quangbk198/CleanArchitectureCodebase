package com.example.dictionaryapp.features.history.ui

import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.dictionaryapp.R
import com.example.dictionaryapp.databinding.ActivityHistoryBinding
import com.example.dictionaryapp.features.history.adapter.HistoryWordAdapter
import com.example.dictionaryapp.features.history.viewmodel.HistoryViewModel
import com.example.dictionaryapp.utils.ConstantApp.WordHistoryAdapter.VIEW_TYPE_DATE
import com.quangnh.core.base.view.BaseActivity
import com.quangnh.core.utils.extension.observe
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HistoryActivity : BaseActivity<ActivityHistoryBinding, HistoryViewModel>(ActivityHistoryBinding::inflate) {

    @Inject
    lateinit var historyWordAdapter: HistoryWordAdapter

    @Inject
    lateinit var gridLayoutManager: GridLayoutManager

    override val viewModel: HistoryViewModel by viewModels()

    override fun onCommonViewLoaded() {
        title = getString(R.string.history)

        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                if (VIEW_TYPE_DATE == historyWordAdapter.getItemViewType(position)) {
                    return 2
                }

                return 1
            }
        }

        binding.rcvHistoryWord.apply {
            layoutManager = gridLayoutManager
            adapter = historyWordAdapter
        }
        historyWordAdapter.setLoading()
    }

    override fun addViewListener() {
        viewModel.apply {
            observe(listWordLocal) {
                historyWordAdapter.submitList(it)
            }
        }
    }
}