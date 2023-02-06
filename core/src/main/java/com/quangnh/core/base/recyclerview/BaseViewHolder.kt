package com.quangnh.core.base.recyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.quangnh.core.databinding.ItemEmptyBinding
import com.quangnh.core.utils.extension.*

/**
 * Created by quangnh
 * Date: 06/2/2023
 * Time: 09:15 AM
 * Project DictionaryApp
 */
abstract class BaseViewHolder<T: ViewBinding>(
    open val binding: T
): RecyclerView.ViewHolder(binding.root) {
    abstract fun bindData(position: Int)
}

class EmptyViewHolder: BaseViewHolder<ItemEmptyBinding> {

    constructor(itemEmptyBinding: ItemEmptyBinding) : super(itemEmptyBinding)

    constructor(itemEmptyBinding: ItemEmptyBinding, contentEmpty: String, imageEmpty: Int) : super(itemEmptyBinding) {
        binding.tvErrorContent.text = contentEmpty
        if (imageEmpty == 0) {
            binding.ivEmpty.hide()
        } else {
            binding.ivEmpty.visibility= View.INVISIBLE
            binding.ivEmpty.loadImageDrawable(imageEmpty)
        }
    }

    constructor(itemEmptyBinding: ItemEmptyBinding, contentEmpty: String) : super(itemEmptyBinding) {
        binding.tvErrorContent.text = contentEmpty
        binding.root.setHeightView(getScreenHeight()- dpToPx(56F))
    }

    constructor(itemEmptyBinding: ItemEmptyBinding, contentEmpty: String, imageEmpty: Int, sizeIcon: Float) : super(itemEmptyBinding) {
        binding.tvErrorContent.text = contentEmpty
        binding.ivEmpty.apply {
            loadImageDrawable(imageEmpty)
            setWidthHeightToView(dpToPx(sizeIcon), dpToPx(sizeIcon))
        }
    }

    override fun bindData(position: Int) {

    }
}