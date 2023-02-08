package com.example.hypergol.util

object Constants {

    const val API_BASE_DEV = "https://lldev.thespacedevs.com/2.2.0/"
    const val API_BASE_PROD = "https://ll.thespacedevs.com/2.2.0/"

    // Object tables
    const val LAUNCH_TABLE = "launch_table"
    const val LAUNCH_DETAIL_TABLE = "launch_detail_table"
    const val AGENCY_TABLE = "launch_service_provider"
    const val ROCKET_TABLE = "rocket_detail_table"

    // Key tables
    const val UPCOMING_LAUNCH_REMOTE_KEYS_TABLE = "upcoming_launch_remote_keys"
    const val LAUNCH_REMOTE_KEYS_TABLE = "launch_remote_keys"
    const val AGENCY_REMOTE_KEYS_TABLE = "agency_remote_keys"
    const val ROCKET_REMOTE_KEYS_TABLE = "rocket_remote_keys"

    const val DATABASE_NAME = "hypergol_database"

    const val ITEMS_PER_PAGE = 8

    const val TWITTER_TIMELINE_DARK = "<a class=\"twitter-timeline\" data-lang=\"en\" data-dnt=\"true\" data-theme=\"dark\" href=\"https://twitter.com/NASASpaceflight?ref_src=twsrc%5Etfw\">Tweets by NASASpaceflight</a> <script async src=\"https://platform.twitter.com/widgets.js\" charset=\"utf-8\"></script>"
    const val TWITTER_TIMELINE_LIGHT = "<a class=\"twitter-timeline\" data-lang=\"en\" data-dnt=\"true\" data-theme=\"light\" href=\"https://twitter.com/NASASpaceflight?ref_src=twsrc%5Etfw\">Tweets by NASASpaceflight</a> <script async src=\"https://platform.twitter.com/widgets.js\" charset=\"utf-8\"></script>"

    object Routes {
        // Main routes
        const val WIKI_LAUNCHES_ROUTE = "wiki/launches"
        const val WIKI_ROCKETS_ROUTE = "wiki/rockets"
        const val WIKI_AGENCY_ROUTE = "wiki/agency"
        const val WIKI_ASTRONAUTS_ROUTE = "wiki/astronauts"

        const val LAUNCH_DETAIL_ROUTE = "launch"
        const val LSP_DETAIL_ROUTE = "agency"
        const val ROCKET_DETAIL_ROUTE = "rocket"
        const val ASTRONAUT_DETAIL_ROUTE = "astronaut"

        // Detail keys
        const val LAUNCH_DETAIL_ID = "launchId"
        const val ROCKET_DETAIL_ID = "rocketId"
        const val LSP_DETAIL_ID = "lspId"
        const val ASTRONAUT_DETAIL_ID = "astronautId"

        // Search routes
        const val LAUNCH_SEARCH_ROUTE = "searchLaunch"
        const val ROCKET_SEARCH_ROUTE = "searchRocket"
        const val AGENCY_SEARCH_ROUTE = "searchAgency"
    }

    object Graph {
        const val ROOT = "root_graph"
        const val WIKI = "wiki_graph"
    }
}