plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.susanadev.rickymorty"
    compileSdk = 34
    defaultConfig {
        applicationId = "com.susanadev.rickymorty"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        buildConfigField("String", "URL_BASE", "\"https://rickandmortyapi.com/api/\"")
        vectorDrawables.useSupportLibrary = true
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":usecases"))

    //Coroutines
    implementation(Libs.Kotlin.Coroutines.core)
    implementation(Libs.Kotlin.Coroutines.coroutinesAndroid)

    //AndroidX
    implementation(Libs.AndroidX.coreKtx)
    implementation(Libs.AndroidX.appCompat)
    implementation(Libs.AndroidX.gson)
    implementation(Libs.AndroidX.constraintLayout)
    implementation(Libs.AndroidX.activityCompose)
    implementation(Libs.AndroidX.composeLiveData)
    implementation(Libs.AndroidX.profilerInstaller)

    //Compose
    implementation(Libs.AndroidX.Compose.ui)
    implementation(Libs.AndroidX.Compose.preview)
    implementation(Libs.AndroidX.Compose.Material.material)
    implementation(Libs.AndroidX.Compose.Material.iconsCore)
    implementation(Libs.AndroidX.Compose.Material.iconsExtended)
    implementation(Libs.AndroidX.Compose.Material3.material3)
    implementation(Libs.AndroidX.Compose.Material3.material3WindowSize)


    //Lifecycle
    implementation(Libs.AndroidX.Lifecycle.runtimeKtx)
    implementation(Libs.AndroidX.Lifecycle.viewmodelKtx)
    implementation(Libs.AndroidX.Lifecycle.viewmodelCompose)

    //Navigation
    implementation(Libs.AndroidX.Navigation.compose)

    //Test
    testImplementation(Libs.Kotlin.Coroutines.test)
    testImplementation(Libs.JUnit.junit)
    testImplementation(Libs.JUnit.junit5)
    testImplementation(Libs.Mockito.kotlin)
    testImplementation(Libs.Mockito.inline)
    testImplementation(Libs.Arch.coreTesting)
    testImplementation(project(":app"))

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

    //Paging
    implementation(Libs.AndroidX.Paging.runtime)
    implementation(Libs.AndroidX.Paging.compose)

    //Hilt
    implementation(Libs.Hilt.android)
    implementation(Libs.AndroidX.Hilt.navigation)
    kapt(Libs.Hilt.androidCompiler)
    kapt(Libs.AndroidX.Hilt.compiler)
    implementation(Libs.Hilt.gradlePlugin)
    kapt(Libs.Hilt.compiler)

    //OkHttp3
    implementation(platform(Libs.OkHttp3.okhttpbom))
    implementation(Libs.OkHttp3.loginInterceptor)
    implementation(Libs.OkHttp3.okhttp)

    //Retrofit
    implementation(Libs.Retrofit.retrofit)
    implementation(Libs.Retrofit.adapterRxJava)
    implementation(Libs.Retrofit.converterGson)
    implementation(Libs.Retrofit.coroutinesAdapter)

    //Coil
    implementation(Libs.Coil.accompaistCoil)
    implementation(Libs.Coil.coilCompose)

    //Arch
    implementation(Libs.Arch.Pagin.runtime)
    implementation(Libs.Arch.Pagin.rxjava2)
    implementation(Libs.Arch.Lifecycle.lifecyclePagin)


}