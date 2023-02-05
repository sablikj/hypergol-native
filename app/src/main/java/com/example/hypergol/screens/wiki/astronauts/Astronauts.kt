package com.example.hypergol.screens.wiki.astronauts

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun AstronautsScreen(astronautsViewModel: AstronautsViewModel = hiltViewModel(),
                  onDetailClicked: (String) -> Unit
){
    Text(text = "Astronauts")
}