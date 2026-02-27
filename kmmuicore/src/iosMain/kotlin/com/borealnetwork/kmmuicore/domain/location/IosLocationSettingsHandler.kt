package com.borealnetwork.kmmuicore.domain.location

import platform.Foundation.NSURL
import platform.UIKit.UIApplication
import platform.UIKit.UIApplicationOpenSettingsURLString

class IosLocationSettingsHandler : LocationSettingsHandler {
    override fun openLocationSettings() {
        val settingsUrl = NSURL(string = UIApplicationOpenSettingsURLString)
        if (UIApplication.Companion.sharedApplication.canOpenURL(settingsUrl)) {
            UIApplication.Companion.sharedApplication.openURL(settingsUrl)
        }
    }
}