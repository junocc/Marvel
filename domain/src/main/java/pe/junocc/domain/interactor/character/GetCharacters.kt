package pe.junocc.domain.interactor.character

import io.reactivex.Observable
import pe.junocc.domain.executor.PostExecutionThread
import pe.junocc.domain.interactor.ObservableUseCase
import pe.junocc.domain.model.Character
import pe.junocc.domain.repository.CharacterRepository
import javax.inject.Inject

class GetCharacters @Inject constructor(
        private val characterRepository: CharacterRepository, private val postExecutionThread: PostExecutionThread)
    : ObservableUseCase<List<Character>, Nothing>(postExecutionThread){


    public override fun buildUseCaseObservable(params: Nothing?): Observable<List<Character>> {
        return characterRepository.getCharacters()
    }

}