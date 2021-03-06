package com.unknowncompany.isttestcase.ui.main

import com.unknowncompany.isttestcase.app.model.Movie
import io.reactivex.Single

interface MainContract {
    interface View {
        fun showLoadingForNowPLaying()
        fun hideLoadingForNowPlaying()
        fun showLoadingForUpcoming()
        fun hideLoadingForUpcoming()
        fun publishDataForNowPlaying(data: List<Movie>)
        fun publishDataForUpcoming(data: List<Movie>)
        fun showMessage(msg: String)
    }

    interface Presenter {
        fun bindView(view: View)
        fun unbindView()
        fun onViewCreated()
        fun onItemClicked(movieId: Int)
        fun onSeeAllOfNowPlayingClicked()
        fun onSeeAllOfUpcomingClicked()
    }

    interface Interactor {
        fun getMoviesNowPlaying(onSuccess: (List<Movie>) -> Unit, onError: (Throwable) -> Unit)
        fun getMoviesUpcoming(onSuccess: (List<Movie>) -> Unit, onError: (Throwable) -> Unit)
        fun dispose()
    }

    interface Router {
        fun openMovieDetails(movieId: Int)
        fun openMoviesSeeAllOfNowPlaying()
        fun openMoviesSeeAllOfUpcoming()
    }

    interface Repo {
        fun getMoviesNowPlaying(): Single<List<Movie>>
        fun getMoviesUpcoming(): Single<List<Movie>>
    }
}