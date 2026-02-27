package com.borealnetwork.kmmuicore.domain.gps

import platform.Foundation.NSURL
import platform.UIKit.UIApplication
import platform.UIKit.UIApplicationOpenSettingsURLString

class IosGpsActivatorHandler : GpsActivatorHandler {
    override fun openGpsSettings() {

        val settingsUrl = NSURL(string = UIApplicationOpenSettingsURLString)

        if (UIApplication.sharedApplication.canOpenURL(settingsUrl)) {
            UIApplication.sharedApplication.openURL(
                url = settingsUrl,
                options = emptyMap<Any?, Any?>(),
                completionHandler = null
            )
        }
    }
}