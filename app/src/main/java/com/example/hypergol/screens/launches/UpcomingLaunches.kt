package com.example.hypergol.screens.launches

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.ExperimentalPagingApi
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.hypergol.R
import com.example.hypergol.model.Launch


@ExperimentalCoilApi
@ExperimentalPagingApi
@Composable
fun UpcomingLaunchesScreen(
    upcomingLaunchesViewModel: UpcomingLaunchesViewModel = hiltViewModel(),
    onDetailClicked: (String) -> Unit
){
    val getAllUpcomingLaunches = upcomingLaunchesViewModel.getAllUpcomingLaunches.collectAsLazyPagingItems()

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(all = 12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ){
        items(getAllUpcomingLaunches,
            key = { upcomingLaunch ->
                upcomingLaunch.id
            }
        ){
                upcomingLaunch ->
            upcomingLaunch?.let { UpcomingLaunchItem(launch = it, onDetailClicked = onDetailClicked) }
        }
    }
}

@ExperimentalCoilApi
@Composable
fun UpcomingLaunchItem(launch: Launch, onDetailClicked: (String) -> Unit){ //TODO: pass navcontroller for navigation
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
        onClick = { onDetailClicked(launch.id) },
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Box(
            Modifier
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
                        color = MaterialTheme.colorScheme.onPrimary,
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
                        text = "${launch.mission?.type} | ${launch.launch_service_provider?.name}",
                        color = MaterialTheme.colorScheme.onSecondary,
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