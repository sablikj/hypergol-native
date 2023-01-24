package com.example.hypergol.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.hypergol.data.local.dao.LaunchRemoteKeysDao
import com.example.hypergol.data.local.dao.UpcomingLaunchDao
import com.example.hypergol.model.LaunchRemoteKeys
import com.example.hypergol.model.UpcomingLaunch

@Database(entities = [UpcomingLaunch::class, LaunchRemoteKeys::class], version = 1)
abstract class HypergolDatabase : RoomDatabase()  {

    abstract fun upcomingLaunchDao(): UpcomingLaunchDao
    abstract fun launchRemoteKeysDao(): LaunchRemoteKeysDao
}