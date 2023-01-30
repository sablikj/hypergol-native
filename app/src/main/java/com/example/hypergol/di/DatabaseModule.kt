package com.example.hypergol.di

import android.content.Context
import androidx.room.Room
import com.example.hypergol.data.local.HypergolDatabase
import com.example.hypergol.util.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): HypergolDatabase {
        return Room.databaseBuilder(
            context,
            HypergolDatabase::class.java,
            DATABASE_NAME
        ).build()
    }
}