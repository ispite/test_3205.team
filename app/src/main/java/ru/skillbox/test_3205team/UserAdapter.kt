package ru.skillbox.test_3205team

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.skillbox.test_3205team.data.User
import ru.skillbox.test_3205team.databinding.ItemUserBinding
import ru.skillbox.test_3205team.utils.inflate

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private var userList: List<User> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(parent.inflate(ItemUserBinding::inflate))
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val currentUser = userList[position]
        holder.bind(currentUser)
    }

    override fun getItemCount(): Int = userList.size

    fun submitList(userList: List<User>) {
        this.userList = userList
        notifyDataSetChanged()
    }

    class UserViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: User) {
            with(binding) {
                usernameTextView.text = item.username

                Glide.with(itemView)
                    .load(item.avatar)
                    .placeholder(R.drawable.ic_image)
                    .into(avatarImageView)
            }
        }
    }
}