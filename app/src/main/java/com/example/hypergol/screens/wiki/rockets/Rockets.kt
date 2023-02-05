package com.example.hypergol.screens.wiki.rockets

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun RocketsScreen(rocketsViewModel: RocketsViewModel = hiltViewModel(),
                  onDetailClicked: (String) -> Unit
){
    Text(text = "Rockets")
}