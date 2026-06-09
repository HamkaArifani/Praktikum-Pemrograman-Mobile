package com.example.listcompose.ui.screen.language

import android.widget.Toast
import androidx.core.os.LocaleListCompat


import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.listcompose.R
import com.example.listcompose.ui.screen.HeaderScreen

@Composable
fun LanguageScreen(navController: NavController) {
    val context = LocalContext.current

    val factory = LanguageViewModelFactory(
        context = context,
        pageInfo = stringResource(R.string.languagepage)
    )
    val viewModel : LanguageViewModel = viewModel(factory = factory)

    val selectedOption by viewModel.selectedOption.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        Toast.makeText(context, viewModel.pageInfo, Toast.LENGTH_LONG).show()
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp)
        ) {
            LanguageOption(
                label = stringResource(R.string.english),
                selected = (selectedOption == "English"),
                onClick = { viewModel.selectLanguage("English") }
            )

            LanguageOption(
                label = stringResource(R.string.indonesia),
                selected = (selectedOption == "Bahasa"),
                onClick = { viewModel.selectLanguage("Bahasa") }
            )
        }
    }
}

@Composable
fun LanguageOption(label: String, selected: Boolean, onClick: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        RadioButton(
            selected = selected,
            onClick = onClick
        )
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}