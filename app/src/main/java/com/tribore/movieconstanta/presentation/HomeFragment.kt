package com.tribore.movieconstanta.presentation

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.tribore.movieconstanta.MovieApp
import com.tribore.movieconstanta.databinding.FragmentHomeBinding
import com.tribore.movieconstanta.domain.models.Movie
import dagger.Lazy
import javax.inject.Inject


class HomeFragment : Fragment() {

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

        val appComponent = ((requireActivity() as MainActivity).application as MovieApp).appComponent
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
        viewModel.listMovies.observe(viewLifecycleOwner) {
            movieAdapter.submitList(it)
        }
    }

    private fun bindView() {
        binding.rcMovies.adapter = movieAdapter
    }

    private fun clickMovie(itemClick: Movie) {
        Toast.makeText(requireContext(), itemClick.title, Toast.LENGTH_LONG).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}