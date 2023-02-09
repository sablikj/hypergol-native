package com.example.hypergol.screens.wiki.rockets.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ChipDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.hypergol.R
import com.example.hypergol.screens.common.CircularProgressBar
import com.example.hypergol.util.formatDate


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RocketDetailScreen() {
    val viewModel = hiltViewModel<RocketDetailViewModel>()
    val rocket = viewModel.rocket
    val loading = viewModel.loading

    val imagePainter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(rocket.image_url)
            .crossfade(durationMillis = 1000)
            .error(R.drawable.ic_placeholder)
            .placeholder(R.drawable.ic_placeholder)
            .build(),
        contentScale = ContentScale.Fit
    )

    val manPainter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(rocket.manufacturer?.image_url)
            .crossfade(durationMillis = 1000)
            .error(R.drawable.ic_placeholder)
            .placeholder(R.drawable.ic_placeholder)
            .build(),
        contentScale = ContentScale.Fit
    )

    val logoPainter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(rocket.manufacturer?.logo_url)
            .crossfade(durationMillis = 1000)
            .error(R.drawable.ic_placeholder)
            .placeholder(R.drawable.ic_placeholder)
            .build(),
        contentScale = ContentScale.Fit
    )

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 6.dp, vertical = 6.dp)
            .fillMaxWidth()
    ) {
        CircularProgressBar(isDisplayed = loading.value)
        if(!loading.value){
            // Main card
            ElevatedCard(
                colors = CardDefaults.cardColors(
                    containerColor =  MaterialTheme.colorScheme.secondaryContainer,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp)
            ) {
                Box(
                    Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    Column {
                        Image(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(CardDefaults.elevatedShape)
                                .background(MaterialTheme.colorScheme.primary),
                            painter = imagePainter,
                            contentDescription = rocket.name,
                            contentScale = ContentScale.Crop,
                        )
                        // Name
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(MaterialTheme.colorScheme.primary),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(horizontal = 12.dp),
                                text = rocket.name.toString(),
                                color = MaterialTheme.colorScheme.onPrimary,
                                fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                                fontWeight = FontWeight.Bold,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                        // Description
                        if(!rocket.description.isNullOrEmpty()){
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(MaterialTheme.colorScheme.secondaryContainer),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween,

                                ){
                                Text(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(6.dp),
                                    text = rocket.description.toString(),
                                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                                    fontWeight = FontWeight.Normal
                                )
                            }
                        }
                        Divider(modifier = Modifier
                            .padding(6.dp)
                            .background(MaterialTheme.colorScheme.secondaryContainer)
                        )
                        // Status, reusable
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.secondaryContainer),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ){
                            // Active
                            Column() {
                                Row(modifier = Modifier
                                    .background(MaterialTheme.colorScheme.secondaryContainer),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ){
                                    Text(
                                        modifier = Modifier.padding(6.dp),
                                        text = "Status",
                                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                                        fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        modifier = Modifier.padding(6.dp),
                                        text = if(rocket.active) { "Active" } else { "Discontinued" },
                                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                                        fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                                        fontWeight = FontWeight.Normal
                                    )
                                }
                            }
                            // Reusable
                            Column() {
                                Row(modifier = Modifier
                                    .background(MaterialTheme.colorScheme.secondaryContainer),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Start
                                ){
                                    Text(
                                        modifier = Modifier.padding(6.dp),
                                        text = "Reusable",
                                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                                        fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        modifier = Modifier.padding(6.dp),
                                        text = if(rocket.reusable) { "Yes" } else { "No" },
                                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                                        fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                                        fontWeight = FontWeight.Normal
                                    )
                                }
                            }
                        }
                        // Length and diameter
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.secondaryContainer),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ){
                            // Length
                            Column() {
                                Row(modifier = Modifier
                                    .background(MaterialTheme.colorScheme.secondaryContainer),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ){
                                    Text(
                                        modifier = Modifier.padding(6.dp),
                                        text = "Length",
                                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                                        fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        modifier = Modifier.padding(6.dp),
                                        text = rocket.length.toString() + " m",
                                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                                        fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                                        fontWeight = FontWeight.Normal
                                    )
                                }
                            }
                            // Diameter
                            Column() {
                                Row(modifier = Modifier
                                    .background(MaterialTheme.colorScheme.secondaryContainer),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Start
                                ){
                                    Text(
                                        modifier = Modifier.padding(6.dp),
                                        text = "Diameter",
                                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                                        fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        modifier = Modifier.padding(6.dp),
                                        text = rocket.diameter.toString() + " m",
                                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                                        fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                                        fontWeight = FontWeight.Normal
                                    )
                                }
                            }
                        }
                        // LEO, GTO payload
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.secondaryContainer),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ){
                            // LEO capacity
                            Column() {
                                Row(modifier = Modifier
                                    .background(MaterialTheme.colorScheme.secondaryContainer),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ){
                                    Text(
                                        modifier = Modifier.padding(6.dp),
                                        text = "LEO capacity",
                                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                                        fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        modifier = Modifier.padding(6.dp),
                                        text = if(rocket.leo_capacity == null) {"Unknown"}
                                        else{rocket.leo_capacity.toString() + " kg"},
                                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                                        fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                                        fontWeight = FontWeight.Normal
                                    )
                                }
                            }
                            // GTO capacity
                            Column() {
                                Row(modifier = Modifier
                                    .background(MaterialTheme.colorScheme.secondaryContainer),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Start
                                ){
                                    Text(
                                        modifier = Modifier.padding(6.dp),
                                        text = "GTO capacity",
                                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                                        fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        modifier = Modifier.padding(6.dp),
                                        text = if(rocket.gto_capacity == null) {"Unknown"}
                                        else{rocket.gto_capacity.toString() + " kg"},
                                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                                        fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                                        fontWeight = FontWeight.Normal
                                    )
                                }
                            }
                        }
                        // Maiden flight
                        Row(modifier = Modifier
                            .background(MaterialTheme.colorScheme.secondaryContainer),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ){
                            Text(
                                modifier = Modifier.padding(6.dp),
                                text = "Maiden flight",
                                color = MaterialTheme.colorScheme.onSecondaryContainer,
                                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                modifier = Modifier.padding(6.dp),
                                text = formatDate(rocket.maiden_flight, true),
                                color = MaterialTheme.colorScheme.onSecondaryContainer,
                                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                                fontWeight = FontWeight.Normal
                            )
                        }
                    }
                }
            }
            // Manufacturer card
            ElevatedCard(
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
                        if(!rocket.manufacturer?.image_url.isNullOrEmpty()){
                            Image(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(CardDefaults.elevatedShape),
                                painter = manPainter,
                                contentDescription = rocket.manufacturer?.name,
                                contentScale = ContentScale.Crop,
                            )
                        }
                        // Name
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 3.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                modifier = Modifier.weight(1f),
                                text = rocket.manufacturer?.name.toString(),
                                color = MaterialTheme.colorScheme.onSecondary,
                                fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                                fontWeight = FontWeight.Bold,
                                maxLines = 3,
                                overflow = TextOverflow.Ellipsis
                            )
                            if(!rocket.manufacturer?.logo_url.isNullOrEmpty()){
                                Image(
                                    modifier = Modifier
                                        .height(64.dp)
                                        .clip(CardDefaults.elevatedShape),
                                    painter = logoPainter,
                                    contentDescription = rocket.manufacturer?.name,
                                    contentScale = ContentScale.Fit,
                                )
                            }
                        }
                        // Country code, Agency type
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(MaterialTheme.colorScheme.primary)
                                .padding(horizontal = 6.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ){
                            ElevatedSuggestionChip(
                                colors = SuggestionChipDefaults.elevatedSuggestionChipColors(
                                    containerColor =  MaterialTheme.colorScheme.secondaryContainer),
                                onClick = {},
                                label = {
                                    androidx.compose.material.Text(
                                        text = "${rocket.manufacturer?.country_code}",
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
                                    Text(
                                        text = "${rocket.manufacturer?.type}",
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
    }
}