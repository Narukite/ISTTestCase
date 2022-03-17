package com.unknowncompany.isttestcase.ui.detail.data

import com.unknowncompany.isttestcase.app.model.DetailMovie
import com.unknowncompany.isttestcase.ui.detail.DetailContract
import com.unknowncompany.isttestcase.ui.detail.api.DetailApi
import com.unknowncompany.isttestcase.utils.DataMapper.toDetailMovie
import io.reactivex.Single

class DetailRepo(private val api: DetailApi) : DetailContract.Repo {
    override fun getMovieDetails(movieId: Int): Single<DetailMovie> {
        return api.getMovieDetails(movieId).map { it.toDetailMovie() }
    }
}