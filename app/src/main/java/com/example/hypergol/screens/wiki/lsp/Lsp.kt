package com.example.hypergol.screens.wiki.lsp

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun LspScreen(lspViewModel: LspViewModel = hiltViewModel(),
                  onDetailClicked: (String) -> Unit
){
    Text(text = "Launch service providers")
}