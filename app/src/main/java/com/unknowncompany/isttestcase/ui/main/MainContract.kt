package com.unknowncompany.isttestcase.ui.main

import com.unknowncompany.isttestcase.app.model.MainMovie
import io.reactivex.Single

interface MainContract {
    interface View {
        fun showLoadingForNowPLaying()
        fun hideLoadingForNowPlaying()
        fun showLoadingForUpcoming()
        fun hideLoadingForUpcoming()
        fun publishDataForNowPlaying(data: List<MainMovie>)
        fun publishDataForUpcoming(data: List<MainMovie>)
        fun showMessage(msg: String)
    }

    interface Presenter {
        fun bindView(view: View)
        fun unbindView()
        fun onViewCreated()
    }

    interface Interactor {
        fun getMoviesNowPlaying(onSuccess: (List<MainMovie>) -> Unit, onError: (Throwable) -> Unit)
        fun getMoviesUpcoming(onSuccess: (List<MainMovie>) -> Unit, onError: (Throwable) -> Unit)
        fun dispose()
    }

    interface Router {
    }

    interface Repo {
        fun getMoviesNowPlaying(): Single<List<MainMovie>>
        fun getMoviesUpcoming(): Single<List<MainMovie>>
    }
}