package com.uvg.rickandmorty.data.repository

import com.uvg.rickandmorty.data.model.Character
import com.uvg.rickandmorty.data.source.CharacterDb
import com.uvg.rickandmorty.domain.repository.CharacterRepository
import kotlinx.coroutines.delay

class LocalCharacterRepository: CharacterRepository {
    private val characterDb = CharacterDb()

    override suspend fun getCharacters(): List<Character> {
        delay(2000L)
        return characterDb.getAllCharacters()
    }

    override suspend fun getCharacterById(id: Int): Character {
        delay(2000L)
        return characterDb.getCharacterById(id)
    }
}