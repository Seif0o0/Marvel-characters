package totersapp.marvel.task.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import totersapp.marvel.task.databinding.EventItemBinding
import totersapp.marvel.task.domain.model.Event
import javax.inject.Inject

class EventsAdapter @Inject constructor() :
    ListAdapter<Event, EventsAdapter.ViewHolder>(EventsComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder private constructor(private val binding: EventItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(event: Event) {
            binding.event = event

        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                return ViewHolder(
                    EventItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }
    }

    object EventsComparator : DiffUtil.ItemCallback<Event>() {
        override fun areItemsTheSame(oldItem: Event, newItem: Event) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Event, newItem: Event) =
            oldItem == newItem
    }

}