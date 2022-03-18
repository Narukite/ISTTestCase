package com.unknowncompany.isttestcase.ui.seeall

import com.unknowncompany.isttestcase.app.model.Movie
import io.reactivex.Single

interface SeeAllContract {
    interface View {
        fun getMoviesTypeFromIntent(): String
        fun showLoading()
        fun showLoadingAndHideList()
        fun hideLoading()
        fun hideLoadingAndShowList()
        fun publishData(data: List<Movie>)
        fun showMessage(msg: String)
    }

    interface Presenter {
        fun bindView(view: View)
        fun unbindView()
        fun onViewCreated()
        fun onBackClicked()
        fun onItemClicked(movieId: Int)
        fun onLastItemReached()
    }

    interface Interactor {
        fun getMoviesNowPlaying(onSuccess: (List<Movie>) -> Unit, onError: (Throwable) -> Unit)
        fun getMoviesUpcoming(onSuccess: (List<Movie>) -> Unit, onError: (Throwable) -> Unit)
        fun dispose()
    }

    interface Router {
        fun finish()
        fun openMovieDetails(movieId: Int)
    }

    interface Repo {
        fun getMoviesNowPlaying(): Single<List<Movie>>
        fun getMoviesUpcoming(): Single<List<Movie>>
    }
}