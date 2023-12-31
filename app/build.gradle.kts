plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.susanadev.rickymorty"
    compileSdk = 33
    defaultConfig {
        applicationId = "com.susanadev.rickymorty"
        minSdk = 26
        targetSdk = 33
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
        kotlinCompilerExtensionVersion = rootProject.extra.get("compose_ui_version") as String
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation("androidx.compose.ui:ui:${rootProject.extra.get("compose_version") as String}")
    implementation("androidx.compose.ui:ui-tooling-preview:${rootProject.extra.get("compose_version") as String}")
    implementation("androidx.compose.material:material:${rootProject.extra.get("compose_ui_version") as String}")
    implementation("androidx.compose.material:material-icons-core:${rootProject.extra.get("compose_ui_version") as String}")
    implementation("androidx.compose.material:material-icons-extended:${rootProject.extra.get("compose_ui_version") as String}")
    implementation("androidx.compose.material3:material3:1.1.1")
    implementation("androidx.compose.material3:material3-window-size-class:1.1.1")
    implementation("androidx.compose.runtime:runtime-livedata:1.5.0-beta02")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")
    implementation("androidx.navigation:navigation-compose:2.7.0-beta01")
    implementation("androidx.constraintlayout:constraintlayout-compose:1.1.0-alpha10")
    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
    implementation("com.google.code.gson:gson:2.9.0")


    //Test
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.mockito:mockito-core:5.4.0")
    testImplementation("androidx.arch.core:core-testing:2.2.0")
    testImplementation("com.squareup.okhttp3:mockwebserver:5.0.0-alpha.11")
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.2")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.5.1")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:${rootProject.extra.get("compose_version") as String}")
    debugImplementation("androidx.compose.ui:ui-tooling:${rootProject.extra.get("compose_version") as String}")
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.4.3")


    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.retrofit2:adapter-rxjava2:2.9.0")
    implementation("com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2")
    implementation("com.squareup.okhttp3:okhttp:5.0.0-alpha.3")
    //Logging Network Calls
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.1")


    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")

    //Coroutines Test
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.1")


    // Coroutine Lifecycle Scopes
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")

    // Coil
    implementation("io.coil-kt:coil-compose:2.2.1")
    implementation("com.google.accompanist:accompanist-coil:0.7.0")

    //Dagger - Hilt
    implementation("com.google.dagger:hilt-android:${rootProject.extra.get("hilt_version") as String}")
    kapt("com.google.dagger:hilt-android-compiler:${rootProject.extra.get("hilt_version") as String}")
    kapt("androidx.hilt:hilt-compiler:1.0.0")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")

    //paging
    implementation("android.arch.lifecycle:extensions:${rootProject.extra.get("lifecycle_version_pagin") as String}")
    implementation("android.arch.paging:runtime:${rootProject.extra.get("paging_version") as String}")
    implementation("android.arch.paging:rxjava2:${rootProject.extra.get("paging_version") as String}")
    implementation("androidx.paging:paging-runtime-ktx:3.2.0")
    implementation("androidx.paging:paging-compose:3.2.0")

    implementation("androidx.profileinstaller:profileinstaller:1.3.1")
}