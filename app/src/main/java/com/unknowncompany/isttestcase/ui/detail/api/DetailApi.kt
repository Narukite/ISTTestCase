package com.unknowncompany.isttestcase.ui.detail.api

import com.unknowncompany.isttestcase.BuildConfig
import com.unknowncompany.isttestcase.app.response.MovieDetailsResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface DetailApi {

    @GET("movie/{movie_id}")
    @Headers("Authorization: Bearer ${BuildConfig.MOVIE_BEARER_TOKEN}")
    fun getMovieDetails(
        @Path("movie_id") movieId: Int,
    ): Single<MovieDetailsResponse>

}