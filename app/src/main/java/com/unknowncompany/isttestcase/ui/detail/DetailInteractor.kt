package com.unknowncompany.isttestcase.ui.detail

import com.unknowncompany.isttestcase.app.model.DetailMovie
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class DetailInteractor(private val repo: DetailContract.Repo) : DetailContract.Interactor {

    private val compositeDisposable = CompositeDisposable()

    override fun getMovieDetails(
        movieId: Int,
        onSuccess: (DetailMovie) -> Unit,
        onError: (Throwable) -> Unit,
    ) {
        val disposable = repo.getMovieDetails(movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError(onError)
            .doOnSuccess(onSuccess)
            .subscribe()

        compositeDisposable.add(disposable)
    }

    override fun dispose() = compositeDisposable.dispose()
}