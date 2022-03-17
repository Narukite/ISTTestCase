package com.unknowncompany.isttestcase.ui.main

import android.util.Log

class MainPresenter(
    private val router: MainContract.Router,
    private val interactor: MainContract.Interactor,
) : MainContract.Presenter {

    private var view: MainContract.View? = null

    override fun bindView(view: MainContract.View) {
        this.view = view
    }

    override fun unbindView() {
        view = null
        interactor.dispose()
    }

    override fun onViewCreated() {
        view?.showLoadingForNowPLaying()
        view?.showLoadingForUpcoming()
        interactor.getMoviesNowPlaying({
            view?.hideLoadingForNowPlaying()
            view?.publishDataForNowPlaying(it)
        }, this::onErrorForNowPlaying)
        interactor.getMoviesUpcoming({
            view?.hideLoadingForUpcoming()
            view?.publishDataForUpcoming(it)
        }, this::onErrorForUpcoming)
    }

    override fun onItemClicked(movieId: Int) {
        router.openMovieDetails(movieId)
    }

    private fun onErrorForNowPlaying(error: Throwable) {
        Log.d(MainPresenter::class.simpleName, "onErrorForNowPlaying: ${error.message}")

        view?.hideLoadingForNowPlaying()
        error.message?.let { view?.showMessage(it) }
    }

    private fun onErrorForUpcoming(error: Throwable) {
        Log.d(MainPresenter::class.simpleName, "onErrorForNowPlaying: ${error.message}")

        view?.hideLoadingForUpcoming()
        error.message?.let { view?.showMessage(it) }
    }
}