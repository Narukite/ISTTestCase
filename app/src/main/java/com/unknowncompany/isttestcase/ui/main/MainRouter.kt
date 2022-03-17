package com.unknowncompany.isttestcase.ui.main

import com.unknowncompany.isttestcase.ui.detail.DetailActivity

class MainRouter(private val activity: MainActivity) : MainContract.Router {
    override fun openMovieDetails(movieId: Int) {
        DetailActivity.launch(activity, movieId)
    }
}