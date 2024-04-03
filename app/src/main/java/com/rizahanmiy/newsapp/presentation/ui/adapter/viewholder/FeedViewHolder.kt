package com.rizahanmiy.newsapp.presentation.ui.adapter.viewholder


import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.rizahanmiy.newsapp.data.entities.NewsArticlesApi
import com.rizahanmiy.newsapp.utils.common.loadImage
import kotlinx.android.synthetic.main.layout_news_author.view.*
import kotlinx.android.synthetic.main.list_item_article.view.*
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*


class FeedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    @RequiresApi(Build.VERSION_CODES.O)
    fun bindTo(newsArticleEntity: NewsArticlesApi) {
        with(itemView) {

            newsArticleEntity.urlToImage?.let { ivArticle.loadImage(it) }
            tvTitle.text = newsArticleEntity.title ?: "John Doe"
            tvAuthor.text = newsArticleEntity.author ?: "John Doe"
            tvDesc.text = newsArticleEntity.description
            tvContent.text = newsArticleEntity.content
            tvTime.text = newsArticleEntity.publishedAt?.let { convertTimeFormat(it) }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun convertTimeFormat(inputTime: String?): String? {
        try {
            val utcInstant = Instant.parse(inputTime)
            val utcDateTime = ZonedDateTime.ofInstant(utcInstant, ZoneOffset.UTC)

            val indonesianLocale = Locale("id", "ID")
            val formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm", indonesianLocale)

            return utcDateTime.format(formatter)
        } catch (e: Exception) {
            return try {
                val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX", Locale.US)
                val outputFormat = SimpleDateFormat("dd MMMM yyyy HH:mm:ss", Locale("id", "ID"))

                val date = inputTime?.let { inputFormat.parse(it) }
                date?.let { outputFormat.format(it) }
            } catch (e: Exception) {
                "invalid date format"
            }
        }
    }
}