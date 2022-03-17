package com.unknowncompany.isttestcase.ui.main

import com.unknowncompany.isttestcase.app.model.MainMovie
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainInteractor(private val repo: MainContract.Repo) : MainContract.Interactor {

    private val compositeDisposable = CompositeDisposable()

    override fun getMoviesNowPlaying(
        onSuccess: (List<MainMovie>) -> Unit,
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
        onSuccess: (List<MainMovie>) -> Unit,
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