object Libs {

    const val androidGradlePlugin = "com.android.tools.build:gradle:8.0.2"

    object Kotlin {
        private const val version = "1.8.10"
        const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"

        object Coroutines {
            private const val version = "1.7.1"
            const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
            const val coroutinesAndroid =
                "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
            const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
        }

    }

    object AndroidX {

        const val coreKtx = "androidx.core:core-ktx:1.10.1"
        const val appCompat = "androidx.appcompat:appcompat:1.6.1"
        const val gson = "com.google.code.gson:gson:2.9.0"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout-compose:1.1.0-alpha10"
        const val activityCompose = "androidx.activity:activity-compose:1.7.2"
        const val composeLiveData = "androidx.compose.runtime:runtime-livedata:1.5.0-beta02"
        const val profilerInstaller = "androidx.profileinstaller:profileinstaller:1.3.1"


        object Compose {
            private const val version = "1.5.0-beta02"
            const val ui = "androidx.compose.ui:ui:$version"
            const val preview = "androidx.compose.ui:ui-tooling-preview:$version"

            object Material {
                private const val version = "1.4.3"
                const val material = "androidx.compose.material:material:$version"
                const val iconsCore =
                    "androidx.compose.material:material-icons-core:$version"
                const val iconsExtended =
                    "androidx.compose.material:material-icons-extended:$version"

            }

            object Material3 {
                private const val version = "1.1.1"
                const val material3 = "androidx.compose.material3:material3:$version"
                const val material3WindowSize =
                    "androidx.compose.material3:material3-window-size-class:$version"
            }

            object Ui {
                private const val version = "1.5.0-beta02"
                private const val versionManifest = "1.4.3"

                const val uiTooling = "androidx.compose.ui:ui-tooling:$version"
                const val uiTestManifest = "androidx.compose.ui:ui-test-manifest:$versionManifest"
                const val uiTestJunit = "androidx.compose.ui:ui-test-junit4:$version"
            }

        }

        object Lifecycle {
            private const val version = "2.6.1"
            const val runtimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:$version"
            const val viewmodelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
            const val viewmodelCompose = "androidx.lifecycle:lifecycle-viewmodel-compose:$version"
        }

        object Navigation {
            private const val version = "2.7.2"
            const val compose = "androidx.navigation:navigation-compose:$version"
        }

        object Test {
            private const val version = "1.5.0"
            const val runner = "androidx.test:runner:$version"
            const val rules = "androidx.test:rules:$version"

            object Ext {
                private const val version = "1.1.5"
                const val junit = "androidx.test.ext:junit-ktx:$version"
            }

            object Espresso {
                private const val version = "3.5.1"
                const val contrib = "androidx.test.espresso:espresso-contrib:$version"
                const val core = "androidx.test.espresso:espresso-core:$version"

            }
        }

        object Paging {
            private const val version = "3.2.0"
            const val runtime = "androidx.paging:paging-runtime-ktx:$version"
            const val compose = "androidx.paging:paging-compose:$version"
        }

        object Hilt {
            private const val version = "1.0.0"
            const val compiler = "androidx.hilt:hilt-compiler:$version"
            const val navigation = "androidx.hilt:hilt-navigation-compose:$version"
        }

    }

    object OkHttp3 {
        private const val version = "4.12.0"
        const val loginInterceptor = "com.squareup.okhttp3:logging-interceptor:$version"
        const val mockWebServer = "com.squareup.okhttp3:mockwebserver:$version"
        const val okhttp = "com.squareup.okhttp3:okhttp:5.0.0-alpha.12"
        const val okhttpbom = "com.squareup.okhttp3:okhttp-bom:$version"
        const val okio = "com.squareup.okio:okio:1.17.2"

    }

    object Retrofit {
        private const val version = "2.9.0"
        private const val coroutinesAdapterVersion = "0.9.2"
        const val retrofit = "com.squareup.retrofit2:retrofit:$version"
        const val converterGson = "com.squareup.retrofit2:converter-gson:$version"
        const val adapterRxJava = "com.squareup.retrofit2:adapter-rxjava2:$version"
        const val coroutinesAdapter =
            "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:$coroutinesAdapterVersion"
    }

    object JUnit {
        private const val version = "4.13.2"
        private const val versionJupiter = "5.8.2"
        const val junit = "junit:junit:$version"
        const val junit5 = "org.junit.jupiter:junit-jupiter:$versionJupiter"
    }

    object Mockito {
        const val kotlin = "org.mockito.kotlin:mockito-kotlin:5.0.0"
        const val inline = "org.mockito:mockito-inline:5.2.0"
    }

    object Espresso{
        private const val version="3.5.1"
        const val contrib = "androidx.test.espresso:espresso-contrib:$version"
    }

    object Hilt {
        private const val version = "2.43.2"
        const val android = "com.google.dagger:hilt-android:$version"
        const val compiler = "com.google.dagger:hilt-compiler:$version"
        const val androidCompiler ="com.google.dagger:hilt-android-compiler:$version"
        const val gradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:$version"
        const val test = "com.google.dagger:hilt-android-testing:$version"
    }

    object Coil {
        const val coilCompose = "io.coil-kt:coil-compose:2.2.1"
        const val accompaistCoil = "com.google.accompanist:accompanist-coil:0.7.0"
    }

    object Arch {
        private const val version = "2.2.0"
        const val coreTesting = "androidx.arch.core:core-testing:$version"

        object Pagin {
            private const val version = "1.0.1"
            const val runtime = "android.arch.paging:runtime:$version"
            const val rxjava2 = "android.arch.paging:rxjava2:$version"
        }

        object Lifecycle {
            private const val version = "1.1.1"
            const val lifecyclePagin = "android.arch.lifecycle:extensions:$version"

        }
    }

}