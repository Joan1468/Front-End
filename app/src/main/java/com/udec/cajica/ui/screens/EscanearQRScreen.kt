package com.example.appinventario.ui.screens

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.google.zxing.integration.android.IntentIntegrator

@Composable
fun EscanearQRScreen(navController: NavController) {
    val context = LocalContext.current
    var resultadoQR by remember { mutableStateOf<String?>(null) }
    var tienePermisoCamara by remember { mutableStateOf(false) }

    // ✅ Verificación y solicitud de permisos de cámara
    val launcherPermiso = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { concedido -> tienePermisoCamara = concedido }
    )

    LaunchedEffect(Unit) {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
            == PackageManager.PERMISSION_GRANTED) {
            tienePermisoCamara = true
        } else {
            launcherPermiso.launch(Manifest.permission.CAMERA)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Escanear Código QR") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (tienePermisoCamara) {
                Button(onClick = {
                    // ✅ Lanzamos el escáner QR usando ZXing
                    IntentIntegrator(context as androidx.activity.ComponentActivity).apply {
                        setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
                        setPrompt("Escanea el código QR del equipo")
                        setCameraId(0)
                        setBeepEnabled(true)
                        initiateScan()
                    }
                }) {
                    Text("Escanear QR")
                }

                resultadoQR?.let {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Resultado: $it", textAlign = TextAlign.Center)
                    Button(onClick = {
                        // ✅ Navegamos al detalle del equipo usando el ID leído del QR
                        navController.navigate("detalle_equipo/$it")
                    }) {
                        Text("Ver Detalle")
                    }
                }
            } else {
                Text("Necesitas permiso de cámara para escanear códigos QR.")
            }
        }
    }
}
