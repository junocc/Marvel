package pe.junocc.domain.interactor.character

import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import pe.junocc.domain.executor.PostExecutionThread
import pe.junocc.domain.model.Character
import pe.junocc.domain.repository.CharacterRepository
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GetCharactersTest{

    private lateinit var getCharacters: GetCharacters
    @Mock lateinit var repository: CharacterRepository
    @Mock lateinit var postExecutionThread: PostExecutionThread



    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        getCharacters = GetCharacters(repository, postExecutionThread)
    }

    @Test
    fun getCharacterCompletes(){
        whenever(repository.getCharacters()).thenReturn(dataFake())
        val testObserver = getCharacters.buildUseCaseObservable().test()
        testObserver.assertComplete()
    }

    private fun dataFake(): Observable<List<Character>>? {
        val item1 = Character(1,"Luchito", "Murio")
        val item2 = Character(2,"Cristian", "Pinguino")
        return Observable.just(listOf(item1, item2))
    }


}