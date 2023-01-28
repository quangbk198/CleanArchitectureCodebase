import java.text.SimpleDateFormat
import java.util.*

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
    const val hilt = "2.44"
}

object BuildPlugins {
    const val androidApplication = "com.android.application"
    const val androidLibrary = "com.android.library"
    const val jetbrainsKotlinAndroid = "org.jetbrains.kotlin.android"
    const val kotlinKapt = "kotlin-kapt"
    const val hiltAndroidPlugin = "dagger.hilt.android.plugin"
    const val kotlinAndroid = "kotlin-android"
    const val kotlinParcelize = "kotlin-parcelize"
    const val daggerHilt = "com.google.dagger.hilt.android"
}

object Libraries {
    private object LibVersions {
        const val coreKtx = "1.7.0"
        const val appcompat = "1.5.1"
        const val material = "1.7.0"
        const val constraintLayout = "2.1.4"
        const val kotlinStdLib = "1.8.0"
        const val ktx = "1.5.1"
        const val coroutineVersion = "1.6.2"
        const val lifecycle = "2.5.1"
        const val lifecycleExtensions = "2.2.0"
        const val cardView = "1.0.0"
        const val recyclerView = "1.1.0"
        const val annotations = "1.5.0"
        const val glide = "4.14.2"
        const val hilt = "2.44"
        const val calligraphyVersion = "3.1.1"
        const val viewPumpVersion = "2.0.3"
        const val retrofit = "2.9.0"
        const val loggingInterceptor = "4.9.0"
        const val okhttp3 = "5.0.0-alpha.2"
        const val roomDB = "2.4.2"
    }

    const val coreKtx = "androidx.core:core-ktx:${LibVersions.coreKtx}"
    const val appCompat = "androidx.appcompat:appcompat:${LibVersions.appcompat}"
    const val material = "com.google.android.material:material:${LibVersions.material}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${LibVersions.constraintLayout}"
    const val conflictGuava = "com.google.guava:listenablefuture:9999.0-empty-to-avoid-conflict-with-guava"

    // Kotlin Standard Library
    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${LibVersions.kotlinStdLib}"

    // Android KTX
    const val activityKtx = "androidx.activity:activity-ktx:${LibVersions.ktx}"
    const val fragmentKtx = "androidx.fragment:fragment-ktx:${LibVersions.ktx}"

    // Kotlin Coroutines
    const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${LibVersions.coroutineVersion}"
    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${LibVersions.coroutineVersion}"

    // Hilt
    const val hiltAndroid = "com.google.dagger:hilt-android:${LibVersions.hilt}"
    const val hiltCompiler = "com.google.dagger:hilt-compiler:${LibVersions.hilt}"

    // Lifecycle
    const val lifecycleCompiler = "androidx.lifecycle:lifecycle-compiler:${LibVersions.lifecycle}"
    const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${LibVersions.lifecycle}"
    const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:${LibVersions.lifecycle}"
    const val lifeCycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${LibVersions.lifecycle}"
    const val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:${LibVersions.lifecycleExtensions}"

    // Retrofit
    const val retrofit = "com.squareup.retrofit2:converter-gson:${LibVersions.retrofit}"
    const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${LibVersions.loggingInterceptor}"
    const val retrofitConverterGson = "com.squareup.retrofit2:converter-gson:${LibVersions.retrofit}"

    // OKHttp
    const val okhttp3 = "com.squareup.okhttp3:okhttp:${LibVersions.okhttp3}"

    // Room database
    const val roomRuntime = "androidx.room:room-runtime:${LibVersions.roomDB}"
    const val roomCompiler = "androidx.room:room-compiler:${LibVersions.roomDB}"
    const val roomKtx = "androidx.room:room-ktx:${LibVersions.roomDB}"


    // View
    const val cardView = "androidx.cardview:cardview:${LibVersions.cardView}"
    const val recyclerView = "androidx.recyclerview:recyclerview:${LibVersions.recyclerView}"

    // Custom fonts
    const val calligraphy3 = "io.github.inflationx:calligraphy3:${LibVersions.calligraphyVersion}"

    // View inflation
    // ViewPump installs a custom LayoutInflater via a ContextThemeWrapper and provides an API of pre/post-inflation interceptors
    const val viewPump = "io.github.inflationx:viewpump:${LibVersions.viewPumpVersion}"

    // Glide
    const val glide = "com.github.bumptech.glide:glide:${LibVersions.glide}"

    // Annotation
    const val androidAnnotations = "androidx.annotation:annotation:${LibVersions.annotations}"
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

@Suppress("SimpleDateFormat")
fun getCurrentDayTime(): String {
    val timeCurrent = Date()
    val dateFormat = SimpleDateFormat("dd.MM.HH.mm")
    return dateFormat.format(timeCurrent)
}