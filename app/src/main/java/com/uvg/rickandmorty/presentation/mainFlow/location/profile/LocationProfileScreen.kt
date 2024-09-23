package com.uvg.rickandmorty.presentation.mainFlow.location.profile

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.uvg.rickandmorty.data.model.Location
import com.uvg.rickandmorty.data.source.LocationDb
import com.uvg.rickandmorty.presentation.ui.theme.RickAndMortyTheme

@Composable
fun LocationProfileRoute(
    locationId: Int,
    onNavigateBack: () -> Unit
) {
    val locationDb = LocationDb()
    val location = locationDb.getLocationById(locationId)
    LocationProfileScreen(location = location, onNavigateBack = onNavigateBack)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LocationProfileScreen(
    location: Location,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
    ) {
        TopAppBar(
            title = {
                Text("Location Details")
            },
            navigationIcon = {
                IconButton(onClick = onNavigateBack) {
                    Icon(Icons.Default.ArrowBack, contentDescription = null)
                }
            }
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 64.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = location.name,
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(16.dp))
            LocationProfilePropItem(
                title = "ID:",
                value = location.id.toString(),
                modifier = Modifier.fillMaxWidth()
            )
            LocationProfilePropItem(
                title = "Type:",
                value = location.type,
                modifier = Modifier.fillMaxWidth()
            )
            LocationProfilePropItem(
                title = "Dimensions:",
                value = location.dimension,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun LocationProfilePropItem(
    title: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = title)
        Text(text = value)
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PreviewLocationProfileScreen() {
    RickAndMortyTheme() {
        Surface {
            LocationProfileScreen(
                location = Location(1, "Earth (C-137)", "Planet", "Dimension C-137"),
                onNavigateBack = { },
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}