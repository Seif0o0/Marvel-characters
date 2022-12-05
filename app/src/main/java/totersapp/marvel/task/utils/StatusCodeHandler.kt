package totersapp.marvel.task.utils

import android.app.Application
import com.squareup.moshi.Moshi
import retrofit2.Response
import totersapp.marvel.task.R
import totersapp.marvel.task.data.response.ErrorResponse


fun <T> handleStatusCode(application: Application, response: Response<T>): String {
    return when (response.code()) {
        409 -> {
            val body = response.errorBody()
            val adapter = Moshi.Builder().build().adapter(ErrorResponse::class.java)
            val errorParser = adapter.fromJson(body?.string() ?: "")

            // if message == null .. check if status == null or not ...
            errorParser?.message
                ?: errorParser?.status
                ?: application.getString(R.string.something_went_wrong_try_again_later)
        }
        401 ->
            application.getString(R.string.error_401_message)
        403 ->
            application.getString(R.string.error_403_message)
        500 ->
            application.getString(R.string.server_error_message)
        else ->
            application.getString(R.string.something_went_wrong_try_again_later)
    }
}