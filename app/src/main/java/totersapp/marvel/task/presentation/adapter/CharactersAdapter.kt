package totersapp.marvel.task.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import totersapp.marvel.task.databinding.CharacterItemBinding
import totersapp.marvel.task.domain.model.MarvelCharacter
import javax.inject.Inject

class CharactersAdapter @Inject constructor() :
    PagingDataAdapter<MarvelCharacter, CharactersAdapter.ViewHolder>(CharactersComparator) {

    private var clickListener: ListItemClickListener<MarvelCharacter>? = null

    fun setOnClickListener(clickListener: ListItemClickListener<MarvelCharacter>) {
        this.clickListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!, clickListener!!)
    }

    class ViewHolder private constructor(private val binding: CharacterItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            character: MarvelCharacter,
            clickListener: ListItemClickListener<MarvelCharacter>
        ) {
            binding.character = character
            binding.clickListener = clickListener
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                return ViewHolder(
                    CharacterItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }
    }

    object CharactersComparator : DiffUtil.ItemCallback<MarvelCharacter>() {
        override fun areContentsTheSame(oldItem: MarvelCharacter, newItem: MarvelCharacter) =
            oldItem == newItem

        override fun areItemsTheSame(oldItem: MarvelCharacter, newItem: MarvelCharacter) =
            oldItem.id == newItem.id
    }
}