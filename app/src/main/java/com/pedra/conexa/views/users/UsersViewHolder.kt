package com.pedra.conexa.views.users

import android.view.View
import androidx.core.content.ContextCompat
import com.pedra.conexa.R
import com.pedra.conexa.databinding.ItemUsersBinding
import com.pedra.conexa.utils.GenericRecyclerViewHolder
import com.pedra.conexamodel.UserUI
import com.pedra.core.utils.UtilDate

class UsersViewHolder(
    private val binding: ItemUsersBinding
) : GenericRecyclerViewHolder(binding) {

    private val utilDate = UtilDate.getInstance()

    fun render(
        item: UserUI,
        onClick: ((UserUI) -> Unit)?
    ) {
        with(binding) {
            val name = "${item.firstName} ${item.lastName}"
            tvUserName.text = name
            tvEmail.text = item.email
            selectIconBackGround(item)
            usersItem.setOnClickListener {
                if (onClick != null) onClick(item)
            }
        }
    }

    private fun selectIconBackGround(item: UserUI) {
        with(binding) {

            val drawable = ContextCompat.getDrawable(
                ivUserBackground.context,
                R.drawable.bg_circle_user
            )
            drawable?.let { it.setTint(selectColor(item.id)) }
            ivUserBackground.setImageDrawable(drawable)
            tvUserName.visibility = View.VISIBLE
            val name = "${item.firstName} ${item.lastName}"
            tvUserNameInitials.text = getTwoFirstLetters(name)
        }

    }

    private fun getTwoFirstLetters(name: String): String {
        if (name.length >= 2) {
            return name.substring(0, 2).uppercase()
        }
        return name.uppercase()
    }

    private fun selectColor(id: Int): Int {
        val resourceId = binding.ivUserBackground.context.resources.getIdentifier(
            "nc_color${id % 10}",
            "color",
            binding.ivUserBackground.context.packageName
        )
        return ContextCompat.getColor(binding.ivUserBackground.context, resourceId)
    }
}
