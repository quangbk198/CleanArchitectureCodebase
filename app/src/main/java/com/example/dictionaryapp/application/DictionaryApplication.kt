package com.example.dictionaryapp.application

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.example.dictionaryapp.utils.ConstantApp
import com.quangnh.core.utils.LocaleHelper
import dagger.hilt.android.HiltAndroidApp
import io.github.inflationx.calligraphy3.CalligraphyConfig
import io.github.inflationx.calligraphy3.CalligraphyInterceptor
import io.github.inflationx.viewpump.ViewPump
import javax.inject.Inject

/**
 * Created by quangnh
 * Date: 1/2/2023
 * Time: 10:27 PM
 * Project DictionaryApp
 */
@HiltAndroidApp
class DictionaryApplication : Application() {
    @Inject
    lateinit var calligraphyConfig: CalligraphyConfig

    override fun onCreate() {
        super.onCreate()

        // Init font
        initFont()
    }

    private fun initFont() {
        ViewPump.init(
            ViewPump.builder()
                .addInterceptor(CalligraphyInterceptor(calligraphyConfig))
                .build()
        )
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base?.let { LocaleHelper.onAttach(it, ConstantApp.VI) })
        MultiDex.install(this)
    }
}