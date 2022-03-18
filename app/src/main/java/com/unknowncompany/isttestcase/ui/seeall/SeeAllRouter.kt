package com.unknowncompany.isttestcase.ui.seeall

import com.unknowncompany.isttestcase.ui.detail.DetailActivity

class SeeAllRouter(private val activity: SeeAllActivity) : SeeAllContract.Router {
    override fun finish() {
        activity.finish()
    }

    override fun openMovieDetails(movieId: Int) {
        DetailActivity.launch(activity, movieId)
    }
}