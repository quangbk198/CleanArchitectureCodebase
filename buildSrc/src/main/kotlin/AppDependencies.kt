/**
 * Created by quangnh
 * Date: 27/1/2023
 * Time: 10:50 PM
 * Project CleanArchitectureCodebase
 */
object AndroidSdk {
    const val minSdk = 21
    const val compileSdk = 33
    const val targetSdk = 33
}

object AndroidClient {
    const val namespace = "com.example.cleanarchitecturecodebase"
    const val appId = "com.example.cleanarchitecturecodebase"
    const val versionCode = 1
    const val versionName = "1.0"
    const val testRunner = "androidx.test.runner.AndroidJUnitRunner"
    const val multidexEnabled = true
    const val useSupportLibrary = true
}

object Versions {
    const val buildToolsVersion = "7.3.1"
    const val jetbrainsKotlinAndroidVersion = "1.7.20"
}

object BuildPlugins {
    const val androidApplication = "com.android.application"
    const val androidLibrary = "com.android.library"
    const val jetbrainsKotlinAndroid = "org.jetbrains.kotlin.android"
}

object Libraries {
    private object LibVersion {
        const val coreKtx = "1.7.0"
        const val appcompat = "1.5.1"
        const val material = "1.7.0"
        const val constraintLayout = "2.1.4"
    }

    const val coreKtx = "androidx.core:core-ktx:${LibVersion.coreKtx}"
    const val appCompat = "androidx.appcompat:appcompat:${LibVersion.appcompat}"
    const val material = "com.google.android.material:material:${LibVersion.material}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${LibVersion.constraintLayout}"
}

object TestLibraries {
    private object TestLibVersions {
        const val junit = "4.13.2"
        const val testExtJunit = "1.1.5"
        const val espressoCore = "3.5.1"
    }
    const val junit = "junit:junit:${TestLibVersions.junit}"
    const val testExtJunit = "androidx.test.ext:junit:${TestLibVersions.testExtJunit}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${TestLibVersions.espressoCore}"
}