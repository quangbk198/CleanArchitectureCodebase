package com.quangnh.domain.repository.word

import com.quangnh.core.utils.Resource
import com.quangnh.data.datasource.local.dao.WordInfoDao
import com.quangnh.data.datasource.local.database.AppDatabase
import com.quangnh.data.datasource.remote.ApplicationApi
import com.quangnh.domain.mapper.WordInfoMapper
import com.quangnh.domain.model.WordInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WordInfoRepositoryImpl @Inject constructor(
    private val appApi: ApplicationApi,
    private val appDatabase: AppDatabase,
    private val wordInfoMapper: WordInfoMapper
) : WordInfoRepository {

    /**
     * Work flow:
     * 1. Get word from local database -> emit word
     * 2. Get word from api
     * 3. Delete old word in local database
     * 4. Insert latest word into local database
     * 5. Emit latest word from local database to update UI
     */
    override fun getWordInfo(word: String): Flow<Resource<WordInfo>> = flow {
        emit(Resource.Loading())

        val wordInfo = wordInfoMapper.mapFromEntityToDomainModel(
            appDatabase.wordInfoDao.getWordInfo(word)
        )
        emit(Resource.Loading(data = wordInfo))

        try {
            // Get word from api
            val remoteWordInfo = appApi.getWordInfo(word)

            // Delete old word in local database
            remoteWordInfo.word?.let { appDatabase.wordInfoDao.deleteWordInfo(it) }

            // Insert latest word into local database
            appDatabase.wordInfoDao.insertWordInfo(
                wordInfoMapper.mapFromDtoToEntity(remoteWordInfo)
            )

        } catch (throwable: Throwable) {
            emit(
                Resource.Error(
                throwable = throwable,
                data = wordInfo
            ))
        }

        // Emit latest word from local database to update UI
        val newWordInfo = wordInfoMapper.mapFromEntityToDomainModel(
            appDatabase.wordInfoDao.getWordInfo(word)
        )
        emit(Resource.Success(newWordInfo))
    }

    override suspend fun getAllWordLocal(): List<WordInfo> {
        val listWordLocal = appDatabase.wordInfoDao.getAllWordFromLocal().map { wordEntity ->
            wordInfoMapper.mapFromEntityToDomainModel(wordEntity)
        }

        return listWordLocal
    }
}