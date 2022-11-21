package ru.skillbox.test_3205team.ui.repo

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import ru.skillbox.test_3205team.data.Repository

class RepoAdapter(onDownloadRepo: (repository: Repository) -> Unit) :
    AsyncListDifferDelegationAdapter<Repository>(RepoDiffUtilCallback()) {

    init {
        delegatesManager.addDelegate(RepoDelegate(onDownloadRepo))
    }

    class RepoDiffUtilCallback : DiffUtil.ItemCallback<Repository>() {
        override fun areItemsTheSame(oldItem: Repository, newItem: Repository): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Repository, newItem: Repository): Boolean {
            return oldItem == newItem
        }
    }
}
