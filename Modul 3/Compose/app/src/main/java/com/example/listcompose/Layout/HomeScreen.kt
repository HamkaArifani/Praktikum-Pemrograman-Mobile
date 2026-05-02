package com.example.listcompose.Layout

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import com.example.listcompose.R
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.listcompose.Model.Film
import com.example.listcompose.ViewModel.FilmViewModel


@Composable
fun HomeScreen(navController: NavController, viewModel: FilmViewModel) {
    val films by viewModel.films
    Scaffold(
        topBar = {
            HeaderScreen(
                title = stringResource(R.string.app_title),
                buttonText = stringResource(R.string.languagebutton),
                icon = R.drawable.language,
                onButtonClick = { navController.navigate("language") }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            item {
                LazyRow(
                    contentPadding = PaddingValues(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(films) { film ->
                        HighlightCard(film)
                    }
                }
            }

            item {
                Text(
                    text = stringResource(R.string.app_about),
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
                )
            }

            items(films) { film ->
                val context = LocalContext.current
                MovieItem(
                    film = film,
                    onDetailClick = { navController.navigate("detail/${film.id}") },
                    onIMDBClick = {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(context.getString(film.imdbUrl)))
                        context.startActivity(intent)
                    }
                )
            }
        }
    }
}

@Composable
fun HighlightCard(film : Film) {
    Card(
        modifier = Modifier
            .width(280.dp)
            .height(160.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Image(
            painter = painterResource(id = film.imageId),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun MovieItem(
    film: Film,
    onDetailClick: () -> Unit,
    onIMDBClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        colors = CardDefaults.cardColors(colorResource(R.color.card)),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = film.imageId),
                contentDescription = null,
                modifier = Modifier
                    .size(width = 110.dp, height = 160.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .padding(start = 12.dp)
                    .weight(1f)
            ) {
                Text(
                    text = stringResource(film.title),
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = stringResource(film.synopsis),
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(vertical = 4.dp)
                )

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = stringResource(R.string.rating),
                        style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = stringResource(film.score),
                        style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
                        color = Color.DarkGray
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Button(
                        onClick = onIMDBClick,
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(colorResource(R.color.button)),
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Text(stringResource(R.string.imdb), fontSize = 11.sp, color = Color.Black)
                    }
                    Button(
                        onClick = onDetailClick,
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(colorResource(R.color.button)),
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Text(stringResource(R.string.detail), fontSize = 11.sp, color = Color.Black)
                    }
                }
            }
        }
    }
}
