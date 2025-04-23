package com.example.bolis.presentation.qr

import android.graphics.ImageFormat
import android.util.Log
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.zxing.BarcodeFormat
import com.google.zxing.BinaryBitmap
import com.google.zxing.DecodeHintType
import com.google.zxing.MultiFormatReader
import com.google.zxing.PlanarYUVLuminanceSource
import com.google.zxing.common.HybridBinarizer
import java.nio.ByteBuffer

class QRAnalyzer(
    private val onQRCodeScanned: (String) -> Unit
): ImageAnalysis.Analyzer {

    private val supportedImageFormats = listOf(
        ImageFormat.YUV_420_888,
        ImageFormat.YUV_422_888,
        ImageFormat.YUV_444_888
    )

    override fun analyze(image: ImageProxy) {
        Log.d("QRAnalyzer", "Image format: ${image.format}")
        if (image.format in supportedImageFormats) {
            val yBuffer = image.planes[0].buffer
            val ySize = yBuffer.remaining()
            val yBytes = ByteArray(ySize)
            yBuffer.get(yBytes)

            val source = PlanarYUVLuminanceSource(
                yBytes,
                image.width,
                image.height,
                0,
                0,
                image.width,
                image.height,
                true
            )

            val binaryBitMap = BinaryBitmap(HybridBinarizer(source))
            try {
                val reader = MultiFormatReader().apply {
                    setHints(
                        mapOf(
                            DecodeHintType.POSSIBLE_FORMATS to listOf(BarcodeFormat.QR_CODE),
                            DecodeHintType.TRY_HARDER to true
                        )
                    )
                }

                val result = reader.decodeWithState(binaryBitMap)
                onQRCodeScanned(result.text)
            } catch (e: Exception) {
                Log.d("QRAnalyzer", "No QR found: ${e.message}")
            } finally {
                image.close()
            }
        }
    }

    private fun ByteBuffer.toByteArray(): ByteArray {
        rewind()
        return ByteArray(remaining()).also {
            get(it)
        }
    }
}