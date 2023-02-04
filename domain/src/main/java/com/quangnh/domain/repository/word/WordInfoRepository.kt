package com.quangnh.domain.repository.word

import com.quangnh.core.utils.Resource
import com.quangnh.domain.model.WordInfo
import kotlinx.coroutines.flow.Flow

interface WordInfoRepository {

    fun getWordInfo(word: String): Flow<Resource<WordInfo>>

}