package ru.skillbox.test_3205team.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import ru.skillbox.test_3205team.R
import ru.skillbox.test_3205team.databinding.FragmentMainBinding
import ru.skillbox.test_3205team.utils.autoCleared
import ru.skillbox.test_3205team.utils.textChangedFlow
import timber.log.Timber

class MainFragment : Fragment(R.layout.fragment_main) {

    private val binding: FragmentMainBinding by viewBinding(FragmentMainBinding::bind)
    private val viewModel: MainViewModel by viewModels()
    private var userAdapter: UserAdapter by autoCleared()

    private lateinit var textChangedFlow: Flow<String>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
        flowTextChanged()
        viewModel.search(textChangedFlow)
        bindViewModel()
    }

    private fun flowTextChanged() {
        viewLifecycleOwner.lifecycleScope.launch {
            textChangedFlow = binding.searchEditText.textChangedFlow()
        }
    }

    private fun initList() {
        userAdapter = UserAdapter {
            Timber.d("name= $it")
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToReposFragment(it))
        }
        with(binding.usersRecyclerView) {
            adapter = userAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    private fun bindViewModel() {
        viewModel.userList.observe(viewLifecycleOwner) {
            userAdapter.submitList(it)
        }
    }
}
