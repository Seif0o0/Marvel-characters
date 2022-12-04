package totersapp.marvel.task.presentation.adapter

open class ListItemClickListener<T>(val clickListener: (data: T) -> Unit) {
    fun onItemClickListener(data: T) = clickListener(data)
}