package ru.skillbox.test_3205team.ui.repo

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import ru.skillbox.test_3205team.data.Repository
import ru.skillbox.test_3205team.databinding.ItemRepositoryBinding
import ru.skillbox.test_3205team.utils.inflate

class RepoDelegate(
    private val onDownloadRepo: (repository: Repository) -> Unit
) :
    AbsListItemAdapterDelegate<Repository, Repository, RepoDelegate.RepoViewHolder>() {

    override fun isForViewType(
        item: Repository,
        items: MutableList<Repository>,
        position: Int
    ): Boolean {
        return true
    }

    override fun onCreateViewHolder(parent: ViewGroup): RepoViewHolder {
        return RepoViewHolder(parent.inflate(ItemRepositoryBinding::inflate), onDownloadRepo)
    }

    override fun onBindViewHolder(
        item: Repository,
        holder: RepoViewHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }

    class RepoViewHolder(
        private val binding: ItemRepositoryBinding,
        onDownloadRepo: (repository: Repository) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {

        private var currentRepoId: Repository? = null

        init {
            binding.repoDownloadButton.setOnClickListener { currentRepoId?.let(onDownloadRepo) }
        }

        fun bind(item: Repository) {
            currentRepoId = item
            with(binding) {
                repoNameTextView.text = item.name
                repoDescriptionTextView.text = item.description
            }
        }
    }
}
