package com.example.listcompose.ui.screen.detail

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.listcompose.R
import androidx.navigation.NavController
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.listcompose.ui.screen.HeaderScreen
import timber.log.Timber

@Composable
fun DetailScreen(
    filmId: Int,
    navController: NavController
) {
    val context = LocalContext.current

    val factory = DetailViewModelFactory(
        context = context,
        filmId = filmId,
        pageInfo = stringResource(R.string.detailpage)
    )

    val viewModel: DetailViewModel = viewModel(factory = factory)
    val film by viewModel.film.collectAsStateWithLifecycle()

    LaunchedEffect(film) {
        film?.let {
            Timber.d("Menampilkan Data Detail dari Film: ${it.title}")
        }
    }

    LaunchedEffect(Unit) {
        Toast.makeText(context, viewModel.pageInfo, Toast.LENGTH_SHORT).show()
    }

    Scaffold(
        topBar = {
            HeaderScreen(
                title = stringResource(R.string.app_title),
                buttonText = stringResource(R.string.homebutton),
                icon = R.drawable.home,
                onButtonClick = { navController.popBackStack() }
            )
        }
    ) { innerPadding ->
        val currentFilm = film

        if(currentFilm != null){
            val synopsisRes = when (currentFilm.id) {
                274 -> R.string.synopsis_f01
                812583 -> R.string.synopsis_f05
                687163 -> R.string.synopsis_f03
                1393326 -> R.string.synopsis_f02
                1310741 -> R.string.synopsis_f04
                else -> R.string.errormsg
            }

            val scoreRes = when (currentFilm.id) {
                274 -> R.string.score_f01
                812583 -> R.string.score_f05
                687163 -> R.string.score_f03
                1393326 -> R.string.score_f02
                1310741 -> R.string.score_f04
                else -> R.string.app_name
            }

            val reviewRes = when (currentFilm.id) {
                274 -> R.string.review_f01
                812583 -> R.string.review_f05
                687163 -> R.string.review_f03
                1393326 -> R.string.review_f02
                1310741 -> R.string.review_f04
                else -> R.string.errormsg
            }

            val bigImageRes = when (currentFilm.id) {
                274 -> R.drawable.sotl2
                812583 -> R.drawable.wudm2
                687163 -> R.drawable.phm2
                1393326 -> R.drawable.gitc2
                1310741 -> R.drawable.sktp2
                else -> R.drawable.ic_launcher_background
            }

            val imageRes = when (currentFilm.id) {
                274 -> R.drawable.sotl
                812583 -> R.drawable.wudm
                687163 -> R.drawable.phm
                1393326 -> R.drawable.gitc
                1310741 -> R.drawable.sktp
                else -> R.drawable.ic_launcher_background
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState())
            ) {
                Image(
                    painter = painterResource(id = bigImageRes),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp),
                    contentScale = ContentScale.Crop
                )

                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.Bottom
                    ) {
                        Column(modifier = Modifier.weight(1f)) {

                            Text(
                                text = currentFilm.title,
                                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
                            )

                            Spacer(modifier = Modifier.height(4.dp))

                            Text(
                                text = "Release Date: ${currentFilm.releaseDate}",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.outline
                            )

                            Spacer(modifier = Modifier.height(12.dp))

                            Text(
                                text = stringResource(id = synopsisRes),
                                style = MaterialTheme.typography.bodyMedium,
                                lineHeight = 20.sp
                            )
                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        Image(
                            painter = painterResource(id = imageRes),
                            contentDescription = null,
                            modifier = Modifier
                                .size(width = 120.dp, height = 180.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .fillMaxHeight(),
                            contentScale = ContentScale.Crop
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "${stringResource(id = R.string.rating)} ${stringResource(id = scoreRes)}",
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(colorResource(R.color.card)),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = stringResource(id = R.string.review),
                                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = stringResource(id = reviewRes),
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
            }
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }
}