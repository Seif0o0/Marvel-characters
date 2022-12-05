package totersapp.marvel.task.data.services

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap
import totersapp.marvel.task.data.model.*
import totersapp.marvel.task.data.response.ApiResponse
import totersapp.marvel.task.data.response.ApiResponseData
import totersapp.marvel.task.utils.Constants

interface CharactersService {

    @GET(Constants.CHARACTERS)
    suspend fun fetchCharacters(
        @QueryMap map: Map<String, String>
    ): Response<ApiResponse<ApiResponseData<MarvelCharacterDto>>>

    @GET("${Constants.CHARACTERS}/{${Constants.PATH_CHARACTER_ID}}/${Constants.COMICS}")
    suspend fun fetchCharacterComics(
        @Path(Constants.PATH_CHARACTER_ID) characterId: String,
        @QueryMap map: Map<String, String>
    ): Response<ApiResponse<ApiResponseData<ComicDto>>>

    @GET("${Constants.CHARACTERS}/{${Constants.PATH_CHARACTER_ID}}/${Constants.EVENTS}")
    suspend fun fetchCharacterEvents(
        @Path(Constants.PATH_CHARACTER_ID) characterId: String,
        @QueryMap map: Map<String, String>
    ): Response<ApiResponse<ApiResponseData<EventDto>>>

    @GET("${Constants.CHARACTERS}/{${Constants.PATH_CHARACTER_ID}}/${Constants.SERIES}")
    suspend fun fetchCharacterSeries(
        @Path(Constants.PATH_CHARACTER_ID) characterId: String,
        @QueryMap map: Map<String, String>
    ): Response<ApiResponse<ApiResponseData<SeriesDto>>>

    @GET("${Constants.CHARACTERS}/{${Constants.PATH_CHARACTER_ID}}/${Constants.STORIES}")
    suspend fun fetchCharacterStories(
        @Path(Constants.PATH_CHARACTER_ID) characterId: String,
        @QueryMap map: Map<String, String>
    ): Response<ApiResponse<ApiResponseData<StoryDto>>>
}