package com.example.hypergol.screens.launches.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.hypergol.R
import com.example.hypergol.screens.common.CircularProgressBar


@Composable
fun LaunchDetail()
{
    val viewModel = hiltViewModel<LaunchDetailViewModel>()
    val uiState = viewModel.uiState
    val loading = viewModel.loading

    val launchPainter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(uiState.detail?.image_url)
            .crossfade(durationMillis = 1000)
            .error(R.drawable.ic_placeholder)
            .placeholder(R.drawable.ic_placeholder)
            .build(),
        contentScale = ContentScale.Fit
    )
    var rocketPainter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(uiState.detail?.rocket?.configuration?.image_url)
            .crossfade(durationMillis = 1000)
            .error(R.drawable.ic_placeholder)
            .placeholder(R.drawable.ic_placeholder)
            .build(),
        contentScale = ContentScale.Fit
    )
    if(uiState.detail?.rocket?.configuration?.image_url.isNullOrEmpty()){
        rocketPainter = launchPainter
    }

    val padPainter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(uiState.detail?.pad?.map_image)
            .crossfade(durationMillis = 1000)
            .error(R.drawable.ic_placeholder)
            .placeholder(R.drawable.ic_placeholder)
            .build(),
        contentScale = ContentScale.Fit
    )

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 6.dp, vertical = 12.dp)
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
                    .padding(vertical = 6.dp)
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
                            painter = launchPainter,
                            contentDescription = uiState.detail?.name,
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
                                    .padding(horizontal = 6.dp),
                                text = uiState.detail?.name.toString(),
                                color = MaterialTheme.colorScheme.onPrimary,
                                fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                                fontWeight = FontWeight.Bold,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                        // Description
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
                                text = uiState.detail?.mission?.description.toString(),
                                color = MaterialTheme.colorScheme.onSecondaryContainer,
                                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                                fontWeight = FontWeight.Normal
                            )
                        }
                        Divider(modifier = Modifier
                            .padding(6.dp)
                            .background(MaterialTheme.colorScheme.secondaryContainer)
                        )
                        // Mission type
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(MaterialTheme.colorScheme.secondaryContainer),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,

                            ){
                            Text(
                                modifier = Modifier
                                    .padding(6.dp),
                                text = "Mission type",
                                color = MaterialTheme.colorScheme.onSecondaryContainer,
                                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                modifier = Modifier
                                    .padding(6.dp),
                                text = uiState.detail?.mission?.type.toString(),
                                color = MaterialTheme.colorScheme.onSecondaryContainer,
                                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                                fontWeight = FontWeight.Normal
                            )
                        }
                        // Orbit
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(MaterialTheme.colorScheme.secondaryContainer),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,

                            ){
                            Text(
                                modifier = Modifier
                                    .padding(6.dp),
                                text = "Orbit",
                                color = MaterialTheme.colorScheme.onSecondaryContainer,
                                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                modifier = Modifier
                                    .padding(6.dp),
                                text = uiState.detail?.mission?.orbit?.name.toString(),
                                color = MaterialTheme.colorScheme.onSecondaryContainer,
                                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                                fontWeight = FontWeight.Normal
                            )
                        }
                        // Status
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(MaterialTheme.colorScheme.secondaryContainer),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,

                            ){
                            Text(
                                modifier = Modifier
                                    .padding(6.dp),
                                text = "Status",
                                color = MaterialTheme.colorScheme.onSecondaryContainer,
                                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                modifier = Modifier
                                    .padding(6.dp),
                                text = uiState.detail?.status?.name.toString(),
                                color = MaterialTheme.colorScheme.onSecondaryContainer,
                                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                                fontWeight = FontWeight.Normal
                            )
                        }
                        // Launch date
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(MaterialTheme.colorScheme.secondaryContainer),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,

                            ){
                            Text(
                                modifier = Modifier
                                    .padding(6.dp),
                                text = "Launch date",
                                color = MaterialTheme.colorScheme.onSecondaryContainer,
                                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                modifier = Modifier
                                    .padding(6.dp),
                                text = uiState.formattedNet,
                                color = MaterialTheme.colorScheme.onSecondaryContainer,
                                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                                fontWeight = FontWeight.Normal
                            )
                        }
                    }
                }
            }
            // Rocket card
            ElevatedCard(
                colors = CardDefaults.cardColors(
                    containerColor =  MaterialTheme.colorScheme.secondaryContainer,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        vertical = 6.dp
                    )
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
                            painter = rocketPainter,
                            contentDescription = uiState.detail?.rocket?.configuration?.name,
                            contentScale = ContentScale.Crop,
                        )
                        Column(modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.primary))
                        {
                            // Rocket
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
                                        .padding(horizontal = 6.dp),
                                    text = "Rocket",
                                    color = MaterialTheme.colorScheme.onPrimary,
                                    fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                                    fontWeight = FontWeight.Bold,
                                )
                            }
                            // Name of the rocket
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
                                        .padding(horizontal = 6.dp),
                                    text = uiState.detail?.rocket?.configuration?.name.toString(),
                                    color = MaterialTheme.colorScheme.onPrimary,
                                    fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                                    fontWeight = FontWeight.Bold,
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }

                        // Description
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
                                text = uiState.detail?.rocket?.configuration?.description.toString(),
                                color = MaterialTheme.colorScheme.onSecondaryContainer,
                                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                                fontWeight = FontWeight.Normal
                            )
                        }
                        Divider(modifier = Modifier
                            .padding(6.dp)
                            .background(MaterialTheme.colorScheme.secondaryContainer)
                        )
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
                                        text = if(uiState.detail?.rocket?.configuration?.length == null) {"Unknown"}
                                        else {uiState.detail.rocket.configuration.length.toString() + " m"},
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
                                        text = if(uiState.detail?.rocket?.configuration?.diameter == null) {"Unknown"}
                                        else {uiState.detail.rocket.configuration.diameter.toString() + " m"},
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
                                        text = if(uiState.detail?.rocket?.configuration?.leo_capacity == null) {"Unknown"}
                                        else{uiState.detail.rocket.configuration.leo_capacity.toString() + " kg"},
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
                                        text = if(uiState.detail?.rocket?.configuration?.gto_capacity == null) {"Unknown"}
                                        else{uiState.detail.rocket.configuration.gto_capacity.toString() + " kg"},
                                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                                        fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                                        fontWeight = FontWeight.Normal
                                    )
                                }
                            }
                        }

                        // Manufacturer
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(MaterialTheme.colorScheme.secondaryContainer),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,

                            ){
                            Text(
                                modifier = Modifier.padding(6.dp),
                                text = "Manufacturer",
                                color = MaterialTheme.colorScheme.onSecondaryContainer,
                                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                modifier = Modifier.padding(6.dp),
                                text = uiState.detail?.rocket?.configuration?.manufacturer?.name.toString(),
                                color = MaterialTheme.colorScheme.onSecondaryContainer,
                                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                                fontWeight = FontWeight.Normal,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                        // Launch count
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(MaterialTheme.colorScheme.secondaryContainer),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,

                            ){
                            Text(
                                modifier = Modifier.padding(6.dp),
                                text = "Total launches",
                                color = MaterialTheme.colorScheme.onSecondaryContainer,
                                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                modifier = Modifier.padding(6.dp),
                                text = uiState.detail?.rocket?.configuration?.total_launch_count.toString(),
                                color = MaterialTheme.colorScheme.onSecondaryContainer,
                                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                                fontWeight = FontWeight.Normal,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                }
            }
            // Pad card
            ElevatedCard(
                colors = CardDefaults.cardColors(
                    containerColor =  MaterialTheme.colorScheme.secondaryContainer,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
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
                            painter = padPainter,
                            contentDescription = uiState.detail?.pad?.name,
                            contentScale = ContentScale.Crop,
                        )
                        Column(modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.primary))
                        {
                            // Pad
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
                                        .padding(horizontal = 6.dp),
                                    text = "Launch pad",
                                    color = MaterialTheme.colorScheme.onPrimary,
                                    fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                                    fontWeight = FontWeight.Bold,
                                )
                            }
                            // Launch pad name
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
                                        .padding(horizontal = 6.dp),
                                    text = uiState.detail?.pad?.name.toString(),
                                    color = MaterialTheme.colorScheme.onPrimary,
                                    fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                                    fontWeight = FontWeight.Bold,
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }
                        // Location
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(MaterialTheme.colorScheme.secondaryContainer),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,

                            ){
                            Text(
                                modifier = Modifier.padding(6.dp),
                                text = "Location",
                                color = MaterialTheme.colorScheme.onSecondaryContainer,
                                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                modifier = Modifier.padding(6.dp),
                                text = uiState.detail?.pad?.location?.name.toString(),
                                color = MaterialTheme.colorScheme.onSecondaryContainer,
                                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                                fontWeight = FontWeight.Normal,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                        // Country
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(MaterialTheme.colorScheme.secondaryContainer),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,

                            ){
                            Text(
                                modifier = Modifier.padding(6.dp),
                                text = "Country",
                                color = MaterialTheme.colorScheme.onSecondaryContainer,
                                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                modifier = Modifier.padding(6.dp),
                                text = uiState.detail?.pad?.location?.country_code.toString(),
                                color = MaterialTheme.colorScheme.onSecondaryContainer,
                                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                                fontWeight = FontWeight.Normal,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                        if(uiState.detail?.pad?.name.toString() != "Unknown Pad"){
                            // Total launches
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(MaterialTheme.colorScheme.secondaryContainer),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween,

                                ){
                                Text(
                                    modifier = Modifier.padding(6.dp),
                                    text = "Total launches",
                                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    modifier = Modifier.padding(6.dp),
                                    text = uiState.detail?.pad?.total_launch_count.toString(),
                                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                                    fontWeight = FontWeight.Normal,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}