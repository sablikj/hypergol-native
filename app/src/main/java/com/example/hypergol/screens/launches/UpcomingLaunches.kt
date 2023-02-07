package com.example.hypergol.screens.launches

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import coil.annotation.ExperimentalCoilApi
import com.example.hypergol.screens.common.LaunchItem
import com.example.hypergol.util.getRemainingTime
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds


@ExperimentalMaterialApi
@ExperimentalCoilApi
@ExperimentalPagingApi
@Composable
fun UpcomingLaunchesScreen(
    upcomingLaunchesViewModel: UpcomingLaunchesViewModel = hiltViewModel(),
    onDetailClicked: (String) -> Unit
){
    val upcomingLaunches = upcomingLaunchesViewModel.upcomingLaunches.collectAsLazyPagingItems()

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(all = 12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ){
        itemsIndexed(upcomingLaunches
        ){
            i, launch ->
            launch?.let {
                if(i == 0){
                    // Upcoming launch info card
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor =  MaterialTheme.colorScheme.primary,
                        ),
                        elevation = CardDefaults.cardElevation(),
                        onClick = { onDetailClicked(launch.id) },
                        modifier = Modifier
                            .fillMaxSize()
                    ){
                        // Countdown
                        // TODO: Implement countdown
                        var remainingTime by remember { mutableStateOf("") }
                        LaunchedEffect(Unit){
                            while(true){
                                delay(1.seconds)
                                remainingTime = launch.net?.let { getRemainingTime(it, true) }.toString()
                            }
                        }

                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(6.dp),
                            text = remainingTime,
                            color = Color.Black,
                            fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            modifier = Modifier
                                .padding(6.dp),
                            text = buildAnnotatedString {
                                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                    append(launch.launch_service_provider?.name)
                                }
                                append(" will launch ")
                                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                    append(launch.mission?.name)
                                }
                                append(" mission on it's ")
                                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                    append(launch.rocket?.configuration?.name)
                                }
                                append(" rocket from ")
                                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                    append(launch.pad?.name)
                                }
                                if(launch.pad?.name != "Unknown Pad"){
                                    append(" launch pad. ")
                                }
                            },
                            color = MaterialTheme.colorScheme.onPrimary,
                            fontSize = MaterialTheme.typography.bodyMedium.fontSize
                        )
                    }
                }else{
                    // Other upcoming launches
                    LaunchItem(launch = launch, onDetailClicked = onDetailClicked, upcoming = true)
                }
            }
        }
    }
}