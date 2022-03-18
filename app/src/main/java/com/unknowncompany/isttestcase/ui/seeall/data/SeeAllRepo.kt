package com.unknowncompany.isttestcase.ui.seeall.data

import com.unknowncompany.isttestcase.app.model.Movie
import com.unknowncompany.isttestcase.ui.seeall.SeeAllContract
import com.unknowncompany.isttestcase.ui.seeall.api.SeeAllApi
import com.unknowncompany.isttestcase.utils.DataMapper.toMoviesNowPlaying
import com.unknowncompany.isttestcase.utils.DataMapper.toMoviesUpcoming
import io.reactivex.Single

class SeeAllRepo(private val api: SeeAllApi) : SeeAllContract.Repo {
    private var page = 0

    override fun getMoviesNowPlaying(): Single<List<Movie>> {
        return api.getMoviesNowPlaying(++page).map { it.toMoviesNowPlaying() }
    }

    override fun getMoviesUpcoming(): Single<List<Movie>> {
        return api.getMoviesUpcoming(++page).map { it.toMoviesUpcoming() }
    }
}