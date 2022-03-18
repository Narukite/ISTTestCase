package com.unknowncompany.isttestcase.utils

import com.unknowncompany.isttestcase.app.model.DetailMovie
import com.unknowncompany.isttestcase.app.model.Movie
import com.unknowncompany.isttestcase.app.response.MovieDetailsResponse
import com.unknowncompany.isttestcase.app.response.MoviesNowPlayingResponse

object DataMapper {

    private const val IMAGE_BASE_URL_AND_SIZE = "https://image.tmdb.org/t/p/original/"
    private const val NO_OVERVIEW = "No overview."

    fun MoviesNowPlayingResponse.toMoviesNowPlaying(): List<Movie> {
        val arrayList = arrayListOf<Movie>()
        for (item in this.results)
            arrayList.add(
                Movie(item.id,
                    "$IMAGE_BASE_URL_AND_SIZE${item.posterPath}",
                    item.originalTitle))
        return arrayList
    }

    fun MoviesNowPlayingResponse.toMoviesUpcoming(): List<Movie> {
        val arrayList = arrayListOf<Movie>()
        for (item in this.results)
            arrayList.add(
                Movie(item.id,
                    "$IMAGE_BASE_URL_AND_SIZE${item.posterPath}",
                    item.originalTitle))
        return arrayList
    }

    fun MovieDetailsResponse.toDetailMovie(): DetailMovie {
        return DetailMovie(id,
            "$IMAGE_BASE_URL_AND_SIZE$posterPath",
            originalTitle,
            releaseDate,
            popularity.toString(),
            genres[0].name,
            voteAverage.toString(),
            overview ?: NO_OVERVIEW)
    }

}