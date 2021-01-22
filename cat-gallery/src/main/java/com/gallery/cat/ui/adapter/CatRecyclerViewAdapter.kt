package com.gallery.cat.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gallery.cat.databinding.CatGridItemBinding
import com.gallery.cat.databinding.CatListItemBinding

import com.gallery.cat.network.model.Cat

/**
 * [RecyclerView.Adapter] that displays a [Cat].
 */
class CatRecyclerViewAdapter(private val layoutManager: GridLayoutManager) : RecyclerView.Adapter<CatRecyclerViewAdapter.BaseViewHolder>() {

    private var catList = ArrayList<Cat>()
    private var mOnItemClickListener: OnRecyclerItemClickListener? = null

    // construction method for initiating variables
    companion object {
        const val LIST = 0
        const val GRID = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        // on the basis of variable used, we can change grid or list
        return when(viewType) {
            LIST -> CatListViewHolder.from(parent)
            else -> CatGridViewHolder.from(parent)
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val cat = catList[position]
        holder.onBind(cat)
        holder.itemView.setOnClickListener {
            mOnItemClickListener?.onItemClick(it, cat)
        }
    }

    override fun getItemCount(): Int = catList.size

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getItemViewType(position: Int): Int {
        return if (layoutManager.spanCount == 1) // on the basis [spanCount], list or grid is shown
            LIST
        else
            GRID
    }

    /**
     * adding the cat list received from to adapter
     * @param: [list] - used to populate the adapter items
     */
    fun updateCatList(list: List<Cat>) {
        catList.addAll(list)
        notifyDataSetChanged()
    }

    /**
     * initiating the listener
     *
     * @param:[listener] - use this to assign the class level listener
     */
    fun setOnItemClickListener(listener: OnRecyclerItemClickListener) {
        mOnItemClickListener = listener
    }

    /**
     * View holder class for showing list
     * @param - [CatListItemBinding] - variable use for binding the UI with adapter
     */
    class CatListViewHolder(private val binding: CatListItemBinding) : BaseViewHolder(binding.root) {

        companion object {
            fun from(parent: ViewGroup): CatListViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = CatListItemBinding.inflate(layoutInflater, parent, false)
                return CatListViewHolder(binding)
            }
        }

        override fun onBind(cat: Cat) {
            binding.cat = cat
            binding.executePendingBindings()
        }
    }

    /**
     * View holder class for showing grid
     * @param - [CatGridItemBinding] - variable use for binding the UI with adapter
     */
    class CatGridViewHolder(private val binding: CatGridItemBinding) : BaseViewHolder(binding.root) {

        companion object {
            fun from(parent: ViewGroup): CatGridViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = CatGridItemBinding.inflate(layoutInflater, parent, false)
                return CatGridViewHolder(binding)
            }
        }

        override fun onBind(cat: Cat) {
            binding.cat = cat
            binding.executePendingBindings()
        }
    }

    /**
     * Base view holder class
     * @param - [View] - variable use for binding the UI with adapter
     */
    abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun onBind(cat: Cat)
    }

    /**
     * custom interface to pass the click event on item to the listener
     */
    interface OnRecyclerItemClickListener {
        fun onItemClick(
            item: View?,
            cat: Cat
        )
    }
}