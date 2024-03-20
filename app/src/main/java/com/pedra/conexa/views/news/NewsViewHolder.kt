package com.pedra.conexa.views.news

import com.bumptech.glide.Glide
import com.pedra.conexa.R
import com.pedra.conexa.databinding.ItemNewBinding
import com.pedra.conexa.utils.GenericRecyclerViewHolder
import com.pedra.conexamodel.NewsUI
import com.pedra.core.utils.UtilDate

class NewsViewHolder(
    private val binding: ItemNewBinding
) : GenericRecyclerViewHolder(binding) {


    fun render(
        item: NewsUI,
        onClick: ((NewsUI) -> Unit)?
    ) {
        with(binding) {
            Glide.with(ivImageNew.context).load(item.thumbnail)
                .placeholder(R.drawable.progress_animation).into(ivImageNew)
            tvNewsCategory.text = item.category
            tvNewsTitle.text = item.title
            tvNewsTime.text = item.updatedAt
            newItem.setOnClickListener {
                if (onClick != null) onClick(item)
            }
        }
    }

}