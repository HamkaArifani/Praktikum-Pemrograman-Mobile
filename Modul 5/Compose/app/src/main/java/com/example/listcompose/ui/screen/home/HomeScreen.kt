package com.example.listcompose.ui.screen.home

import android.content.Intent
import android.net.Uri
import android.widget.Toast
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
import androidx.compose.runtime.LaunchedEffect
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.listcompose.domain.model.Film
import com.example.listcompose.ui.screen.HeaderScreen
import timber.log.Timber

private const val TMDB_IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"
@Composable
fun HomeScreen(
    navController: NavController,
) {
    val context=LocalContext.current
    val factory= HomeViewModelFactory(
        context = context,
        pageInfo = stringResource(R.string.homepage)
    )
    val viewModel: HomeViewModel = viewModel(factory = factory)

    val films by viewModel.films.collectAsStateWithLifecycle()

    LaunchedEffect(films) {
        if (films.isNotEmpty()){
            Timber.d("Item Film yang Dikirimkan pada List: ${films.size}")
        }
    }

    LaunchedEffect(Unit) {
        val homepageText = context.getString(R.string.homepage)
        Toast.makeText(context, homepageText, Toast.LENGTH_SHORT).show()
    }
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

                val synopsisRes = when (film.id) {
                    274 -> R.string.synopsis_f01
                    812583 -> R.string.synopsis_f05
                    687163 -> R.string.synopsis_f03
                    1393326 -> R.string.synopsis_f02
                    1310741 -> R.string.synopsis_f04
                    else -> R.string.errormsg
                }

                val scoreRes = when (film.id) {
                    274 -> R.string.score_f01
                    812583 -> R.string.score_f05
                    687163 -> R.string.score_f03
                    1393326 -> R.string.score_f02
                    1310741 -> R.string.score_f04
                    else -> R.string.app_name
                }

                val imdbRes = when (film.id) {
                    274 -> R.string.imdb_f01
                    812583 -> R.string.imdb_f05
                    687163 -> R.string.imdb_f03
                    1393326 -> R.string.imdb_f02
                    1310741 -> R.string.imdb_f04
                    else -> R.string.app_name
                }

                MovieItem(
                    film = film,
                    synopsisRes = synopsisRes,
                    scoreRes = scoreRes,
                    onDetailClick = {
                        Timber.d("Tombol Detail dari Film : ${film.title} Ditekan")
                        navController.navigate("detail/${film.id}")
                    },
                    onIMDBClick = {
                        Timber.d("Tombol IMDB dari Film : ${film.title} Ditekan")
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(context.getString(imdbRes)))
                        context.startActivity(intent)
                    }
                )
            }
        }
    }
}

@Composable
fun HighlightCard(film : Film) {
    val bigImageRes = when(film.id){
        274-> R.drawable.sotl
        812583 -> R.drawable.wudm
        687163 -> R.drawable.phm
        1393326 -> R.drawable.gitc
        1310741 -> R.drawable.sktp
        else -> R.drawable.ic_launcher_background
    }
    Card(
        modifier = Modifier
            .width(280.dp)
            .height(160.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Image(
            painter = painterResource(id = bigImageRes),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun MovieItem(
    film: Film,
    synopsisRes: Int,
    scoreRes: Int,
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
            AsyncImage(
                model = "$TMDB_IMAGE_BASE_URL${film.posterPath}",
                contentDescription = "Poster ${film.title}",
                placeholder = painterResource(R.drawable.ic_launcher_background),
                error = painterResource(R.drawable.ic_launcher_background),
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
                    text = film.title,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = "Release Date: ${film.releaseDate}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.outline
                )

                Text(
                    text = stringResource(synopsisRes),
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
                        text = stringResource(scoreRes),
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
