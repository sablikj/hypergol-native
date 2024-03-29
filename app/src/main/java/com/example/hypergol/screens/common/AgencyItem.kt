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
import com.example.hypergol.model.common.Agency

@ExperimentalMaterialApi
@ExperimentalCoilApi
@Composable
fun AgencyItem(agency: Agency, onDetailClicked: (Int) -> Unit){
    val imagePainter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(agency.image_url)
            .crossfade(durationMillis = 1000)
            .error(R.drawable.ic_placeholder)
            .placeholder(R.drawable.ic_placeholder)
            .build(),
        contentScale = ContentScale.Fit
    )

    val logoPainter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(agency.logo_url)
            .crossfade(durationMillis = 1000)
            .error(R.drawable.ic_placeholder)
            .placeholder(R.drawable.ic_placeholder)
            .build(),
        contentScale = ContentScale.Fit
    )

    ElevatedCard(
        onClick = { onDetailClicked(agency.id) },
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
                    painter = imagePainter,
                    contentDescription = agency.name,
                    contentScale = ContentScale.Crop,
                )
                // Name
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 6.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = agency.name.toString(),
                        color = Color.Black,
                        fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                        fontWeight = FontWeight.Bold,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )
                    Image(
                        modifier = Modifier
                            .height(64.dp)
                            .clip(CardDefaults.elevatedShape),
                        painter = logoPainter,
                        contentDescription = agency.name,
                        contentScale = ContentScale.Fit,
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
                                text = "${agency.country_code}",
                                color = MaterialTheme.colorScheme.onSecondaryContainer,
                                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                                fontWeight = FontWeight.Medium
                            )
                        },
                        icon = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_outline_location_on_24),
                                contentDescription = "Country code",
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
                                text = "${agency.type}",
                                color = MaterialTheme.colorScheme.onSecondaryContainer,
                                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                                fontWeight = FontWeight.Medium
                            )
                        },
                        icon = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_outline_info_24),
                                contentDescription = "Agency type",
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