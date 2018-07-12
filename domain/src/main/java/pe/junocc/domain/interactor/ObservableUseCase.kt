package pe.junocc.domain.interactor

import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import pe.junocc.domain.executor.PostExecutionThread


abstract class ObservableUseCase<T, in Params> constructor(private val postExecutionThread: PostExecutionThread){

    private val disposables = CompositeDisposable()


    protected abstract fun buildUseCaseObservable(params: Params? = null) : Observable<T>

    open fun execute(observable: DisposableObserver<T>, params: Params? = null){
        val single = this.buildUseCaseObservable(params)
                .subscribeOn(Schedulers.io())
                .observeOn(postExecutionThread.scheduler)

        disposables.add(single.subscribeWith(observable))
    }

    fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

    fun dispose() {
        if (!disposables.isDisposed) disposables.dispose()
    }

}