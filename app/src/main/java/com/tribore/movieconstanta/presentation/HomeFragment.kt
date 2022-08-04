package com.tribore.movieconstanta.presentation

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.tribore.movieconstanta.MovieApp
import com.tribore.movieconstanta.databinding.FragmentHomeBinding
import com.tribore.movieconstanta.di.ViewModelsFactory
import com.tribore.movieconstanta.domain.models.Movie
import dagger.Lazy
import javax.inject.Inject


class HomeFragment : Fragment() {

    companion object {
        private const val DIALOG_TAG = "MovieDialogFragment"
        private const val TITLE_KEY = "KeyTitleMovieDialogFragment"
        private const val ERROR_NETWORK = "Ошибка интернет соединения"
    }

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val movieAdapter = MovieAdapter { movie ->
        clickMovie(movie)
    }

    @Inject
    lateinit var viewModelFactory: Lazy<ViewModelsFactory>
    private val viewModel: MovieViewModel by viewModels { viewModelFactory.get() }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        val appComponent =
            ((requireActivity() as MainActivity).application as MovieApp).appComponent
        appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        bindView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.responseStatus.observe(viewLifecycleOwner) { result ->
            when (result) {
                is ResultState.Load -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is ResultState.Error -> {
                    binding.progressBar.visibility = View.GONE
                    binding.btnRefreshNetwork.visibility = View.VISIBLE
                    binding.btnRefreshNetwork.setOnClickListener {
                        viewModel.loadData()
                    }
                    Snackbar.make(view, ERROR_NETWORK, Snackbar.LENGTH_LONG).show()
                }
                is ResultState.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.btnRefreshNetwork.visibility = View.GONE
                    binding.rcMovies.visibility = View.VISIBLE
                    movieAdapter.submitList(result.data)
                }
            }
        }
    }

    private fun bindView() {
        binding.rcMovies.adapter = movieAdapter
    }

    private fun clickMovie(itemClick: Movie) {
        val dialog = SelectedMovieDialogFragment()
        val args = Bundle().apply {
            putString(TITLE_KEY, itemClick.title)
        }

        dialog.arguments = args
        dialog.show(parentFragmentManager, DIALOG_TAG)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}