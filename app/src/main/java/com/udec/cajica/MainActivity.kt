package com.udec.cajica


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.udec.cajica.ui.theme.CajicaTheme
import com.udec.cajica.viewModel.EquipoViewModel
import dagger.hilt.android.AndroidEntryPoint
import com.udec.cajica.navigation.AppNavigator


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            CajicaTheme {
                val navController = rememberNavController()
                val equipoViewModel: EquipoViewModel = hiltViewModel()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigator(navController = navController, viewModel = equipoViewModel)
                }
            }
        }
    }
}
