package com.borealnetwork.kmmuicore.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.borealnetwork.kmmuicore.domain.sources_files.PhotoSource
import dev.icerock.moko.permissions.DeniedAlwaysException
import dev.icerock.moko.permissions.Permission
import dev.icerock.moko.permissions.camera.CAMERA
import dev.icerock.moko.permissions.compose.BindEffect
import dev.icerock.moko.permissions.compose.rememberPermissionsControllerFactory
import dev.icerock.moko.permissions.gallery.GALLERY
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun GetPermissions(
    scope: CoroutineScope,
    onDismissRequest: () -> Unit,
    openCamera: () -> Unit,
    openGallery: () -> Unit
) {

    // 1. Inicializar el controlador de permisos
    val factory = rememberPermissionsControllerFactory()
    val controller = remember(factory) { factory.createPermissionsController() }
    BindEffect(controller)
    PhotoSourceDialog(
        onDismissRequest = onDismissRequest,
        onOptionSelected = { source ->
            when (source) {
                is PhotoSource.Camera -> {
                    println("Cámara seleccionada")
                    scope.launch {
                        try {
                            // Solicita el permiso (suspende la corrutina hasta que el usuario responda)
//                            controller.providePermission(Permission.STORAGE)
                            controller.providePermission(Permission.CAMERA)
//                            controller.providePermission(Permission.WRITE_STORAGE)

                            // Si el código llega aquí, tenemos permiso
                            println("Permiso concedido para $source")
                            // Aquí llamarías a tu lógica de abrir cámara/galería
                            openCamera()
                        } catch (e: Exception) {
                            // Si entra aquí, el permiso fue denegado o el usuario marcó "No volver a preguntar"
                            println("Error o denegación: ${e.message}")
                        }
                    }
                }

                is PhotoSource.Gallery -> {
                    println("Galería seleccionada")
                    scope.launch {
                        try {
                            // Solicita el permiso (suspende la corrutina hasta que el usuario responda)
                            controller.providePermission(Permission.GALLERY)

                            // Si el código llega aquí, tenemos permiso
                            println("Permiso concedido para $source")
                            // Aquí llamarías a tu lógica de abrir cámara/galería
                            openGallery()
                        } catch (e: DeniedAlwaysException) {
                            // EL USUARIO MARCÓ "NO VOLVER A PREGUNTAR"
                            println("Permiso denegado permanentemente")
                            openGallery()
//
//                            // Opción A: Abrir los ajustes directamente
//                            controller.openAppSettings()
//
//                            // Opción B (Recomendada): Mostrar un Dialog propio explicando
//                            // POR QUÉ necesitas que vaya a ajustes antes de redirigirlo.
                        } catch (e: Exception) {
                            // Si entra aquí, el permiso fue denegado o el usuario marcó "No volver a preguntar"
                            println("Error o denegación: ${e.message}")
                        }
                    }
                }
            }
        }
    )
}