package com.unknowncompany.isttestcase.ui.detail

import com.unknowncompany.isttestcase.app.model.DetailMovie
import io.reactivex.Single

interface DetailContract {
    interface View {
        fun showLoading()
        fun hideLoading()
        fun populateView(data: DetailMovie)
        fun showMessage(msg: String)
    }

    interface Presenter {
        fun bindView(view: View)
        fun unbindView()
        fun onViewCreated(movieId: Int)
        fun onBackClicked()
    }

    interface Interactor {
        fun getMovieDetails(
            movieId: Int,
            onSuccess: (DetailMovie) -> Unit,
            onError: (Throwable) -> Unit,
        )

        fun dispose()
    }

    interface Router {
        fun finish()
    }

    interface Repo {
        fun getMovieDetails(movieId: Int): Single<DetailMovie>
    }
}