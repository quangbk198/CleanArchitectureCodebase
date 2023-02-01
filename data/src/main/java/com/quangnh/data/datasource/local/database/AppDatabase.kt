package com.quangnh.data.datasource.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.quangnh.data.datasource.local.converters.Converters
import com.quangnh.data.datasource.local.dao.WordInfoDao
import com.quangnh.data.datasource.local.entity.WordInfoEntity

/**
 * Created by quangnh
 * Date: 31/1/2023
 * Time: 11:30 PM
 * Project DictionaryApp
 */
@Database(
    entities = [WordInfoEntity::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract val wordInfoDao: WordInfoDao

}