package com.example.hypergol.screens.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.ChipDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.hypergol.R
import com.example.hypergol.model.launch.Launch
import com.example.hypergol.util.getRemainingTime

@ExperimentalMaterialApi
@ExperimentalCoilApi
@Composable
fun LaunchItem(launch: Launch, onDetailClicked: (String) -> Unit, upcoming: Boolean){
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
                // Name, remaining time
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
                    if(upcoming){
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
                }
                // LSP, NET
                Row(
                    modifier = Modifier

                        .fillMaxWidth()
                        .padding(horizontal = 6.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Text(
                        text = "${launch.launch_service_provider?.name} \n${launch.formattedNet}",
                        color = MaterialTheme.colorScheme.onSecondary,
                        fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                        fontWeight = FontWeight.Medium
                    )
                }
                Divider(
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 2.dp),
                    color = MaterialTheme.colorScheme.surface

                )
                // Orbit, Mission type
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