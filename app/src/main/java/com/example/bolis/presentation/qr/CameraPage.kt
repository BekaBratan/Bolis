package com.example.bolis.presentation.qr

import android.util.Log
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.example.bolis.R
import com.example.bolis.ui.Elements.CustomButton
import com.example.bolis.ui.theme.Glass50
import com.example.bolis.ui.theme.Green50
import com.example.bolis.ui.theme.Red40
import com.example.bolis.ui.theme.Transp50
import com.example.bolis.ui.theme.White50
import com.example.bolis.ui.theme.fontFamily

@OptIn(ExperimentalMaterial3Api::class)
@androidx.compose.ui.tooling.preview.Preview
@Composable
fun CameraPage() {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val cameraProviderFuture = remember {
        ProcessCameraProvider.getInstance(context)
    }

    var isSheetOpen by remember { mutableStateOf(false) }
    var qrCode by remember { mutableStateOf("") }
    var isQrValid by remember { mutableStateOf(false) }

    val imageAnalysis = remember { ImageAnalysis.Builder().build() }
    val analyzer = remember {
        BarcodeAnalyzer(context).apply {
            onQrScanned = { message ->
                qrCode = message
                isQrValid = message.startsWith("5449")
                isSheetOpen = true
                imageAnalysis.clearAnalyzer()
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = {
                val previewView = PreviewView(context)
                val preview = Preview.Builder().build()
                val selector = CameraSelector.DEFAULT_BACK_CAMERA

                preview.setSurfaceProvider(previewView.surfaceProvider)

                imageAnalysis.setAnalyzer(
                    ContextCompat.getMainExecutor(context),
                    analyzer
                )

                runCatching {
                    cameraProviderFuture.get().bindToLifecycle(
                        lifecycleOwner,
                        selector,
                        preview,
                        imageAnalysis
                    )
                }.onFailure {
                    Log.e("CAMERA", "Camera bind error ${it.localizedMessage}", it)
                }

                previewView
            }
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_qr_scanner),
                contentDescription = null,
                modifier = Modifier
                    .width(250.dp)
                    .height(250.dp)
            )

            Text(
                text = "Scan QR Code",
                fontSize = 24.sp,
                color = White50,
                textAlign = TextAlign.Center,
                fontFamily = fontFamily,
            )
        }

        if (isSheetOpen) {
            ModalBottomSheet(
                onDismissRequest = {
                    isSheetOpen = false
                    imageAnalysis.setAnalyzer(
                        ContextCompat.getMainExecutor(context),
                        analyzer
                    )
                }
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(48.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    Spacer(Modifier.size(32.dp))
                    if (isQrValid) {
                        Text(
                            text = qrCode,
                            fontSize = 20.sp,
                            color = Green50,
                            fontFamily = fontFamily,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                    } else {
                        Text(
                            text = "Invalid QR Code",
                            fontSize = 20.sp,
                            color = Red40,
                            fontFamily = fontFamily,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                    }
                    Spacer(Modifier.size(32.dp))

                    if (isQrValid) {
                        CustomButton(
                            onClick = {
                                // Handle send request
                            },
                            name = "Send Request"
                        )
//                    } else {
//                        Text(
//                            text = "Invalid QR Code",
//                            fontSize = 18.sp,
//                            fontWeight = FontWeight(600),
//                            textAlign = TextAlign.Center,
//                            fontFamily = fontFamily,
//                            color = Red40,
//                        )
                    }

                    CustomButton(
                        onClick = {
                            isSheetOpen = false
                            imageAnalysis.setAnalyzer(
                                ContextCompat.getMainExecutor(context),
                                analyzer
                            )
                        },
                        name = "Scan Again",
                        backgroundColor = Glass50,
                        strokeColor = Green50,
                        textColor = Green50
                    )
                    Spacer(Modifier.size(32.dp))
                }
            }
        }
    }
}