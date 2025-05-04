package com.example.myapp.model


sealed class PostItem {
    data class AuthorPost(val authorName: String, val text: String) : PostItem()
    data class ImagePost(val imageUrl: String, val caption: String) : PostItem()
    data class TextWithButtonPost(val text: String, val buttonText: String) : PostItem()
}
