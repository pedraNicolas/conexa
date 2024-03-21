package com.pedra.conexa.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.pedra.conexa.databinding.ItemNewBinding
import com.pedra.conexa.databinding.ItemUsersBinding
import com.pedra.conexa.views.news.NewsViewHolder
import com.pedra.conexa.views.users.UsersViewHolder
import com.pedra.conexamodel.NewsUI
import com.pedra.conexamodel.UserUI

enum class ViewHolderType {
    NewsViewHolder,
    UsersViewHolder
}

class GenericRecyclerAdapter<T, S>(
    private var list: List<T>,
    private val onDelete: ((S) -> Unit)? = null,
    private val onPrimaryClick: ((S) -> Unit)? = null,
    private val viewHolderType: ViewHolderType,
) : RecyclerView.Adapter<GenericRecyclerViewHolder>() {

    /**
     * This method manage the correct calls when the list is modified.
     * Use it instead of notifyDataSetChanged()
     * */
    fun updateList(newList: List<T>) {
        val indicatorUIDiff = GenericDiffUtil(list, newList)
        val result = DiffUtil.calculateDiff(indicatorUIDiff)
        list = newList
        result.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GenericRecyclerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        return when (viewHolderType) {
            ViewHolderType.NewsViewHolder -> {
                val binding = ItemNewBinding.inflate(layoutInflater, parent, false)
                NewsViewHolder(binding)
            }

            ViewHolderType.UsersViewHolder -> {
                val binding = ItemUsersBinding.inflate(layoutInflater, parent, false)
                UsersViewHolder(binding)
            }
        }
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: GenericRecyclerViewHolder, position: Int) {
        val item = list[position]
        when (viewHolderType) {
            ViewHolderType.NewsViewHolder -> {
                (holder as NewsViewHolder).render(
                    item as NewsUI,
                    onPrimaryClick as (NewsUI) -> Unit
                )
            }

            ViewHolderType.UsersViewHolder ->{
                (holder as UsersViewHolder).render(
                    item as UserUI,
                    onPrimaryClick as (UserUI) -> Unit
                )
            }
        }
    }
}

