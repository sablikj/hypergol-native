package com.example.hypergol.util

object Constants {

    const val API_BASE_DEV = "https://lldev.thespacedevs.com/2.2.0/"
    const val API_BASE_PROD = "https://ll.thespacedevs.com/2.2.0/"

    // Object tables
    const val LAUNCH_TABLE = "launch_table"
    const val LAUNCH_DETAIL_TABLE = "launch_detail_table"

    // Key tables
    const val UPCOMING_LAUNCH_REMOTE_KEYS_TABLE = "upcoming_launch_remote_keys"
    const val LAUNCH_REMOTE_KEYS_TABLE = "launch_remote_keys"

    const val DATABASE_NAME = "hypergol_database"

    const val ITEMS_PER_PAGE = 8

    const val TWITTER_TIMELINE_DARK = "<a class=\"twitter-timeline\" data-lang=\"en\" data-dnt=\"true\" data-theme=\"dark\" href=\"https://twitter.com/NASASpaceflight?ref_src=twsrc%5Etfw\">Tweets by NASASpaceflight</a> <script async src=\"https://platform.twitter.com/widgets.js\" charset=\"utf-8\"></script>"
    const val TWITTER_TIMELINE_LIGHT = "<a class=\"twitter-timeline\" data-lang=\"en\" data-dnt=\"true\" data-theme=\"light\" href=\"https://twitter.com/NASASpaceflight?ref_src=twsrc%5Etfw\">Tweets by NASASpaceflight</a> <script async src=\"https://platform.twitter.com/widgets.js\" charset=\"utf-8\"></script>"

    object Routes {
        // Main routes
        const val LAUNCH_DETAIL_ROUTE = "launch"
        const val WIKI_LAUNCHES_ROUTE = "wiki/launches"
        const val WIKI_ROCKETS_ROUTE = "wiki/rockets"
        const val WIKI_LSP_ROUTE = "wiki/lsp"
        const val WIKI_ASTRONAUTS_ROUTE = "wiki/astronauts"

        // Detail routes
        const val LAUNCH_DETAIL_ID = "launchId"
        const val ROCKET_DETAIL_ID = "rocketId"
        const val LSP_DETAIL_ID = "lspId"
        const val ASTRONAUT_DETAIL_ID = "astronautId"
    }

    object Graph {
        const val ROOT = "root_graph"
        const val WIKI = "wiki_graph"
    }
}