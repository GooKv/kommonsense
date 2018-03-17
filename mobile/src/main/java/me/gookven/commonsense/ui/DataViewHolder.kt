package me.gookven.commonsense.ui

import android.support.v7.widget.RecyclerView
import android.view.View

abstract class DataViewHolder<in T>(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun render(item: T)
}