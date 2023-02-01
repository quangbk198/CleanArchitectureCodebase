package com.quangnh.domain.mapper

import com.quangnh.data.datasource.local.entity.WordInfoEntity
import com.quangnh.data.datasource.remote.dto.WordDto
import com.quangnh.domain.model.WordInfo
import javax.inject.Inject

class WordInfoMapper @Inject constructor() {

    fun mapFromDtoToEntity(wordDto: WordDto): WordInfoEntity {
        val listDefinition = mutableListOf<String>()

        wordDto.results?.forEach { result ->
            result.lexicalEntries?.forEach { lexicalEntry ->
                lexicalEntry.entries?.forEach { entry ->
                    entry.senses?.forEach { sense ->
                        sense.definitions?.let { listDefinition.addAll(it) }
                    }
                }
            }
        }

        return WordInfoEntity(
            word = wordDto.word,
            definitions = listDefinition,
            timeStamp = System.currentTimeMillis()
        )
    }

    fun mapFromEntityToDomainModel(wordInfoEntity: WordInfoEntity): WordInfo {
        return WordInfo(
            id = wordInfoEntity.id,
            word = wordInfoEntity.word,
            definitions = wordInfoEntity.definitions,
            timeStamp = wordInfoEntity.timeStamp
        )
    }
}