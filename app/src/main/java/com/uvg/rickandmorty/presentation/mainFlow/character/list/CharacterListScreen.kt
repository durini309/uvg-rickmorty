package com.uvg.rickandmorty.presentation.mainFlow.character.list

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.uvg.rickandmorty.data.model.Character
import com.uvg.rickandmorty.data.source.CharacterDb
import com.uvg.rickandmorty.presentation.ui.theme.RickAndMortyTheme
import kotlin.random.Random

@Composable
fun CharacterListRoute(
    onCharacterClick: (Int) -> Unit,
) {
    val characterDb = CharacterDb()
    val characters = characterDb.getAllCharacters()
    val randomNum by rememberSaveable {
        mutableIntStateOf(Random.nextInt())
    }
    // Use rememberSaveable to persist the state across recompositions and process death
    CharacterListScreen(
        num = randomNum,
        characters = characters,
        onCharacterClick = onCharacterClick,
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
private fun CharacterListScreen(
    num: Int,
    characters: List<Character>,
    onCharacterClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        item {
            Text(
                text = num.toString(),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
        items(characters) { item ->
            CharacterItem(
                character = item,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onCharacterClick(item.id) }
            )
        }
    }
}

@Composable
private fun CharacterItem(
    character: Character,
    modifier: Modifier = Modifier
) {
    val imageBackgroundColors = listOf(
        MaterialTheme.colorScheme.error,
        MaterialTheme.colorScheme.primary,
        MaterialTheme.colorScheme.secondary,
        MaterialTheme.colorScheme.tertiary,
        MaterialTheme.colorScheme.primaryContainer,
        MaterialTheme.colorScheme.secondaryContainer,
        MaterialTheme.colorScheme.tertiaryContainer,
        MaterialTheme.colorScheme.inverseSurface
    )
    Row(
        modifier = modifier.padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Surface(
            modifier = Modifier.size(48.dp),
            color = imageBackgroundColors[(character.id % (imageBackgroundColors.count() - 1))],
            shape = CircleShape
        ) {
            Box {
                Icon(
                    Icons.Outlined.Person, contentDescription = "Image",
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
        Column {
            Text(text = character.name)
            Text(
                text = "${character.species} * ${character.status}",
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PreviewCharacterListScreen() {
    RickAndMortyTheme() {
        Surface {
            val db = CharacterDb()
            CharacterListScreen(
                num = 4,
                characters = db.getAllCharacters().take(6),
                onCharacterClick = {},
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}