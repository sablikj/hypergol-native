package com.example.hypergol.screens.launches

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ChipDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.ExperimentalPagingApi
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.hypergol.R
import com.example.hypergol.model.Launch
import com.example.hypergol.util.getRemainingTime
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds


@ExperimentalCoilApi
@ExperimentalPagingApi
@Composable
fun UpcomingLaunchesScreen(
    upcomingLaunchesViewModel: UpcomingLaunchesViewModel = hiltViewModel(),
    onDetailClicked: (String) -> Unit
){
    val upcomingLaunches = upcomingLaunchesViewModel.upcomingLaunches.collectAsLazyPagingItems()

    // Other upcoming launches
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
                            text = "T - $remainingTime",
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
                    UpcomingLaunchItem(launch = launch, onDetailClicked = onDetailClicked)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
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

    ElevatedCard(
        onClick = { onDetailClicked(launch.id) },
        colors = CardDefaults.cardColors(
            containerColor =  MaterialTheme.colorScheme.primary,
        ),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Box(
            Modifier
            .fillMaxWidth(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Column {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(CardDefaults.elevatedShape),
                    painter = painter,
                    contentDescription = launch.name,
                    contentScale = ContentScale.Crop,

                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 6.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = launch.name,
                        color = Color.Black,
                        fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                        fontWeight = FontWeight.Bold,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )
                    ElevatedSuggestionChip(
                        colors = SuggestionChipDefaults.elevatedSuggestionChipColors(
                            containerColor =  MaterialTheme.colorScheme.primaryContainer),
                        onClick = {},
                        label = {
                            Text(
                                text = "T - ${launch.net?.let { getRemainingTime(it, false) }}",
                                color = MaterialTheme.colorScheme.onPrimaryContainer,
                                fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                                fontWeight = FontWeight.Normal
                            )
                        }
                    )
                }
                Row(
                    modifier = Modifier

                        .fillMaxWidth()
                        .padding(horizontal = 6.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Text(
                        text = "${launch.launch_service_provider?.name} | ${launch.formattedNet}",
                        color = MaterialTheme.colorScheme.onSecondary,
                        fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                        fontWeight = FontWeight.Medium
                    )
                }
                Divider(
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 2.dp),
                    color = MaterialTheme.colorScheme.surface

                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 6.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ){
                    ElevatedSuggestionChip(
                        colors = SuggestionChipDefaults.elevatedSuggestionChipColors(
                            containerColor =  MaterialTheme.colorScheme.secondaryContainer),
                        onClick = {},
                        label = {
                            Text(
                                text = "${launch.mission?.orbit?.name}",
                                color = MaterialTheme.colorScheme.onSecondaryContainer,
                                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                                fontWeight = FontWeight.Medium
                            )
                        },
                        icon = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_orbit),
                                contentDescription = "Orbit",
                                modifier = Modifier
                                    .size(ChipDefaults.LeadingIconSize)
                                    .wrapContentSize(align = Alignment.Center)
                            )
                        }
                    )
                    ElevatedSuggestionChip(
                        colors = SuggestionChipDefaults.elevatedSuggestionChipColors(
                            containerColor =  MaterialTheme.colorScheme.secondaryContainer),
                        onClick = {},
                        label = {
                            Text(
                                text = "${launch.mission?.type}",
                                color = MaterialTheme.colorScheme.onSecondaryContainer,
                                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                                fontWeight = FontWeight.Medium
                            )
                        },
                        icon = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_outline_info_24),
                                contentDescription = "Mission type",
                                modifier = Modifier
                                    .size(ChipDefaults.LeadingIconSize)
                                    .wrapContentSize(align = Alignment.Center)
                            )
                        }
                    )
                }
            }
        }
    }
}