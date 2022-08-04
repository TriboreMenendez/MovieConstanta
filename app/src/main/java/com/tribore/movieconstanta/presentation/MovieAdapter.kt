package com.tribore.movieconstanta.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tribore.movieconstanta.R
import com.tribore.movieconstanta.databinding.ItemRvMovieBinding
import com.tribore.movieconstanta.domain.models.Actor
import com.tribore.movieconstanta.domain.models.Movie

class MovieAdapter(private val onClick: (item: Movie) -> Unit) :
    ListAdapter<Movie, MovieViewHolder>(DiffCallback) {

    object DiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            ItemRvMovieBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ), onClick = onClick
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

}

class MovieViewHolder(
    private val binding: ItemRvMovieBinding,
    private val onClick: (item: Movie) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    private val context = binding.root.context

    fun bind(item: Movie) {
        binding.apply {
            tvTitle.text = context.getString(R.string.txt_title, item.title)
            tvReleaseYear.text = context.getString(R.string.txt_release_year, item.releaseYear.toString())
            tvDirectorsName.text = context.getString(R.string.txt_director, item.directorName)
            tvActors.text = context.getString(R.string.txt_actors, item.actors.toListActor())
            root.setOnClickListener { onClick(item) }
        }
    }

}

fun List<Actor>.toListActor(): String {
    val resultString = this.map {
        it.actorName
    }
    return resultString.joinToString()
}