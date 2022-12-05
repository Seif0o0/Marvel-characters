package totersapp.marvel.task.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Thumbnail(
    val url: String,
    val extension: String
) : Parcelable