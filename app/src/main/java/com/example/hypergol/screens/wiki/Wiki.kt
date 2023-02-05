package com.example.hypergol.screens.wiki

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.ExperimentalPagingApi
import coil.annotation.ExperimentalCoilApi
import com.example.hypergol.R
import com.example.hypergol.util.Constants.Routes


@ExperimentalCoilApi
@ExperimentalPagingApi
@ExperimentalMaterialApi
@ExperimentalMaterial3Api
@Composable
fun WikiScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxWidth()
    ) {
        Card(
            onClick = { navController.navigate(Routes.WIKI_LAUNCHES_ROUTE) },
            modifier = Modifier
                .padding(6.dp)
                .fillMaxWidth()
        ) {
            Box(
                Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = Modifier.fillMaxWidth(),
                    painter = painterResource(id = R.drawable.launches),
                    contentDescription = "Rocket launches",
                    contentScale = ContentScale.Crop
                )
                Text( //TODO: Move text to string.xml
                    text = "Launches",
                    color = Color.White,
                    fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        Card(
            onClick = { navController.navigate(Routes.WIKI_ROCKETS_ROUTE) },
            modifier = Modifier
                .padding(6.dp)
                .fillMaxWidth()
        ) {
            Box(
                Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = Modifier.fillMaxWidth(),
                    painter = painterResource(id = R.drawable.rockets),
                    contentDescription = "Rockets",
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = "Rockets",
                    color = Color.White,
                    fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        Card(
            onClick = { navController.navigate(Routes.WIKI_LSP_ROUTE) },
            modifier = Modifier
                .padding(6.dp)
                .fillMaxWidth()
        ) {
            Box(
                Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = Modifier.fillMaxWidth(),
                    painter = painterResource(id = R.drawable.lsp),
                    contentDescription = "Launch Providers",
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = "Launch Providers",
                    color = Color.White,
                    fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        Card(
            onClick = { navController.navigate(Routes.WIKI_ASTRONAUTS_ROUTE)},
            modifier = Modifier
                .padding(6.dp)
                .fillMaxWidth()
        ) {
            Box(
                Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = Modifier.fillMaxWidth(),
                    painter = painterResource(id = R.drawable.astronauts),
                    contentDescription = "Astronauts",
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = "Astronauts",
                    color = Color.White,
                    fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}