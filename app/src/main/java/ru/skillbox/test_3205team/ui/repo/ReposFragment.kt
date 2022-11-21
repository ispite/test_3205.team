package ru.skillbox.test_3205team.ui.repo

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.skillbox.test_3205team.R
import ru.skillbox.test_3205team.databinding.FragmentReposBinding
import ru.skillbox.test_3205team.utils.autoCleared
import timber.log.Timber

class ReposFragment : Fragment(R.layout.fragment_repos) {

    private val binding: FragmentReposBinding by viewBinding(FragmentReposBinding::bind)
    private val viewModel: ReposViewModel by viewModels()
    private val args: ReposFragmentArgs by navArgs()
    private var repoAdapter: RepoAdapter by autoCleared()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
        bindViewModel()
        viewModel.userRepos(args.owner)
    }

    private fun initList() {
        repoAdapter = RepoAdapter { Timber.d("repo ID= $it") }
        with(binding.reposRecyclerView) {
            adapter = repoAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    private fun bindViewModel() {
        viewModel.repoList.observe(viewLifecycleOwner) {
            repoAdapter.items = it
        }
    }
}