package com.rizahanmiy.newsapp.presentation.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rizahanmiy.newsapp.R
import kotlinx.android.synthetic.main.list_item_news_category.view.*

class CategoryListAdapter(
    val context: Context,
    val data:List<String?>,
    val onCategoryClicked: ((category: String?) -> Unit)? = null
):RecyclerView.Adapter<CategoryListAdapter.CategoryViewHolder>() {

    companion object {
        var last_position = 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            LayoutInflater.from(context).inflate(R.layout.list_item_news_category, parent, false)
        )
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(data[position])
        holder.itemView.btCategory.setTextColor(context.getColor(R.color.white_200))

        holder.itemView.setOnClickListener {
            last_position = position
            onCategoryClicked?.invoke(data[position])
            notifyDataSetChanged()
        }

        if (last_position == position) {
            holder.itemView.btCategory.setTextColor(context.getColor(R.color.blue_100))
        } else {
            holder.itemView.btCategory.setTextColor(context.getColor(R.color.white_200))
        }

    }

    //ViewHolder
    inner class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(data: String?) {
            with(itemView) {
                btCategory.text = "#$data"
            }
        }
    }
}