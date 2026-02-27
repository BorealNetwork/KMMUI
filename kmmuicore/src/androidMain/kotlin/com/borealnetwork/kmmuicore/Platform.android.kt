package com.borealnetwork.kmmuicore

//import org.koin.androidx.viewmodel.dsl.viewModel
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.location.Address
import android.location.Geocoder
import android.media.ExifInterface
import android.os.Build
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.suspendCancellableCoroutine
import org.koin.core.definition.Definition
import org.koin.core.definition.KoinDefinition
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.Qualifier
import java.io.ByteArrayOutputStream
import java.util.Locale
import kotlin.coroutines.resume
import kotlin.system.exitProcess


actual inline fun <reified T : ViewModel> Module.viewModelDefinition(
    qualifier: Qualifier?,
    noinline definition: Definition<T>,
): KoinDefinition<T> = viewModel(qualifier = qualifier, definition = definition)


actual fun getFixedImageBytes(path: String, maxWidth: Int): ByteArray {
    val options = BitmapFactory.Options().apply {
        inJustDecodeBounds = true
    }
    BitmapFactory.decodeFile(path, options)

    // 2. Calcular el factor de reducción (inSampleSize)
    options.inSampleSize = calculateInSampleSize(options, maxWidth)
    options.inJustDecodeBounds = false // Ahora sí queremos cargar los píxeles

    // 3. Cargar el bitmap ya pequeño
    val decodedBitmap = BitmapFactory.decodeFile(path, options) ?: return byteArrayOf()

    // 4. Corregir rotación (igual que antes)
    val exif = ExifInterface(path)
    val orientation =
        exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL)

    val matrix = Matrix()
    when (orientation) {
        ExifInterface.ORIENTATION_ROTATE_90 -> matrix.postRotate(90f)
        ExifInterface.ORIENTATION_ROTATE_180 -> matrix.postRotate(180f)
        ExifInterface.ORIENTATION_ROTATE_270 -> matrix.postRotate(270f)
    }

    val finalBitmap = Bitmap.createBitmap(
        decodedBitmap,
        0,
        0,
        decodedBitmap.width,
        decodedBitmap.height,
        matrix,
        true
    )

    // 5. Comprimir a ByteArray
    val stream = ByteArrayOutputStream()
    finalBitmap.compress(Bitmap.CompressFormat.JPEG, 85, stream) // 85% es un buen balance
    return stream.toByteArray()
}

// Función auxiliar para calcular cuánto reducir la imagen
fun calculateInSampleSize(options: BitmapFactory.Options, reqWidth: Int): Int {
    val (height: Int, width: Int) = options.outHeight to options.outWidth
    var inSampleSize = 1
    if (width > reqWidth) {
        val halfWidth = width / 2
        while (halfWidth / inSampleSize >= reqWidth) {
            inSampleSize *= 2
        }
    }
    return inSampleSize
}




actual fun closeApplication() {
    // Mata el proceso actual con código 0 (cierre normal sin errores)
    exitProcess(0)
}

actual fun compressImage(imageData: ByteArray, quality: Double): ByteArray {
    val bitmap = BitmapFactory.decodeByteArray(imageData, 0, imageData.size)
    val outputStream = ByteArrayOutputStream()
    // Reducimos al 70% de calidad y formato JPEG (pesa mucho menos que PNG)
    bitmap.compress(Bitmap.CompressFormat.JPEG, quality.toInt(), outputStream)
    return outputStream.toByteArray()
}


@Composable
actual fun BackPressHandler(
    isEnabled: Boolean,
    onBackPressed: () -> Unit
) {
    // Usamos el BackHandler oficial de Android
    BackHandler(enabled = isEnabled) {
        onBackPressed()
    }
}
