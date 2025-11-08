import com.android.build.api.dsl.androidLibrary
import com.android.utils.TraceUtils.simpleId
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.android.kotlin.multiplatform.library)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.kotlinx.serialization)
    id("com.vanniktech.maven.publish") version "0.34.0"
    id("maven-publish")
}

group = "io.github.baudelioandalon"
version = "1.0.5"

mavenPublishing {
    publishToMavenCentral()

    signAllPublications()

    coordinates(group.toString(), "kmmuicore", version.toString())
    pom {
        name = "KMMUI Library"
        description = "Libreria multiplataforma de componentes visuales y utilerias."
        inceptionYear = "2025"
        url = "https://github.com/BorealNetwork/KMMUI/"
        licenses {
            license {
                name = "The Apache License, Version 2.0"
                url = "https://www.apache.org/licenses/LICENSE-2.0.txt"
                distribution = "https://www.apache.org/licenses/LICENSE-2.0.txt"
            }
        }
        developers {
            developer {
                id = "BorealNetwork"
                name = "Team BorealNetwork"
                url = "https://github.com/BorealNetwork/"
            }
        }
        scm {
            url = "https://github.com/BorealNetwork/KMMUI/"
            connection = "scm:git:git://github.com/BorealNetwork/KMMUI.git"
            developerConnection = "scm:git:ssh://git@github.com/BorealNetwork/KMMUI.git"
        }
    }
}

compose.resources {
    publicResClass = true
    packageOfResClass = "io.github.baudelioandalon.kmmuicore.drawable"
    generateResClass = always
}

kotlin {

    androidLibrary {
        namespace = "io.github.baudelioandalon.kmmuicore"
        compileSdk = libs.versions.android.compileSdk.get().toInt()
        minSdk = libs.versions.android.minSdk.get().toInt()

        // Enables Java compilation support.
        // This improves build times when Java compilation is not needed
        withJava()

        withHostTestBuilder {}.configure {}
        withDeviceTestBuilder {
            sourceSetTreeName = "test"
        }

        compilations.configureEach {
            compilerOptions.configure {
                jvmTarget.set(
                    JvmTarget.JVM_11
                )
            }
        }
        experimentalProperties["android.experimental.kmp.enableAndroidResources"] = true
    }

    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeLibrary"
            isStatic = true
        }
    }

    sourceSets {

        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)

            //ktor
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.negotiation)
            implementation(libs.ktor.serialization)
            implementation(libs.ktor.logging)

            //Koin
            implementation(project.dependencies.platform(libs.koin.bom))
            implementation(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewmodel)
            implementation(libs.koin.compose.navigation)
        }

        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
            implementation(libs.koin.android)
            implementation(libs.ktor.client.okhttp)
        }
        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}