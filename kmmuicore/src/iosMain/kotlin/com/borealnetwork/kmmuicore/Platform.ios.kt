package com.borealnetwork.kmmuicore

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import com.kashif.cameraK.utils.toByteArray
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.readValue
import kotlinx.cinterop.useContents
import kotlinx.cinterop.usePinned
import kotlinx.coroutines.suspendCancellableCoroutine
import org.koin.core.definition.Definition
import org.koin.core.definition.KoinDefinition
import org.koin.core.module.Module
import org.koin.core.qualifier.Qualifier
import platform.CoreGraphics.CGRectMake
import platform.CoreGraphics.CGSizeMake
import platform.CoreLocation.CLGeocoder
import platform.CoreLocation.CLLocation
import platform.CoreLocation.CLPlacemark
import platform.Photos.PHAsset
import platform.Photos.PHImageContentModeAspectFit
import platform.Photos.PHImageManager
import platform.Photos.PHImageManagerMaximumSize
import platform.Photos.PHImageRequestOptions
import platform.Photos.PHImageRequestOptionsDeliveryModeHighQualityFormat
import platform.UIKit.UIGraphicsBeginImageContextWithOptions
import platform.UIKit.UIGraphicsEndImageContext
import platform.UIKit.UIGraphicsGetImageFromCurrentImageContext
import platform.UIKit.UIImage
import platform.UIKit.UIImageJPEGRepresentation
import platform.posix.exit
import platform.posix.memcpy
import qrgenerator.toNSData
import kotlin.coroutines.resume


actual inline fun <reified T : ViewModel> Module.viewModelDefinition(
    qualifier: Qualifier?,
    noinline definition: Definition<T>,
): KoinDefinition<T> = factory(qualifier = qualifier, definition = definition)


@OptIn(ExperimentalForeignApi::class)
actual fun getFixedImageBytes(path: String, maxWidth: Int): ByteArray {
    val image = if (path.startsWith("ph://")) {
        fetchImageFromPhotoKit(path)
    } else {
        UIImage.imageWithContentsOfFile(path)
    } ?: return byteArrayOf()

    // 1. Calcular el nuevo tamaño manteniendo la proporción (Aspect Ratio)
    val originalSize = image.size
    val width = originalSize.useContents { width }
    val height = originalSize.useContents { height }

    val targetWidth: Double
    val targetHeight: Double

    if (width > maxWidth) {
        val ratio = width / height
        targetWidth = maxWidth.toDouble()
        targetHeight = targetWidth / ratio
    } else {
        targetWidth = width
        targetHeight = height
    }

    // 2. Crear un contexto gráfico para redimensionar y normalizar la rotación
    // Esto "aplana" la imagen para que los píxeles coincidan con la orientación visual
    UIGraphicsBeginImageContextWithOptions(
        size = CGSizeMake(targetWidth, targetHeight),
        opaque = false,
        scale = 1.0 // Usamos 1.0 para controlar los píxeles exactos
    )

    image.drawInRect(CGRectMake(0.0, 0.0, targetWidth, targetHeight))
    val normalizedImage = UIGraphicsGetImageFromCurrentImageContext()
    UIGraphicsEndImageContext()

    if (normalizedImage == null) return byteArrayOf()

    // 3. Comprimir a JPEG (calidad 0.85 para ahorrar espacio sin perder mucha calidad)
    val data = UIImageJPEGRepresentation(normalizedImage, 0.85) ?: return byteArrayOf()

    // 4. Convertir NSData a Kotlin ByteArray
    return ByteArray(data.length.toInt()).apply {
        usePinned { pinned ->
            memcpy(pinned.addressOf(0), data.bytes, data.length)
        }
    }
}

@OptIn(ExperimentalForeignApi::class)
private fun fetchImageFromPhotoKit(phPath: String): UIImage? {
    val localId = phPath.replace("ph://", "")
    val fetchResult = PHAsset.fetchAssetsWithLocalIdentifiers(listOf(localId), null)
    val asset = fetchResult.firstObject() as? PHAsset ?: return null

    var resultImage: UIImage? = null
    val options = PHImageRequestOptions().apply {
        synchronous = true // Necesario para que la función retorne los bytes al instante
        networkAccessAllowed = true
        deliveryMode = PHImageRequestOptionsDeliveryModeHighQualityFormat
    }

    PHImageManager.defaultManager().requestImageForAsset(
        asset = asset,
        targetSize = PHImageManagerMaximumSize.readValue(), // Obtenemos la original, luego redimensionamos arriba
        contentMode = PHImageContentModeAspectFit,
        options = options
    ) { image, _ ->
        resultImage = image
    }

    return resultImage
}



actual fun compressImage(imageData: ByteArray, quality: Double): ByteArray {
    val data = imageData.toNSData()
    val image = UIImage.imageWithData(data)
    val compressedData = UIImageJPEGRepresentation(image!!, 0.7) // 0.7 es la calidad
    return compressedData!!.toByteArray()
}

actual fun closeApplication() {
    // Fuerza la salida del programa (Cuidado: Apple lo ve como un crash)
    exit(0)
}

@Composable
actual fun BackPressHandler(
    isEnabled: Boolean,
    onBackPressed: () -> Unit
) {
    // Intencionalmente vacío.
    // En iOS, el retroceso se maneja visualmente con el botón "< Atrás" en la TopAppBar
}