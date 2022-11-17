package ru.skillbox.test_3205team

import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.skillbox.test_3205team.databinding.FragmentMainBinding

class MainFragment : Fragment(R.layout.fragment_main) {

    private val binding: FragmentMainBinding by viewBinding(FragmentMainBinding::bind)

    // TODO почитать gitHub API нужна ли авторизация для доступа к репозиториям и имплементировать вот это всё
}
