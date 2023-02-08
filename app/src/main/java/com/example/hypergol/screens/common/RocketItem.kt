package com.example.hypergol.screens.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.ChipDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
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
import com.example.hypergol.model.rocket.Rocket

@ExperimentalMaterialApi
@ExperimentalCoilApi
@Composable
fun RocketItem(rocket: Rocket, onDetailClicked: (Int) -> Unit){
    val imagePainter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(rocket.image_url)
            .crossfade(durationMillis = 1000)
            .error(R.drawable.ic_placeholder)
            .placeholder(R.drawable.ic_placeholder)
            .build(),
        contentScale = ContentScale.Fit
    )

    ElevatedCard(
        onClick = { onDetailClicked(rocket.id) },
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
                if(!rocket.image_url.isNullOrEmpty()){
                    Image(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(CardDefaults.elevatedShape),
                        painter = imagePainter,
                        contentDescription = rocket.full_name,
                        contentScale = ContentScale.Crop,
                    )
                }

                // Name, status
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 6.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = "${rocket.full_name}",
                        color = Color.Black,
                        fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                        fontWeight = FontWeight.Bold,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                // Country code, Agency type
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
                                text = if(rocket.active) {"Active"}else{"Discontinued"},
                                color = MaterialTheme.colorScheme.onSecondaryContainer,
                                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                                fontWeight = FontWeight.Medium
                            )
                        },
                        icon = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_outline_info_24),
                                contentDescription = "Status",
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
                            androidx.compose.material3.Text(
                                text = if(rocket.reusable){"Reusable"}else{"Expendable"},
                                color = MaterialTheme.colorScheme.onSecondaryContainer,
                                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                                fontWeight = FontWeight.Medium
                            )
                        },
                        icon = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_outline_info_24),
                                contentDescription = "Number of flights",
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