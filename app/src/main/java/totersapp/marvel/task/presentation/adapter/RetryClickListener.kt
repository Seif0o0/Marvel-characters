package totersapp.marvel.task.presentation.adapter

open class RetryClickListener(val clickListener: () -> Unit) {
    fun onRetry() = clickListener()
}