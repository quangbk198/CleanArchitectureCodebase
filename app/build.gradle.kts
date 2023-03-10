import com.android.build.gradle.internal.api.BaseVariantOutputImpl

plugins {
    id(BuildPlugins.androidApplication)
    id(BuildPlugins.jetbrainsKotlinAndroid)
    id(BuildPlugins.kotlinAndroid)
    id(BuildPlugins.kotlinKapt)
    id(BuildPlugins.hiltAndroidPlugin)
    id(BuildPlugins.kotlinParcelize)
}

android {
    namespace = Namespace.namespaceAppModule
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
        buildConfigField("String","SHARE_PREFERENCE_FOLDER","\"share_preference_data\"")
        buildConfigField("String","DATABASE_NAME","\"app_local_database\"")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        // Config output APK filename
        applicationVariants.all {
            val variant = this
            variant.outputs
                .map { it as BaseVariantOutputImpl }
                .forEach { output ->
                    val outputFileName = variant.name +
                            "_ver${variant.versionName}" +
                            "_${getCurrentTime()}.apk"
                    output.outputFileName = outputFileName
                }
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
    implementation(Libraries.conflictGuava)

    // Kotlin Standard Library
    implementation(Libraries.kotlinStdLib)

    // Android KTX
    implementation(Libraries.activityKtx)
    implementation(Libraries.fragmentKtx)

    // Kotlin Coroutines
    implementation(Libraries.coroutinesCore)
    implementation(Libraries.coroutinesAndroid)

    // Hilt
    implementation(Libraries.hiltAndroid)
    kapt(Libraries.hiltCompiler)

    // Lifecycle
    implementation(Libraries.lifecycleCompiler)
    implementation(Libraries.viewModel)
    implementation(Libraries.liveData)
    implementation(Libraries.lifeCycleRuntime)
    implementation(Libraries.lifecycleExtensions)

    // Retrofit
    implementation(Libraries.retrofit)
    implementation(Libraries.loggingInterceptor)
    implementation(Libraries.retrofitConverterGson)

    // OKHttp
    implementation(Libraries.okhttp3)

    // View
    implementation(Libraries.cardView)
    implementation(Libraries.recyclerView)

    // Custom fonts
    implementation(Libraries.calligraphy3)

    // View inflation
    implementation(Libraries.viewPump)

    // Glide
    implementation(Libraries.glide)

    // Lottie
    implementation(Libraries.lottie)

    // Annotation
    implementation(Libraries.androidAnnotations)

    // MultiDex
    implementation(Libraries.multidex)

    // Unit or android test
    testImplementation(TestLibraries.junit)

    // Acceptance test
    androidTestImplementation(TestLibraries.testExtJunit)
    androidTestImplementation(TestLibraries.espressoCore)

    // Local library module dependency
    implementation(project(path = SubModule.core))
    implementation(project(path = SubModule.data))
    implementation(project(path = SubModule.domain))
}