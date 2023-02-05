package com.example.hypergol.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.hypergol.data.local.dao.LaunchDetailDao
import com.example.hypergol.data.local.dao.UpcomingLaunchRemoteKeysDao
import com.example.hypergol.data.local.dao.LaunchDao
import com.example.hypergol.data.local.dao.LaunchRemoteKeysDao
import com.example.hypergol.model.launch.LaunchRemoteKeys
import com.example.hypergol.model.launch.Launch
import com.example.hypergol.model.LaunchDetail
import com.example.hypergol.model.launch.UpcomingLaunchRemoteKeys
import com.example.hypergol.util.Converters


@TypeConverters(Converters::class)
@Database(
    entities = [Launch::class, LaunchRemoteKeys::class, LaunchDetail::class, UpcomingLaunchRemoteKeys::class],
    version = 1,/*
    autoMigrations = [
        autoMigration(from = 1, to = 2)
    ]*/)
abstract class HypergolDatabase : RoomDatabase()  {

    // Objects
    abstract fun launchDao(): LaunchDao
    abstract fun launchDetailDao(): LaunchDetailDao

    // Keys
    abstract fun upcomingLaunchRemoteKeysDao(): UpcomingLaunchRemoteKeysDao
    abstract fun launchRemoteKeysDao(): LaunchRemoteKeysDao
}