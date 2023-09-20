plugins {
    id("com.android.application") version "8.0.2" apply false
    id("com.android.library") version "8.0.2" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
    id("org.jetbrains.kotlin.jvm") version "1.8.10" apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

buildscript {

    dependencies {
        classpath(Libs.androidGradlePlugin)
        classpath(Libs.Kotlin.kotlinGradlePlugin)
        classpath(Libs.Hilt.gradlePlugin)

    }
}

