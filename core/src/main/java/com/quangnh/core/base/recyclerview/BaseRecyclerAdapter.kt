package com.quangnh.core.base.recyclerview

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.quangnh.core.databinding.ItemDefaultBinding

@SuppressLint("NotifyDataSetChanged")
abstract class BaseRecyclerAdapter<T: Any> : RecyclerView.Adapter<BaseViewHolder<*>>() {

    companion object {
        const val VIEW_TYPE_LOADING = 0
        const val VIEW_TYPE_ERROR = 1
        const val VIEW_TYPE_SUCCESS = 2
        const val VIEW_TYPE_EMPTY = 3

        const val ITEM_COUNT_TYPE_LOADING = 1
        const val ITEM_COUNT_TYPE_ERROR = 1
        const val ITEM_COUNT_TYPE_EMPTY = 1
    }

    /**
     * List item data
     */
    private var _itemList: MutableList<T> = ArrayList()

    var itemList: MutableList<T>
        get() = _itemList
        set(value) {
            _itemList = value
        }

    /**
     * Variable type recycle view
     * @see BaseViewHolder::class
     */
    protected var typeRecyclerView: StateRecyclerView = StateRecyclerView.STATE_LOADING

    /**
     * Submit new list item for recyclerview
     * List item is empty: typeRecyclerView = STATE_EMPTY
     * List item is not empty: typeRecyclerView = TYPE_SUCCESS
     */
    open fun submitList(list: List<T>) {
        _itemList.apply {
            clear()
            addAll(list)
        }

        this.typeRecyclerView = if (list.isEmpty()) {
            StateRecyclerView.STATE_EMPTY
        } else {
            StateRecyclerView.STATE_SUCCESS
        }

        notifyDataSetChanged()
    }

    /**
     * Delete all item in list data, then change typeRecyclerView = STATE_EMPTY
     */
    fun clearList() {
        _itemList.clear()
        this.typeRecyclerView = StateRecyclerView.STATE_EMPTY
        notifyDataSetChanged()
    }

    /**
     * Set type for recyclerview
     */
    fun setTypeForRecyclerView(type: StateRecyclerView) {
        this.typeRecyclerView = type
        notifyDataSetChanged()
    }

    /**
     * Set loading to Recyclerview
     */
    fun setLoading() {
        this.typeRecyclerView = StateRecyclerView.STATE_LOADING
        notifyDataSetChanged()
    }

    /**
     * Set empty to Recyclerview
     */
    fun setEmpty() {
        this.typeRecyclerView = StateRecyclerView.STATE_EMPTY
        notifyDataSetChanged()
    }

    /**
     * Set error to Recyclerview
     */
    fun setError() {
        this.typeRecyclerView = StateRecyclerView.STATE_ERROR
        notifyDataSetChanged()
    }

    /**
     * Get default view-holder
     */
    fun getDefaultViewHolder(parent: ItemDefaultBinding): BaseViewHolder<ItemDefaultBinding> {
        return DefaultViewHolder(parent)
    }

    /**
     * Custom loading ViewHolder
     * @param parent: ViewGroup
     * @return @BaseViewHolder
     */
    abstract fun setLoadingViewHolder(parent: ViewGroup): BaseViewHolder<*>?

    /**
     * Custom empty ViewHolder
     * @param parent: ViewGroup
     * @return @BaseViewHolder
     */
    abstract fun setEmptyViewHolder(parent: ViewGroup): BaseViewHolder<*>?

    /**
     * Custom error ViewHolder
     * @param parent: ViewGroup
     * @return @BaseViewHolder
     */
    abstract fun setErrorViewHolder(parent: ViewGroup): BaseViewHolder<*>?

    /**
     * Set ViewHolder for Recyclerview when typeRecyclerView = STATE_SUCCESS
     * @param parent: ViewGroup
     * @return @BaseViewHolder
     */
    abstract fun setNormalViewHolder(parent: ViewGroup): BaseViewHolder<*>?

    /**
     * On create view holder
     * @see 4 type -> LOADING, SUCCESS, ERROR, EMPTY, DEFAULT
     * @return BaseViewHolder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        when (viewType) {
            VIEW_TYPE_LOADING -> {
                return setLoadingViewHolder(parent = parent) ?: getDefaultViewHolder(parent = ItemDefaultBinding.inflate(LayoutInflater.from(parent.context)))
            }

            VIEW_TYPE_SUCCESS -> {
                return setNormalViewHolder(parent = parent) ?: getDefaultViewHolder(parent = ItemDefaultBinding.inflate(LayoutInflater.from(parent.context)))
            }

            VIEW_TYPE_EMPTY -> {
                return setEmptyViewHolder(parent = parent) ?: getDefaultViewHolder(parent = ItemDefaultBinding.inflate(LayoutInflater.from(parent.context)))
            }

            VIEW_TYPE_ERROR -> {
                return setErrorViewHolder(parent = parent) ?: getDefaultViewHolder(parent = ItemDefaultBinding.inflate(LayoutInflater.from(parent.context)))
            }
        }
        return getDefaultViewHolder(parent = ItemDefaultBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        holder.bindData(position)
    }

    override fun getItemViewType(position: Int): Int {
        return when (this.typeRecyclerView) {
            StateRecyclerView.STATE_LOADING -> {
                VIEW_TYPE_LOADING
            }
            StateRecyclerView.STATE_SUCCESS -> {
                VIEW_TYPE_SUCCESS
            }
            StateRecyclerView.STATE_EMPTY -> {
                VIEW_TYPE_EMPTY
            }
            StateRecyclerView.STATE_ERROR -> {
                VIEW_TYPE_ERROR
            }
        }
    }

    override fun getItemCount(): Int {
        return when (this.typeRecyclerView) {
            StateRecyclerView.STATE_LOADING -> {
                ITEM_COUNT_TYPE_LOADING
            }
            StateRecyclerView.STATE_SUCCESS -> {
                _itemList.size
            }
            StateRecyclerView.STATE_EMPTY -> {
                ITEM_COUNT_TYPE_EMPTY
            }
            StateRecyclerView.STATE_ERROR -> {
                ITEM_COUNT_TYPE_ERROR
            }
        }
    }

    class DefaultViewHolder(
        viewBinding: ItemDefaultBinding
    ): BaseViewHolder<ItemDefaultBinding>(viewBinding) {
        override fun bindData(position: Int) {}
    }
}