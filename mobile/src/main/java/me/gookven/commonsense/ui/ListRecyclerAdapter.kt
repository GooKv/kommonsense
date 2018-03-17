package me.gookven.commonsense.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class ListRecyclerAdapter<D>(private val view: Int,
                             private val holderFactory: (View) -> DataViewHolder<D>)
    : RecyclerView.Adapter<DataViewHolder<D>>() {

    override fun getItemCount() = items.size

    var items: List<D> = emptyList()
        set (list) {
            field = list
            notifyDataSetChanged()
        }

    fun addItems(list: List<D>) {
        val insertion = itemCount
        items += list
        notifyItemRangeInserted(insertion, itemCount)
    }

    override fun onBindViewHolder(holder: DataViewHolder<D>, position: Int) {
        holder.render(items[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder<D> {
        val view = LayoutInflater
                .from(parent.context)
                .inflate(view, parent, false)
        return holderFactory(view)
    }
}
