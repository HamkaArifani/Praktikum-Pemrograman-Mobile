package com.example.listcompose

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.compose.rememberNavController
import com.example.listcompose.Navigation.Nav_Graph
import com.example.listcompose.ui.theme.ListComposeTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ListComposeTheme {
                val navController = rememberNavController()
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    Nav_Graph(
                        navController = navController,
                        startDestination = "home"
                    )
                }
                }
            }
        }
}

