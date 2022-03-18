package com.unknowncompany.isttestcase.ui.seeall

import com.unknowncompany.isttestcase.app.model.Movie
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class SeeAllInteractor(private val repo: SeeAllContract.Repo) : SeeAllContract.Interactor {

    private val compositeDisposable = CompositeDisposable()

    override fun getMoviesNowPlaying(
        onSuccess: (List<Movie>) -> Unit,
        onError: (Throwable) -> Unit,
    ) {
        val disposable = repo.getMoviesNowPlaying()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError(onError)
            .doOnSuccess(onSuccess)
            .subscribe()

        compositeDisposable.add(disposable)
    }

    override fun getMoviesUpcoming(
        onSuccess: (List<Movie>) -> Unit,
        onError: (Throwable) -> Unit,
    ) {
        val disposable = repo.getMoviesUpcoming()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError(onError)
            .doOnSuccess(onSuccess)
            .subscribe()

        compositeDisposable.add(disposable)
    }

    override fun dispose() = compositeDisposable.dispose()
}