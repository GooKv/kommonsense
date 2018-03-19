package me.gookven.commonsense.activity

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.activity_main.*
import me.gookven.commonsense.App
import me.gookven.commonsense.R
import me.gookven.commonsense.api.dto.Comment
import me.gookven.commonsense.mvp.presenter.CommentsPresenter
import me.gookven.commonsense.mvp.view.CommentsView
import me.gookven.commonsense.mvp.view.ErrorHandler
import me.gookven.commonsense.ui.CommentViewHolder
import me.gookven.commonsense.ui.ListRecyclerAdapter

class CommentsActivity :
        MvpAppCompatActivity(),
        CommentsView,
        ErrorHandler
{
    @InjectPresenter lateinit var commentsPresenter: CommentsPresenter

    private val recycleAdapter = ListRecyclerAdapter(R.layout.comment, ::CommentViewHolder)

    private lateinit var errorHandlerDelegate: ErrorHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        App.component.inject(this)

        loadComments.setOnClickListener { commentsPresenter.loadComments() }

        commentsRecycler.layoutManager = LinearLayoutManager(this)
        commentsRecycler.adapter = recycleAdapter

        errorHandlerDelegate = AlertErrorHandler(this)
    }

    override fun renderComments(comments: List<Comment>) {
        recycleAdapter.items = comments
    }

    override fun handleError(exception: Throwable) {
        errorHandlerDelegate.handleError(exception)
    }

    class AlertErrorHandler(private val errorDialog: AlertDialog) : ErrorHandler {
        constructor(activity: Activity) :
                this(AlertDialog.Builder(activity).setTitle(R.string.app_name).create())

        override fun handleError(exception: Throwable) {
            errorDialog.setMessage(exception.message)
            errorDialog.show()
        }
    }
}
