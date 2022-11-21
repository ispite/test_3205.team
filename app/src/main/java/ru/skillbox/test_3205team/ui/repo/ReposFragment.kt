package ru.skillbox.test_3205team.ui.repo

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.skillbox.test_3205team.R
import ru.skillbox.test_3205team.data.Repository
import ru.skillbox.test_3205team.databinding.FragmentReposBinding
import ru.skillbox.test_3205team.utils.autoCleared
import ru.skillbox.test_3205team.utils.toast
import timber.log.Timber

class ReposFragment : Fragment(R.layout.fragment_repos) {

    private val binding: FragmentReposBinding by viewBinding(FragmentReposBinding::bind)
    private val viewModel: ReposViewModel by viewModels()
    private val args: ReposFragmentArgs by navArgs()
    private var repoAdapter: RepoAdapter by autoCleared()

    private lateinit var createZipLauncher: ActivityResultLauncher<String>
    private lateinit var repository: Repository

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initCreateZipLauncher()
        initList()
        bindViewModel()
        viewModel.userRepos(args.owner)
    }

    private fun initList() {
        repoAdapter = RepoAdapter {
            Timber.d("repo ID= $it")
            repository = it
            createZip()
        }
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
        viewModel.loading.observe(viewLifecycleOwner, ::setLoading)
    }

    private fun initCreateZipLauncher() {
        createZipLauncher = registerForActivityResult(
            ActivityResultContracts.CreateDocument("application/zip")
        ) { uri ->
            handleCreateZip(uri)
        }
    }

    private fun handleCreateZip(uri: Uri?) {
        if (uri == null) {
            toast(R.string.create_zip_error_toast_message)
            return
        }
        //TODO сделать сохранение файла
        viewModel.downloadRepoZip(uri, repository.owner.username, repository.name)
    }

    private fun createZip() {
        createZipLauncher.launch("new repository.zip")
    }

    private fun setLoading(isLoading: Boolean) {
        binding.repoProgressBar.isVisible = isLoading
        binding.reposRecyclerView.isEnabled = isLoading.not()
    }
}
