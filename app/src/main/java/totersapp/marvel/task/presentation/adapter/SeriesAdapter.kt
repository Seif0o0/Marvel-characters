package totersapp.marvel.task.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import totersapp.marvel.task.databinding.SeriesItemBinding
import totersapp.marvel.task.domain.model.Series
import javax.inject.Inject

class SeriesAdapter @Inject constructor() :
    ListAdapter<Series, SeriesAdapter.ViewHolder>(SeriesComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder private constructor(private val binding: SeriesItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(series: Series) {
            binding.series = series

        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                return ViewHolder(
                    SeriesItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }
    }

    object SeriesComparator : DiffUtil.ItemCallback<Series>() {
        override fun areItemsTheSame(oldItem: Series, newItem: Series) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Series, newItem: Series) =
            oldItem == newItem
    }

}