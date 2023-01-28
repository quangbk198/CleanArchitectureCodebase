// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id(BuildPlugins.androidApplication) version Versions.buildToolsVersion apply false
    id(BuildPlugins.androidLibrary) version Versions.buildToolsVersion apply false
    id(BuildPlugins.jetbrainsKotlinAndroid) version Versions.jetbrainsKotlinAndroidVersion apply false
    id(BuildPlugins.daggerHilt) version Versions.hilt apply false
}