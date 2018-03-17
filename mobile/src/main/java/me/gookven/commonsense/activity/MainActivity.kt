package me.gookven.commonsense.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import me.gookven.commonsense.App
import me.gookven.commonsense.R
import me.gookven.commonsense.api.PlaceholderApi
import me.gookven.commonsense.api.dto.Comment
import me.gookven.commonsense.ui.CommentViewHolder
import me.gookven.commonsense.ui.ListRecyclerAdapter
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject lateinit var placeholderApi: PlaceholderApi

    private val recycleAdapter = ListRecyclerAdapter(R.layout.comment, ::CommentViewHolder)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        App.component.inject(this)

        loadComments.setOnClickListener {
            placeholderApi
                    .comments()
                    .subscribe(this::onCommentsFetched)
        }

        commentsRecycler.layoutManager = LinearLayoutManager(this)
        commentsRecycler.adapter = recycleAdapter
    }

    fun onCommentsFetched(comments: List<Comment>) {
        recycleAdapter.items = comments
    }

}