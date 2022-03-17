package com.unknowncompany.isttestcase.app.response

import com.google.gson.annotations.SerializedName

data class MoviesNowPlayingResponse(
    @field:SerializedName("page")
    val page: Int,

    @field:SerializedName("results")
    val results: List<ResultsItem>,

    @field:SerializedName("dates")
    val dates: Dates,

    @field:SerializedName("total_pages")
    val totalPages: Int,

    @field:SerializedName("total_results")
    val totalResults: Int,
)

data class ResultsItem(
    @field:SerializedName("poster_path")
    val posterPath: String? = null,

    @field:SerializedName("adult")
    val adult: Boolean,

    @field:SerializedName("overview")
    val overview: String,

    @field:SerializedName("release_date")
    val releaseDate: String,

    @field:SerializedName("genre_ids")
    val genreIds: List<Int>,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("original_title")
    val originalTitle: String,

    @field:SerializedName("original_language")
    val originalLanguage: String,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("backdrop_path")
    val backdropPath: String? = null,

    @field:SerializedName("popularity")
    val popularity: Double,

    @field:SerializedName("vote_count")
    val voteCount: Int,

    @field:SerializedName("video")
    val video: Boolean,

    @field:SerializedName("vote_average")
    val voteAverage: Double,
)

data class Dates(
    @field:SerializedName("maximum")
    val maximum: String,

    @field:SerializedName("minimum")
    val minimum: String,
)
