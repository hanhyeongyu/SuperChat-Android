package com.example.superchat.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.superchat.core.database.entity.TestEntity
import com.example.superchat.core.database.util.InstantConverter


@Database(
    entities = [
        TestEntity::class
    ],
    version = 14,
    autoMigrations = [
    ],
    exportSchema = true,
)
@TypeConverters(
    InstantConverter::class,
)
internal abstract class SuperChatDatabase : RoomDatabase() {
    //abstract fun topicDao(): TopicDao
}
