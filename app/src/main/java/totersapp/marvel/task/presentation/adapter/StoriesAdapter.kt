package totersapp.marvel.task.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import totersapp.marvel.task.databinding.StoryItemBinding
import totersapp.marvel.task.domain.model.Story
import javax.inject.Inject

class StoriesAdapter @Inject constructor() :
    ListAdapter<Story, StoriesAdapter.ViewHolder>(StoriesComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder private constructor(private val binding: StoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(story: Story) {
            binding.story = story

        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                return ViewHolder(
                    StoryItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }
    }

    object StoriesComparator : DiffUtil.ItemCallback<Story>() {
        override fun areItemsTheSame(oldItem: Story, newItem: Story) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Story, newItem: Story) =
            oldItem == newItem
    }

}