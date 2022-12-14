package totersapp.marvel.task.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MarvelCharacter(
    val id: String,
    val name: String,
    val description: String,
    val thumbnail: Thumbnail
):Parcelable
