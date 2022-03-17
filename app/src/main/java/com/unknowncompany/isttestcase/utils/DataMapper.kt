package com.unknowncompany.isttestcase.utils

import com.unknowncompany.isttestcase.app.model.MainMovie
import com.unknowncompany.isttestcase.app.response.MoviesNowPlayingResponse

object DataMapper {

    private const val IMAGE_BASE_URL_AND_SIZE = "https://image.tmdb.org/t/p/original/"

    fun MoviesNowPlayingResponse.toMoviesNowPlaying(): List<MainMovie> {
        val arrayList = arrayListOf<MainMovie>()
        for (item in this.results)
            arrayList.add(
                MainMovie("$IMAGE_BASE_URL_AND_SIZE${item.posterPath}", item.originalTitle))
        return arrayList
    }

    fun MoviesNowPlayingResponse.toMoviesUpcoming(): List<MainMovie> {
        val arrayList = arrayListOf<MainMovie>()
        for (item in this.results)
            arrayList.add(
                MainMovie("$IMAGE_BASE_URL_AND_SIZE${item.posterPath}", item.originalTitle))
        return arrayList
    }

}