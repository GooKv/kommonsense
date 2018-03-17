package me.gookven.commonsense.ui

import android.view.View
import android.widget.TextView
import me.gookven.commonsense.R
import me.gookven.commonsense.api.dto.Comment

class CommentViewHolder(view: View) : DataViewHolder<Comment>(view) {
    override fun render(item: Comment) {
        itemView.findViewById<TextView>(R.id.comment_name).text = item.name
    }
}