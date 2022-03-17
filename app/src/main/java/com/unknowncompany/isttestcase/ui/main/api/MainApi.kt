package com.unknowncompany.isttestcase.ui.main.api

import com.unknowncompany.isttestcase.BuildConfig
import com.unknowncompany.isttestcase.app.response.MoviesNowPlayingResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface MainApi {

    @GET("movie/now_playing")
    @Headers("Authorization: Bearer ${BuildConfig.MOVIE_BEARER_TOKEN}")
    fun getMoviesNowPlaying(
        @Query("page") page: Int,
    ): Single<MoviesNowPlayingResponse>

    @GET("movie/upcoming")
    @Headers("Authorization: Bearer ${BuildConfig.MOVIE_BEARER_TOKEN}")
    fun getMoviesUpcoming(
        @Query("page") page: Int,
    ): Single<MoviesNowPlayingResponse>

}