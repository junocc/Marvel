package pe.junocc.domain.repository

import io.reactivex.Observable
import pe.junocc.domain.model.Character

interface CharacterRepository {

    fun getCharacters(): Observable<List<Character>>
    fun getCharacterById(id: Int): Observable<Character>

}