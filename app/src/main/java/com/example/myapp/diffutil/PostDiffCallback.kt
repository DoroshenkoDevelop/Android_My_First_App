package com.example.myapp.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.example.myapp.model.PostItem

class PostDiffCallback(
    private val oldList: List<PostItem>,
    private val newList: List<PostItem>
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size
    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldPos: Int, newPos: Int): Boolean {
        val oldItem = oldList[oldPos]
        val newItem = newList[newPos]
        return when {
            oldItem is PostItem.AuthorPost && newItem is PostItem.AuthorPost ->
                oldItem.authorName == newItem.authorName
            oldItem is PostItem.ImagePost && newItem is PostItem.ImagePost ->
                oldItem.imageUrl == newItem.imageUrl
            oldItem is PostItem.TextWithButtonPost && newItem is PostItem.TextWithButtonPost ->
                oldItem.text == newItem.text
            else -> false
        }
    }

    override fun areContentsTheSame(oldPos: Int, newPos: Int): Boolean {
        return oldList[oldPos] == newList[newPos]
    }
}