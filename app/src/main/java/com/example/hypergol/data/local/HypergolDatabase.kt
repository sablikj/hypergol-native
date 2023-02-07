package com.example.hypergol.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.hypergol.data.local.dao.*
import com.example.hypergol.model.agency.AgencyRemoteKeys
import com.example.hypergol.model.launch.LaunchRemoteKeys
import com.example.hypergol.model.launch.Launch
import com.example.hypergol.model.LaunchDetail
import com.example.hypergol.model.common.Agency
import com.example.hypergol.model.launch.UpcomingLaunchRemoteKeys
import com.example.hypergol.util.Converters


@TypeConverters(Converters::class)
@Database(
    entities = [
        Launch::class,
        LaunchRemoteKeys::class,
        LaunchDetail::class,
        UpcomingLaunchRemoteKeys::class,
        Agency::class,
        AgencyRemoteKeys::class
               ],
    version = 1,/*
    autoMigrations = [
        autoMigration(from = 1, to = 2)
    ]*/)
abstract class HypergolDatabase : RoomDatabase()  {

    // Objects
    abstract fun launchDao(): LaunchDao
    abstract fun launchDetailDao(): LaunchDetailDao
    abstract fun agencyDao(): AgencyDao

    // Keys
    abstract fun upcomingLaunchRemoteKeysDao(): UpcomingLaunchRemoteKeysDao
    abstract fun launchRemoteKeysDao(): LaunchRemoteKeysDao
    abstract fun agencyRemoteKeysDao(): AgencyRemoteKeysDao
}