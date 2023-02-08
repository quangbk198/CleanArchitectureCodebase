package com.quangnh.domain.usecase

import com.quangnh.domain.model.WordInfo
import com.quangnh.domain.repository.word.WordInfoRepository
import javax.inject.Inject

class GetAllWordLocal @Inject constructor(
    private val wordInfoRepository: WordInfoRepository
) {
    suspend operator fun invoke(): List<WordInfo> {
        return wordInfoRepository.getAllWordLocal()
    }
}