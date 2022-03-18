package com.unknowncompany.isttestcase.ui.main

import com.unknowncompany.isttestcase.ui.detail.DetailActivity
import com.unknowncompany.isttestcase.ui.seeall.SeeAllActivity

class MainRouter(private val activity: MainActivity) : MainContract.Router {
    override fun openMovieDetails(movieId: Int) {
        DetailActivity.launch(activity, movieId)
    }

    override fun openMoviesSeeAllOfNowPlaying() {
        SeeAllActivity.launch(activity, SeeAllActivity.NOW_PLAYING)
    }

    override fun openMoviesSeeAllOfUpcoming() {
        SeeAllActivity.launch(activity, SeeAllActivity.UPCOMING)
    }
}