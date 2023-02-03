plugins {
    id(BuildPlugins.androidLibrary)
    id(BuildPlugins.jetbrainsKotlinAndroid)
    id(BuildPlugins.kotlinKapt)
}

android {
    namespace = Namespace.namespaceCoreModule
    compileSdk = AndroidSdk.compileSdk

    defaultConfig {
        minSdk = AndroidSdk.minSdk
        targetSdk = AndroidSdk.targetSdk

        testInstrumentationRunner = AndroidClient.testRunner
        consumerProguardFiles("consumer-rules.pro")
        buildConfigField("String","SHARE_PREFERENCE_FOLDER","\"share_preference_data\"")
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

    api(Libraries.coreKtx)
    api(Libraries.appCompat)
    api(Libraries.material)
    api(Libraries.constraintLayout)

    // Kotlin Standard Library
    api(Libraries.kotlinStdLib)

    // Kotlin Coroutines
    api(Libraries.coroutinesCore)
    api(Libraries.coroutinesAndroid)

    // Lifecycle
    api(Libraries.lifecycleExtensions)

    // View
    api(Libraries.cardView)
    api(Libraries.recyclerView)

    // Annotation
    api(Libraries.androidAnnotations)

    // Glide
    api(Libraries.glide)

    // Lottie
    api(Libraries.lottie)

    // Retrofit
    api(Libraries.retrofit)
    api(Libraries.loggingInterceptor)
    api(Libraries.retrofitConverterGson)

    // Hilt
    api(Libraries.hiltAndroid)
    kapt(Libraries.hiltCompiler)

    // Custom fonts
    api(Libraries.calligraphy3)

    // View inflation
    api(Libraries.viewPump)

    // Lifecycle
    api(Libraries.lifeCycleRuntime)

    // Unit or android test
    testImplementation(TestLibraries.junit)

    // Acceptance test
    androidTestImplementation(TestLibraries.testExtJunit)
    androidTestImplementation(TestLibraries.espressoCore)
}