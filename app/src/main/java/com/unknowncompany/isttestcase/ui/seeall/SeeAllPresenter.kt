package com.unknowncompany.isttestcase.ui.seeall

import android.util.Log

class SeeAllPresenter(
    private val router: SeeAllContract.Router,
    private val interactor: SeeAllContract.Interactor,
) : SeeAllContract.Presenter {

    private var view: SeeAllContract.View? = null

    override fun bindView(view: SeeAllContract.View) {
        this.view = view
    }

    override fun unbindView() {
        view = null
        interactor.dispose()
    }

    override fun onViewCreated() {
        view?.showLoadingAndHideList()
        view?.getMoviesTypeFromIntent()?.let { moviesType ->
            when (moviesType) {
                SeeAllActivity.NOW_PLAYING -> interactor.getMoviesNowPlaying({
                    view?.hideLoadingAndShowList()
                    view?.publishData(it)
                }, this::onError)
                SeeAllActivity.UPCOMING -> interactor.getMoviesUpcoming({
                    view?.hideLoadingAndShowList()
                    view?.publishData(it)
                }, this::onError)
                else -> router.finish()
            }
        }
    }

    override fun onBackClicked() {
        router.finish()
    }

    override fun onItemClicked(movieId: Int) {
        router.openMovieDetails(movieId)
    }

    override fun onLastItemReached() {
        view?.showLoading()
        view?.getMoviesTypeFromIntent()?.let { moviesType ->
            when (moviesType) {
                SeeAllActivity.NOW_PLAYING -> interactor.getMoviesNowPlaying({
                    view?.hideLoading()
                    view?.publishData(it)
                }, this::onError)
                SeeAllActivity.UPCOMING -> interactor.getMoviesUpcoming({
                    view?.hideLoading()
                    view?.publishData(it)
                }, this::onError)
                else -> router.finish()
            }
        }
    }

    private fun onError(error: Throwable) {
        Log.d(SeeAllPresenter::class.simpleName, "onError: ${error.message}")

        view?.hideLoading()
        error.message?.let { view?.showMessage(it) }
    }

}