package com.example.hypergol.screens.wiki

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hypergol.R
import com.example.hypergol.screens.common.ListContent
import kotlinx.serialization.json.JsonNull.content


@ExperimentalMaterial3Api
@Composable
fun WikiScreen() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxWidth()
    ) {
        Card(
            onClick = { Log.d("click", "clicked on card") },
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
            onClick = { Log.d("click", "clicked on card") },
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
            onClick = { Log.d("click", "clicked on card") },
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
            onClick = { Log.d("click", "clicked on card") },
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

@Preview
@Composable
fun WikiScreenPreview(){
    WikiScreen()
}