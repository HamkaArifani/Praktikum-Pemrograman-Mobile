package com.example.listcompose.Layout

import androidx.core.os.LocaleListCompat


import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.listcompose.R

@Composable
fun LanguageScreen(navController: NavController) {
    val currentLocale = AppCompatDelegate.getApplicationLocales().toLanguageTags()

    var selectedOption by remember {
        mutableStateOf(if (currentLocale.contains("in")) "Bahasa" else "English")
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
                onClick = {
                    selectedOption = "English"
                    val appLocale: LocaleListCompat = LocaleListCompat.forLanguageTags("en")
                    AppCompatDelegate.setApplicationLocales(appLocale)
                }
            )

            LanguageOption(
                label = stringResource(R.string.indonesia),
                selected = (selectedOption == "Bahasa"),
                onClick = {
                    selectedOption = "Bahasa"
                    val appLocale: LocaleListCompat = LocaleListCompat.forLanguageTags("in")
                    AppCompatDelegate.setApplicationLocales(appLocale)
                }
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