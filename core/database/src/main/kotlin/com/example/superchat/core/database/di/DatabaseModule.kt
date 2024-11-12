package com.example.superchat.core.database.di

import android.content.Context
import androidx.room.Room
import com.example.superchat.core.database.SuperChatDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {
    @Provides
    @Singleton
    fun providesNiaDatabase(
        @ApplicationContext context: Context,
    ): SuperChatDatabase = Room.databaseBuilder(
        context,
        SuperChatDatabase::class.java,
        "superchat-database",
    ).build()
}