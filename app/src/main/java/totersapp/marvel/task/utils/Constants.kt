package totersapp.marvel.task.utils

import java.math.BigInteger
import java.security.MessageDigest
import java.sql.Timestamp

object Constants {
    const val BASE_URL = "https://gateway.marvel.com/v1/public/"
    const val PAGE_SIZE = 10
    const val SMALL_PAGE_SIZE = 3

    val timeStamp = Timestamp(System.currentTimeMillis()).time
    const val PUBLIC_API_KEY = "1e51f255f25f00c22947b566c84ec275"
    private const val PRIVATE_API_KEY = "9b9b941280fc53fcdcb69a8f6d4c2c52b52c8b80"
    fun hash(): String {
        val input = "$timeStamp$PRIVATE_API_KEY$PUBLIC_API_KEY"
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
    }

    const val CHARACTERS = "characters"
    const val PATH_CHARACTER_ID = "characterId"
    const val COMICS = "comics"
    const val EVENTS = "events"
    const val SERIES = "series"
    const val STORIES = "stories"

}