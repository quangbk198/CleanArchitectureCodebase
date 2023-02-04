package com.quangnh.domain.usecase

import com.quangnh.core.utils.Resource
import com.quangnh.domain.model.WordInfo
import com.quangnh.domain.repository.word.WordInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetWordInfo @Inject constructor(
    private val wordInfoRepository: WordInfoRepository
) {
    operator fun invoke(word: String): Flow<Resource<WordInfo>> {
        if (word.isBlank()) return flow {  }

        return wordInfoRepository.getWordInfo(word)
    }
}