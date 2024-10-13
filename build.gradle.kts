// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.jetbrains.kotlin.kapt) apply false
    alias(libs.plugins.dagger.hilt) apply false
    alias(libs.plugins.ktor.serialization) apply false
    alias(libs.plugins.kotlin.parcelize) apply false
}

//buildscript {
//    dependencies {
//        classpath "com.google.dagger:hilt-android-gradle-plugin:2.48"
//    }
//}