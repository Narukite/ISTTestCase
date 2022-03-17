package com.unknowncompany.isttestcase.ui.main

class MainPresenter(
    private val router: MainContract.Router,
    private val interactor: MainContract.Interactor,
) :
    MainContract.Presenter {

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

    private fun onErrorForNowPlaying(error: Throwable) {
        view?.hideLoadingForNowPlaying()
        error.message?.let { view?.showMessage(it) }
    }

    private fun onErrorForUpcoming(error: Throwable) {
        view?.hideLoadingForUpcoming()
        error.message?.let { view?.showMessage(it) }
    }
}