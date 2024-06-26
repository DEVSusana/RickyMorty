plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.kapt")
}

android {
    namespace = "com.susanadev.rickymorty.usecases"
    compileSdk = 34
    compileSdk = 34
    defaultConfig {
        minSdk = 26
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":data"))

    //Coroutines
    implementation(Libs.Kotlin.Coroutines.core)
    implementation(Libs.Kotlin.Coroutines.coroutinesAndroid)
    //Paging
    implementation(Libs.AndroidX.Paging.runtime)
    implementation(Libs.AndroidX.Paging.compose)
    //Retrofit
    implementation(Libs.Retrofit.retrofit)
    implementation(Libs.Retrofit.adapterRxJava)
    implementation(Libs.Retrofit.converterGson)
    implementation(Libs.Retrofit.coroutinesAdapter)
    //Hilt
    implementation(Libs.Hilt.android)
    implementation(Libs.AndroidX.Hilt.navigation)
    kapt(Libs.Hilt.androidCompiler)
    kapt(Libs.AndroidX.Hilt.compiler)
    implementation(Libs.Hilt.gradlePlugin)
    kapt(Libs.Hilt.compiler)
    //Test
    testImplementation(Libs.Kotlin.Coroutines.test)
    testImplementation(Libs.JUnit.junit)
    testImplementation(Libs.JUnit.junit5)
    testImplementation(Libs.Mockito.kotlin)
    testImplementation(Libs.Mockito.inline)
    testImplementation(Libs.Arch.coreTesting)

    androidTestImplementation(Libs.AndroidX.Test.runner)
    androidTestImplementation(Libs.AndroidX.Test.rules)
    androidTestImplementation(Libs.AndroidX.Test.Ext.junit)
    androidTestImplementation(Libs.AndroidX.Test.Espresso.contrib)
    androidTestImplementation(Libs.AndroidX.Compose.Ui.uiTestJunit)
    androidTestImplementation(Libs.Hilt.test)
    androidTestImplementation(Libs.Espresso.contrib)
    androidTestImplementation(Libs.OkHttp3.mockWebServer)
    androidTestImplementation(Libs.OkHttp3.okio)

    debugImplementation(Libs.AndroidX.Compose.Ui.uiTestManifest)
    debugImplementation(Libs.AndroidX.Compose.Ui.uiTooling)

    kaptAndroidTest(Libs.Hilt.compiler)



    testImplementation("androidx.arch.core:core-testing:2.2.0")
}
