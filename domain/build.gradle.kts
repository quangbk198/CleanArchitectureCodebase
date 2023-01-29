plugins {
    id(BuildPlugins.androidLibrary)
    id(BuildPlugins.jetbrainsKotlinAndroid)
    id(BuildPlugins.kotlinKapt)
}

android {
    namespace = Namespace.namespaceDomainModule
    compileSdk = AndroidSdk.compileSdk

    defaultConfig {
        minSdk = AndroidSdk.minSdk
        targetSdk = AndroidSdk.targetSdk

        testInstrumentationRunner = AndroidClient.testRunner
        consumerProguardFiles("consumer-rules.pro")
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
}

dependencies {

    api(Libraries.coreKtx)

    // Kotlin Coroutines
    implementation(Libraries.coroutinesCore)
    implementation(Libraries.coroutinesAndroid)

    // Kotlin Standard Library
    implementation(Libraries.kotlinStdLib)

    // Unit or android test
    testImplementation(TestLibraries.junit)

    // Acceptance test
    androidTestImplementation(TestLibraries.testExtJunit)
    androidTestImplementation(TestLibraries.espressoCore)

    // Local library module dependency
    implementation(project(mapOf("path" to ":core")))
    implementation(project(mapOf("path" to ":data")))
}