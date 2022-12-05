package totersapp.marvel.task.presentation.fragment

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import totersapp.marvel.task.R
import totersapp.marvel.task.databinding.FragmentCharactersBinding
import totersapp.marvel.task.presentation.adapter.CharactersAdapter
import totersapp.marvel.task.presentation.adapter.ListItemClickListener
import totersapp.marvel.task.presentation.adapter.ListLoadStateAdapter
import totersapp.marvel.task.presentation.adapter.RetryClickListener
import totersapp.marvel.task.presentation.viewmodel.CharactersViewModel
import totersapp.marvel.task.utils.getScreenSize
import javax.inject.Inject

@AndroidEntryPoint
class CharactersFragment : Fragment() {
    private val viewModel: CharactersViewModel by viewModels()
    private lateinit var binding: FragmentCharactersBinding

    @Inject
    lateinit var charactersAdapter: CharactersAdapter

    @Inject
    lateinit var loadStateAdapter: ListLoadStateAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharactersBinding.inflate(layoutInflater, container, false)
        var screenSize = if (Build.VERSION.SDK_INT < 30) getScreenSize(resources = resources)
        else getScreenSize(
            resources = resources,
            windowMetrics = requireContext().applicationContext.getSystemService(
                WindowManager::class.java
            ).maximumWindowMetrics
        )
        viewModel.screenSizeState(screenSize)
        binding.viewModel = viewModel
        binding.retryListener = RetryClickListener {
            charactersAdapter.retry()
        }
        binding.lifecycleOwner = requireActivity()

        charactersAdapter.setOnClickListener(ListItemClickListener {
            if (findNavController().currentDestination?.id == R.id.charactersFragment) findNavController().navigate(
                CharactersFragmentDirections.actionCharactersFragmentToCharacterDetailsFragment(
                    marvelCharacter = it
                )
            )
        })

        charactersAdapter.addLoadStateListener { combinedLoadStates ->
            viewModel.handleLoadStateListener(combinedLoadStates, charactersAdapter.itemCount)
        }

        loadStateAdapter.setRetryListener(RetryClickListener {
            charactersAdapter.retry()
        })

        binding.list.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = charactersAdapter.withLoadStateFooter(
                footer = loadStateAdapter
            )
        }

        lifecycleScope.launchWhenStarted {
            viewModel.characters.collectLatest {
                charactersAdapter.submitData(it)
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.screenSizeState.collectLatest {
                if (it.first < 600) binding.list.layoutManager =
                    LinearLayoutManager(requireContext())
                else if (it.first < 840) binding.list.layoutManager =
                    GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
                else binding.list.layoutManager =
                    GridLayoutManager(requireContext(), 3, GridLayoutManager.VERTICAL, false)
            }
        }

        return binding.root
    }
}