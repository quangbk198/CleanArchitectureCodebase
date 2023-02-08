package com.example.dictionaryapp.features.history.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.dictionaryapp.R
import com.example.dictionaryapp.databinding.ItemDateTimeHistoryBinding
import com.example.dictionaryapp.databinding.ItemWordBinding
import com.example.dictionaryapp.databinding.LayoutErrorOrEmptyBinding
import com.quangnh.domain.model.recyclerview.BaseModelHistoryWord
import com.example.dictionaryapp.utils.ConstantApp.WordHistoryAdapter.VIEW_TYPE_DATE
import com.example.dictionaryapp.utils.ConstantApp.WordHistoryAdapter.VIEW_TYPE_WORD
import com.quangnh.core.base.recyclerview.BaseRecyclerAdapter
import com.quangnh.core.base.recyclerview.BaseViewHolder
import com.quangnh.core.base.recyclerview.StateRecyclerView
import com.quangnh.core.databinding.DialogLoadingViewBinding
import com.quangnh.core.databinding.ItemDefaultBinding
import com.quangnh.domain.model.WordInfo
import com.quangnh.domain.model.recyclerview.DateTimeObject

class HistoryWordAdapter : BaseRecyclerAdapter<BaseModelHistoryWord>(){
    override fun setLoadingViewHolder(parent: ViewGroup): BaseViewHolder<*> =
        LoadingViewHolder(
            DialogLoadingViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun setEmptyViewHolder(parent: ViewGroup): BaseViewHolder<*> =
        EmptyViewHolder(
            LayoutErrorOrEmptyBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun setErrorViewHolder(parent: ViewGroup): BaseViewHolder<*>? = null

    override fun setNormalViewHolder(parent: ViewGroup): BaseViewHolder<*>? = null

    private fun setDateViewHolder(parent: ViewGroup): BaseViewHolder<*> =
        DateTimeViewHolder(
            ItemDateTimeHistoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    private fun setWordViewHolder(parent: ViewGroup): BaseViewHolder<*> =
        WordViewHolder(
            ItemWordBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemViewType(position: Int): Int {
        return when (this.typeRecyclerView) {
            StateRecyclerView.STATE_LOADING -> {
                VIEW_TYPE_LOADING
            }
            StateRecyclerView.STATE_SUCCESS -> {
                val baseWord = itemList[position]

                if (baseWord.getModelType() == BaseModelHistoryWord.TYPE_DATE) {
                    VIEW_TYPE_DATE
                } else {
                    VIEW_TYPE_WORD
                }
            }
            StateRecyclerView.STATE_EMPTY -> {
                VIEW_TYPE_EMPTY
            }
            StateRecyclerView.STATE_ERROR -> {
                VIEW_TYPE_ERROR
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        when (viewType) {
            VIEW_TYPE_LOADING -> {
                return setLoadingViewHolder(parent = parent)
            }

            VIEW_TYPE_EMPTY -> {
                return setEmptyViewHolder(parent = parent)
            }

            VIEW_TYPE_ERROR -> {
                return setErrorViewHolder(parent = parent) ?: getDefaultViewHolder(parent = ItemDefaultBinding.inflate(LayoutInflater.from(parent.context)))
            }

            VIEW_TYPE_DATE -> {
                return setDateViewHolder(parent = parent)
            }

            VIEW_TYPE_WORD -> {
                return setWordViewHolder(parent = parent)
            }
        }
        return getDefaultViewHolder(parent = ItemDefaultBinding.inflate(LayoutInflater.from(parent.context)))
    }

    class LoadingViewHolder(
        viewBinding: DialogLoadingViewBinding
    ): BaseViewHolder<DialogLoadingViewBinding>(viewBinding) {
        override fun bindData(position: Int) {}
    }

    class EmptyViewHolder(
        viewBinding: LayoutErrorOrEmptyBinding
    ): BaseViewHolder<LayoutErrorOrEmptyBinding>(viewBinding) {
        override fun bindData(position: Int) {
            binding.tvStatus.text = itemView.context.getString(R.string.empty_search_word)
        }
    }

    inner class DateTimeViewHolder(
        viewBinding: ItemDateTimeHistoryBinding
    ): BaseViewHolder<ItemDateTimeHistoryBinding>(viewBinding) {
        override fun bindData(position: Int) {
            val dateTime = itemList[position] as DateTimeObject
            binding.tvDateTime.text = dateTime.date
        }
    }

    inner class WordViewHolder(
        viewBinding: ItemWordBinding
    ): BaseViewHolder<ItemWordBinding>(viewBinding) {
        override fun bindData(position: Int) {
            val word = itemList[position] as WordInfo
            binding.tvWord.text = word.word
        }
    }
}