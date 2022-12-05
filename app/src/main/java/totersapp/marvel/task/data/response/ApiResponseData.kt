package totersapp.marvel.task.data.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class ApiResponseData<T>(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<T>
)