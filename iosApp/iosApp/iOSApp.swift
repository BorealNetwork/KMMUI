import SwiftUI
import ComposeApp

@main
struct iOSApp: App {

    init() {
        KoinHelperKt.koinInjection(appDeclaration:{ _ in })
    }
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}