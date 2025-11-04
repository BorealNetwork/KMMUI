package com.borealnetwork.kmmuicore.utils

import androidx.navigation.NavController
import com.borealnetwork.kmmuicore.domain.base.EMPTY_STRING

fun NavController?.safeNavigate(
    route: String,
    popUpTo: String = EMPTY_STRING,
    avoidPopUpTo: Boolean = true,
    saveState: Boolean = false,
    launchSingleTop: Boolean = true,
    restoreState: Boolean = false,
    callback: () -> Unit = {}
) {
    if (this?.currentDestination?.route !== route) {
        this?.navigate(route) {
//            // Pop up to the start destination of the graph to
//            // avoid building up a large stack of destinations
//            // on the back stack as users select items
            if (!avoidPopUpTo) {
                popUpTo(route = popUpTo) {
                    this.saveState = saveState
                }
            }

            // Avoid multiple copies of the same destination when
            // reselecting the same item
            this.launchSingleTop = launchSingleTop
            // Restore state when reselecting a previously selected item
            this.restoreState = restoreState
        }
        callback()
    }
}

fun NavController.safePopBackstack(route: String, inclusiveOption: Boolean) {
    try {
        popBackStack(route, inclusiveOption)
    } catch (exception: Exception) {
        println("ERROR: safePopBackstack ${exception.message}")
    }
}