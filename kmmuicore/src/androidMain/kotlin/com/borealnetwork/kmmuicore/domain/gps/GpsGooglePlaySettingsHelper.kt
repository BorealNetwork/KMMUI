package com.borealnetwork.kmmuicore.domain.gps

import android.content.Context
import androidx.activity.result.IntentSenderRequest
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.location.Priority

class GpsGooglePlaySettingsHelper(private val context: Context) {

    fun checkAndPromptGps(
        onGpsAlreadyEnabled: () -> Unit,
        onRequireResolution: (IntentSenderRequest) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        // 1. Configuramos la petición de alta precisión
        val locationRequest = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 1000).build()
        val builder = LocationSettingsRequest.Builder().addLocationRequest(locationRequest)

        // 2. Obtenemos el cliente
        val client = LocationServices.getSettingsClient(context)
        val task = client.checkLocationSettings(builder.build())

        // 3. Manejamos la respuesta
        task.addOnSuccessListener {
            // El GPS ya estaba encendido
            onGpsAlreadyEnabled()
        }

        task.addOnFailureListener { exception ->
            if (exception is ResolvableApiException) {
                // El GPS está apagado, pero podemos mostrar el diálogo de Google
                try {
                    val intentSenderRequest = IntentSenderRequest.Builder(exception.resolution).build()
                    onRequireResolution(intentSenderRequest)
                } catch (sendEx: Exception) {
                    onFailure(sendEx)
                }
            } else {
                // El GPS está apagado y no se puede resolver automáticamente (muy raro)
                onFailure(exception)
            }
        }
    }
}