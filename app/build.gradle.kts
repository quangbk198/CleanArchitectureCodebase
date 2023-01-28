plugins {
    id(BuildPlugins.androidApplication)
    id(BuildPlugins.jetbrainsKotlinAndroid)
}

android {
    namespace = AndroidClient.namespace
    compileSdk = AndroidSdk.compileSdk

    defaultConfig {
        applicationId = AndroidClient.appId
        minSdk = AndroidSdk.minSdk
        targetSdk = AndroidSdk.targetSdk
        versionCode = AndroidClient.versionCode
        versionName = AndroidClient.versionName

        testInstrumentationRunner = AndroidClient.testRunner
        multiDexEnabled = AndroidClient.multidexEnabled
        vectorDrawables {
            useSupportLibrary = AndroidClient.useSupportLibrary
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(Libraries.coreKtx)
    implementation(Libraries.appCompat)
    implementation(Libraries.material)
    implementation(Libraries.constraintLayout)

    // Unit or android test
    testImplementation(TestLibraries.junit)

    // Acceptance test
    androidTestImplementation(TestLibraries.testExtJunit)
    androidTestImplementation(TestLibraries.espressoCore)
}