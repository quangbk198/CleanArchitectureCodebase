package com.quangnh.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.quangnh.data.datasource.local.entity.WordInfoEntity

/**
 * Created by quangnh
 * Date: 31/1/2023
 * Time: 11:34 PM
 * Project DictionaryApp
 */
@Dao
interface WordInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWordInfo(word: WordInfoEntity)

    @Query("DELETE FROM WordInfoEntity WHERE id IN (:id)")
    suspend fun deleteWordInfoById(id: Int)

    @Query("DELETE FROM WordInfoEntity WHERE word IN (:word)")
    suspend fun deleteWordInfo(word: String)

    @Query("SELECT * FROM WordInfoEntity WHERE word IN (:word)")
    suspend fun getWordInfo(word: String): WordInfoEntity
}