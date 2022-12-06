package totersapp.marvel.task.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import totersapp.marvel.task.databinding.FragmentCharacterDetailsBinding
import totersapp.marvel.task.presentation.adapter.*
import totersapp.marvel.task.presentation.viewmodel.CharacterDetailsViewModel
import totersapp.marvel.task.presentation.viewmodel.CharactersViewModel
import javax.inject.Inject

@AndroidEntryPoint
class CharacterDetailsFragment : Fragment() {
    private val viewModel: CharacterDetailsViewModel by viewModels()
    private lateinit var binding: FragmentCharacterDetailsBinding

    @Inject
    lateinit var comicsAdapter: ComicsAdapter

    @Inject
    lateinit var eventsAdapter: EventsAdapter

    @Inject
    lateinit var seriesAdapter: SeriesAdapter

    @Inject
    lateinit var storiesAdapter: StoriesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterDetailsBinding.inflate(inflater, container, false)
        val character = CharacterDetailsFragmentArgs.fromBundle(requireArguments()).marvelCharacter
        binding.character = character
        binding.viewModel = viewModel
        binding.comicsRetryListener = RetryClickListener { viewModel.getComics() }
        binding.eventsRetryListener = RetryClickListener { viewModel.getEvents() }
        binding.seriesRetryListener = RetryClickListener { viewModel.getSeries() }
        binding.storiesRetryListener = RetryClickListener { viewModel.getStories() }
        binding.lifecycleOwner = requireActivity()

        if (savedInstanceState != null) {
            binding.scrollView.scrollY = savedInstanceState.getInt(SCROLL_POSITION_KEY)
        }
        binding.comicsList.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = comicsAdapter
        }
        lifecycleScope.launchWhenStarted {
            viewModel.comics.collectLatest {
                comicsAdapter.submitList(it)
            }
        }

        binding.eventsList.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = eventsAdapter
        }
        lifecycleScope.launchWhenStarted {
            viewModel.events.collectLatest {
                eventsAdapter.submitList(it)
            }
        }

        binding.seriesList.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = seriesAdapter
        }
        lifecycleScope.launchWhenStarted {
            viewModel.series.collectLatest {
                seriesAdapter.submitList(it)
            }
        }

        binding.storiesList.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = storiesAdapter
        }
        lifecycleScope.launchWhenStarted {
            viewModel.stories.collectLatest {
                storiesAdapter.submitList(it)
            }
        }


        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    findNavController().popBackStack()
                }
            })


        return binding.root
    }

    private val SCROLL_POSITION_KEY = "scrollPosition"
    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(SCROLL_POSITION_KEY, binding.scrollView.scrollY)
        super.onSaveInstanceState(outState)
    }
}