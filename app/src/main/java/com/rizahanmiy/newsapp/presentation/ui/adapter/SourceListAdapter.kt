package com.rizahanmiy.newsapp.presentation.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rizahanmiy.newsapp.R
import com.rizahanmiy.newsapp.data.entities.NewsSourceApi
import kotlinx.android.synthetic.main.list_item_source.view.*

class SourceListAdapter(
    val context: Context,
    val data:MutableList<NewsSourceApi>,
    val onSourceClicked: ((category: String?) -> Unit)? = null,
):RecyclerView.Adapter<SourceListAdapter.NewsSourceViewHolder>() {

    companion object {
        var last_position = 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsSourceViewHolder {
        return NewsSourceViewHolder(
            LayoutInflater.from(context).inflate(R.layout.list_item_source, parent, false)
        )
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: NewsSourceViewHolder, position: Int) {
        holder.bind(data[position])
        holder.itemView.tvSource.setTextColor(context.getColor(R.color.white_200))

        holder.itemView.setOnClickListener {
            last_position = position
            onSourceClicked?.invoke(data[position].id)
            notifyDataSetChanged()
        }

        if (last_position == position) {
            holder.itemView.tvSource.setTextColor(context.getColor(R.color.blue_100))
        } else {
            holder.itemView.tvSource.setTextColor(context.getColor(R.color.white_200))
        }
    }

    //ViewHolder
    inner class NewsSourceViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(data: NewsSourceApi?) {
            with(itemView) {
                tvSource.text = data?.name
            }
        }
    }
}