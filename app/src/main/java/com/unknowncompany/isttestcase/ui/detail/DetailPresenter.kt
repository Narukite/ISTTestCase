package com.unknowncompany.isttestcase.ui.detail

class DetailPresenter(
    private val router: DetailContract.Router,
    private val interactor: DetailContract.Interactor,
) : DetailContract.Presenter {

    private var view: DetailContract.View? = null

    override fun bindView(view: DetailContract.View) {
        this.view = view
    }

    override fun unbindView() {
        view = null
        interactor.dispose()
    }

    override fun onViewCreated() {
        view?.showLoading()
        view?.getMovieIdFromIntent()?.let { movieId ->
            interactor.getMovieDetails(movieId, { data ->
                view?.hideLoading()
                view?.populateView(data)
            }, this::onError)
        }
    }

    override fun onBackClicked() {
        router.finish()
    }

    private fun onError(error: Throwable) {
        view?.hideLoading()
        error.message?.let { view?.showMessage(it) }
    }

}