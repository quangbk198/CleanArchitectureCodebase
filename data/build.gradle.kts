plugins {
    id(BuildPlugins.androidLibrary)
    id(BuildPlugins.jetbrainsKotlinAndroid)
    id(BuildPlugins.kotlinKapt)
}

android {
    namespace = Namespace.namespaceDataModule
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

    // Room database
    api(Libraries.roomKtx)
    api(Libraries.roomRuntime)
    kapt(Libraries.roomCompiler)

    // Retrofit
    implementation(Libraries.retrofit)
    implementation(Libraries.loggingInterceptor)
    implementation(Libraries.retrofitConverterGson)

    // Unit or android test
    testImplementation(TestLibraries.junit)

    // Acceptance test
    androidTestImplementation(TestLibraries.testExtJunit)
    androidTestImplementation(TestLibraries.espressoCore)

    // Local library module dependency
    implementation(project(mapOf("path" to ":core")))
}