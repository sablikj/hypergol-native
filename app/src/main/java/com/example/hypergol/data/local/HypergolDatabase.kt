package com.example.hypergol.data.local

import android.util.Log
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.hypergol.data.local.dao.LaunchDetailDao
import com.example.hypergol.data.local.dao.LaunchRemoteKeysDao
import com.example.hypergol.data.local.dao.UpcomingLaunchDao
import com.example.hypergol.data.remote.LaunchApi
import com.example.hypergol.model.LaunchRemoteKeys
import com.example.hypergol.model.Launch
import com.example.hypergol.model.LaunchDetail
import com.example.hypergol.util.Converters


@TypeConverters(Converters::class)
@Database(
    entities = [Launch::class, LaunchRemoteKeys::class, LaunchDetail::class],
    version = 2,
    autoMigrations = [
        AutoMigration(from = 1, to = 2)
    ])
abstract class HypergolDatabase : RoomDatabase()  {

    abstract fun upcomingLaunchDao(): UpcomingLaunchDao
    abstract fun launchRemoteKeysDao(): LaunchRemoteKeysDao
    abstract fun launchDetailDao(): LaunchDetailDao
}