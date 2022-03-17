package com.unknowncompany.isttestcase.ui.main.data

import com.unknowncompany.isttestcase.app.model.MainMovie
import com.unknowncompany.isttestcase.ui.main.MainContract
import com.unknowncompany.isttestcase.ui.main.api.MainApi
import com.unknowncompany.isttestcase.utils.DataMapper.toMoviesNowPlaying
import com.unknowncompany.isttestcase.utils.DataMapper.toMoviesUpcoming
import io.reactivex.Single

class MainRepo(private val api: MainApi) : MainContract.Repo {
    private val page = 1

    override fun getMoviesNowPlaying(): Single<List<MainMovie>> {
        return api.getMoviesNowPlaying(page).map { it.toMoviesNowPlaying() }
    }

    override fun getMoviesUpcoming(): Single<List<MainMovie>> {
        return api.getMoviesUpcoming(page).map { it.toMoviesUpcoming() }
    }
}