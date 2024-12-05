// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    dependencies {
        classpath(libs.hilt.android.gradle.plugin)
        classpath(libs.gradle)
    }
}


plugins {
    alias(libs.plugins.android.application) version "8.7.2" apply false
    alias(libs.plugins.android.libary) version "8.1.4" apply false
    alias(libs.plugins.kotlin.android) version "1.9.21" apply false
}