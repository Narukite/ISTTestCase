package com.unknowncompany.isttestcase.app.model

data class DetailMovie(
    val id: Int,
    val posterPath: String? = null,
    val originalTitle: String,
    val releaseDate: String,
    val popularity: String,
    val genre: String,
    val voteAverage: String,
    val overview: String,
)