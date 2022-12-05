package totersapp.marvel.task.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import totersapp.marvel.task.databinding.ComicItemBinding
import totersapp.marvel.task.domain.model.Comic
import javax.inject.Inject

class ComicsAdapter @Inject constructor() :
    ListAdapter<Comic, ComicsAdapter.ViewHolder>(ComicsComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder private constructor(private val binding: ComicItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(comic: Comic) {
            binding.comic = comic

        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                return ViewHolder(
                    ComicItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }
    }

    object ComicsComparator : DiffUtil.ItemCallback<Comic>() {
        override fun areItemsTheSame(oldItem: Comic, newItem: Comic) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Comic, newItem: Comic) =
            oldItem == newItem
    }

}