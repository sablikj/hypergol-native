package com.example.hypergol.screens.common

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import androidx.compose.material3.Text
import androidx.compose.ui.tooling.preview.Preview
import com.example.hypergol.model.*
import com.example.hypergol.R
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow

@ExperimentalCoilApi
@Composable
fun ListContent(itemsList: LazyPagingItems<Launch>) {
    Log.d("ErrorLoadState", itemsList.loadState.toString())
    Log.d("launches", itemsList.itemCount.toString())
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(all = 12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ){
        items(itemsList,
            key = { upcomingLaunch ->
                upcomingLaunch.id
            }
        ){
            upcomingLaunch ->
            upcomingLaunch?.let { UpcomingLaunchItem(launch = it) }
        }
    }
}

@ExperimentalCoilApi
@Composable
fun UpcomingLaunchItem(launch: Launch){
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(launch.image)
            .crossfade(durationMillis = 1000)
            .error(R.drawable.ic_placeholder)
            .placeholder(R.drawable.ic_placeholder)
            .build(),
        contentScale = ContentScale.Fit
    )

    Card(
        onClick = { Log.d("click", "clicked on card") },
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Box(Modifier
            .fillMaxWidth(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Column() {
                Image(
                    modifier = Modifier.fillMaxWidth(),
                    painter = painter,
                    contentDescription = launch.name,
                    contentScale = ContentScale.Crop
                )
                Row(
                    modifier = Modifier
                        .height(30.dp)
                        .background(MaterialTheme.colorScheme.primary)
                        .fillMaxWidth()
                        .padding(horizontal = 6.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = launch.name,
                        color = Color.Black,
                        fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                        fontWeight = FontWeight.Bold,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = "NET",
                        color = Color.Black,
                        fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                        fontWeight = FontWeight.Bold,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Row(
                    modifier = Modifier
                        .height(25.dp)
                        .background(MaterialTheme.colorScheme.primary)
                        .fillMaxWidth()
                        .padding(horizontal = 6.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Text(
                        text = "${launch.mission?.type} | ${launch.launch_service_provider.name}",
                        color = Color.Gray,
                        fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                        fontWeight = FontWeight.Medium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}

@ExperimentalCoilApi
@Composable
@Preview
fun UpcomingLaunchPreview(){
    UpcomingLaunchItem(
        launch = Launch(
            id = "1",
            name = "Starling launch",
            net = "25/01/2023 15:56:02",
            launch_service_provider = LaunchProvider(
                id = 1,
                name = "SpaceX",
                type = "Private"
            ),
            rocket = Rocket(
                id = 1,
                configuration = Configuration(
                    id = 1,
                    name = "Falcon 9",
                    family = "Falcon",
                    full_name = "Falcon 9 Block 5",
                    variant = "Block 5",
                )
            ),
            mission = Mission(
                id = 1,
                name = "Starlink launch",
                type = "Communications",
                orbit = Orbit(id = 1, name = "LEO")
            ),
            pad = Pad(
                id = 1,
                name = "Pad"
            ),
            image = ""
        )
    )
}
