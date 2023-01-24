package com.example.hypergol.di

import android.content.Context
import androidx.room.Room
import com.example.hypergol.data.local.HypergolDatabase
import com.example.hypergol.util.Constants.HYPERGOL_DATABASE
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    fun provideDatabase(
        @ApplicationContext context: Context
    ): HypergolDatabase {
        return Room.databaseBuilder(
            context,
            HypergolDatabase::class.java,
            HYPERGOL_DATABASE
        ).build()
    }
}