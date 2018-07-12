package pe.junocc.domain.interactor

import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.schedulers.Schedulers
import pe.junocc.domain.executor.PostExecutionThread

abstract class CompletableUseCase<T, in Params> constructor(
        private val postExecutionThread: PostExecutionThread){

    private val disposables = CompositeDisposable()


    protected abstract fun buildUseCaseCompletable(params: Params? = null) : Completable

    open fun execute(singleObserver: DisposableCompletableObserver, params: Params? = null){
        val completable = this.buildUseCaseCompletable(params)
                .subscribeOn(Schedulers.io())
                .observeOn(postExecutionThread.scheduler)

        disposables.add(completable.subscribeWith(singleObserver))
    }

    fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

    fun dispose() {
        if (!disposables.isDisposed) disposables.dispose()
    }

}