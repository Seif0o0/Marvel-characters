package totersapp.marvel.task.data.services

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap
import totersapp.marvel.task.data.response.CharactersResponse
import totersapp.marvel.task.utils.Constants

interface CharactersService {
    @GET(Constants.CHARACTERS)
    suspend fun fetchCharacters(
        @QueryMap map: Map<String, String>
    ): Response<CharactersResponse>
}