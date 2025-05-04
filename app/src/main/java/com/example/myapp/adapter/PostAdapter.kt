package com.example.myapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myapp.R
import com.example.myapp.diffutil.PostDiffCallback
import com.example.myapp.model.PostItem

class PostAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var items = emptyList<PostItem>()

    fun updateList(newItems: List<PostItem>) {
        val diffResult = DiffUtil.calculateDiff(PostDiffCallback(items, newItems))
        items = newItems
        diffResult.dispatchUpdatesTo(this)
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is PostItem.AuthorPost -> AUTHOR_POST_VIEW_TYPE
            is PostItem.ImagePost -> IMAGE_POST_VIEW_TYPE
            is PostItem.TextWithButtonPost -> TEXT_BUTTON_POST_VIEW_TYPE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            AUTHOR_POST_VIEW_TYPE -> AuthorPostViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_author_post, parent, false)
            )
            IMAGE_POST_VIEW_TYPE -> ImagePostViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_image_post, parent, false)
            )
            TEXT_BUTTON_POST_VIEW_TYPE -> TextButtonPostViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_text_button_post, parent, false)
            )
            else -> throw IllegalArgumentException("Unknown view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = items[position]) {
            is PostItem.AuthorPost -> (holder as AuthorPostViewHolder).bind(item)
            is PostItem.ImagePost -> (holder as ImagePostViewHolder).bind(item)
            is PostItem.TextWithButtonPost -> (holder as TextButtonPostViewHolder).bind(item)
        }
    }

    override fun getItemCount() = items.size

    companion object {
        private const val AUTHOR_POST_VIEW_TYPE = 0
        private const val IMAGE_POST_VIEW_TYPE = 1
        private const val TEXT_BUTTON_POST_VIEW_TYPE = 2
    }

    // ViewHolders
    class AuthorPostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: PostItem.AuthorPost) {
            itemView.findViewById<TextView>(R.id.authorName).text = item.authorName
            itemView.findViewById<TextView>(R.id.postText).text = item.text
        }
    }

    class ImagePostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: PostItem.ImagePost) {
            // Загрузка изображения с Glide/Picasso
            itemView.findViewById<TextView>(R.id.captionText).text = item.caption
        }
    }

    class TextButtonPostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: PostItem.TextWithButtonPost) {
            itemView.findViewById<TextView>(R.id.postText).text = item.text
            itemView.findViewById<Button>(R.id.actionButton).text = item.buttonText
        }
    }
}