const val kotlinVersion: String = "1.4-M2"
const val kotlinAndroid: String = "android"
const val kotlinAndroidExtension: String = "android.extensions"
const val kotlinKapt: String = "kapt"
const val ktlintVersion: String = "8.2.0"

object Config {
    object Version {
        const val minSdkVersion: Int = 21
        const val compileSdkVersion: Int = 29
        const val targetSdkVersion: Int = 29
        const val versionName: String = "1.0"
        const val versionCode: Int = 1
        const val navigation: String = "2.3.0-beta01"
        const val gradle: String = "4.1.0-alpha10"
        const val daggerHiltAndroid: String = "2.28-alpha"
    }

    const val isMultiDexEnabled: Boolean = true

    object Android {
        const val applicationId: String = "com.example.eziketobenna.bakingapp"
        const val testInstrumentationRunner: String = "androidx.test.runner.AndroidJUnitRunner"
    }

    object Plugin : Libraries {
        private const val kotlin: String = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
        private const val navigation: String =
                "androidx.navigation:navigation-safe-args-gradle-plugin:${Version.navigation}"
        private const val gradle: String = "com.android.tools.build:gradle:${Version.gradle}"
        private const val ktlint: String = "org.jlleitschuh.gradle:ktlint-gradle:$ktlintVersion"
        private const val daggerHilt: String = "com.google.dagger:hilt-android-gradle-plugin:${Version.daggerHiltAndroid}"

        override val components: List<String>
            get() = listOf(kotlin, navigation, gradle, daggerHilt)
    }
}

interface Libraries {
    val components: List<String>
}

object Dependencies {
    object AndroidX : Libraries {
        object Version {
            const val coreKtx: String = "1.3.0"
            const val navigation: String = "2.3.0-alpha03"
            const val multidex: String = "2.0.1"
            const val lifeCycle: String = "2.3.0-alpha03"
            const val activity: String = "1.2.0-alpha05"
        }

        const val coreKtx: String = "androidx.core:core-ktx:${Version.coreKtx}"
        const val navigationFragmentKtx: String =
                "androidx.navigation:navigation-fragment-ktx:${Version.navigation}"
        const val navigationUiKtx: String = "androidx.navigation:navigation-ui-ktx:${Version.navigation}"

        const val multiDex: String = "androidx.multidex:multidex:${Version.multidex}"
        const val activity: String = "androidx.activity:activity:${Version.activity}"

        const val lifeCycleCommon: String = "androidx.lifecycle:lifecycle-common-java8:${Version.lifeCycle}"
        const val liveData: String = "androidx.lifecycle:lifecycle-livedata-ktx:${Version.lifeCycle}"
        const val viewModel: String = "androidx.lifecycle:lifecycle-viewmodel-savedstate:${Version.lifeCycle}"

        override val components: List<String>
            get() = listOf(coreKtx, navigationFragmentKtx, navigationUiKtx, multiDex, activity,
                    lifeCycleCommon, liveData, viewModel)

    }

    object View : Libraries {
        object Version {
            const val materialComponent: String = "1.2.0-alpha04"
            const val shimmerLayout: String = "0.5.0"
            const val appCompat: String = "1.20-rc01"
            const val constraintLayout: String = "2.0.0-beta2"
            const val fragment: String = "1.2.4"
            const val cardView: String = "1.0.0"
            const val recyclerView: String = "1.1.0"
            const val exoPlayer: String = "2.9.1"
        }

        private const val appCompat: String = "androidx.appcompat:appcompat:${Version.appCompat}"
        private const val fragment: String = "androidx.fragment:fragment-ktx:${Version.fragment}"
        private const val cardView: String = "androidx.cardview:cardview:${Version.cardView}"
        private const val materialComponent: String =
                "com.google.android.material:material:${Version.materialComponent}"
        private const val shimmerLayout: String = "com.facebook.shimmer:shimmer:${Version.shimmerLayout}"
        private const val constraintLayout: String =
                "androidx.constraintlayout:constraintlayout:${Version.constraintLayout}"
        private const val recyclerView: String = "androidx.recyclerview:recyclerview:${Version.recyclerView}"
        private const val exoPlayer: String = "com.google.android.exoplayer:exoplayer:${Version.exoPlayer}"

        override val components: List<String> = listOf(
                appCompat, fragment, cardView, materialComponent, shimmerLayout, constraintLayout,
                recyclerView, exoPlayer
        )
    }

    object Kotlin {
        const val stdlib: String = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlinVersion"
    }

    object Network : Libraries {
        object Version {
            const val okhttp: String = "4.3.1"
            const val retrofit: String = "2.9.0"
            const val moshi: String = "1.9.2"
        }

        object AnnotationProcessor {
            const val moshi: String = "com.squareup.moshi:moshi-kotlin-codegen:${Version.moshi}"
        }

        private const val okhttp: String = "com.squareup.okhttp3:okhttp:${Version.okhttp}"
        private const val loggingInterceptor: String = "com.squareup.okhttp3:logging-interceptor:${Version.okhttp}"
        private const val retrofit: String = "com.squareup.retrofit2:retrofit:${Version.retrofit}"
        private const val retrofitMoshi: String = "com.squareup.retrofit2:converter-moshi:${Version.retrofit}"
        private const val moshi: String = "com.squareup.moshi:moshi-kotlin:${Version.moshi}"

        override val components: List<String> = listOf(okhttp, loggingInterceptor, retrofit,
                retrofitMoshi, moshi)
    }

    object DI : Libraries {
        object Version {
            const val javaxInject: String = "1"
            const val dagger: String = "2.28"
            const val daggerHilt: String = "1.0.0-SNAPSHOT"
        }

        object AnnotationProcessor {
            const val daggerProcessor: String = "com.google.dagger:dagger-compiler:${Version.dagger}"
            const val daggerHiltAndroid: String =
                    "com.google.dagger:hilt-android-compiler:${Config.Version.daggerHiltAndroid}"
            const val daggerHilt: String = "androidx.hilt:hilt-compiler:${Version.daggerHilt}"
        }

        private const val dagger: String = "com.google.dagger:dagger:${Version.dagger}"
        private const val daggerHilt: String = "androidx.hilt:hilt-common:${Version.daggerHilt}"
        const val daggerHiltAndroid: String =
                "com.google.dagger:hilt-android:${Config.Version.daggerHiltAndroid}"
        const val daggerHiltViewModel: String =
                "androidx.hilt:hilt-lifecycle-viewmodel:${Config.Version.daggerHiltAndroid}"

        override val components: List<String>
            get() = listOf(daggerHilt, dagger)
    }

    object Persistence {
        object Version {
            const val roomVersion: String = "2.2.5"
        }

        object AnnotationProcessor {
            const val roomCompiler: String = "androidx.room:room-compiler:${Version.roomVersion}"
        }

        const val room: String = "androidx.room:room-ktx:${Version.roomVersion}"
    }

    object Google {
        object Version {
            const val playCore: String = "1.6.4"
        }

        const val playCore: String = "com.google.android.play:core:${Version.playCore}"
    }

    object Coroutines : Libraries {
        object Version {
            const val coroutines: String = "1.3.7"
        }

        const val core: String = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.coroutines}"
        const val android: String = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.coroutines}"

        override val components: List<String> = listOf(core, android)
    }

    object Test : Libraries {
        object Version {
            const val mockk: String = "1.9.3"
            const val junit: String = "4.13"
            const val runner: String = "1.1.0"
            const val rules: String = "1.3.0"
            const val testExt: String = "1.1.1"
            const val espresso: String = "3.2.0"
            const val fragment: String = "1.1.0-rc04"
        }

        const val mockk: String = "io.mockk:mockk:${Version.mockk}"
        const val mockkAndroid: String = "io.mockk:mockk-android:${Version.mockk}"
        const val junit: String = "junit:junit:${Version.junit}"
        const val runner: String = "androidx.test:runner:${Version.runner}"
        const val fragmentTesting: String = "androidx.fragment:fragment-testing:${Version.fragment}"
        const val testExt: String = "androidx.test.ext:junit:${Version.testExt}"
        const val espresso: String = "androidx.test.espresso:espresso-core:${Version.espresso}"
        const val rules: String = "androidx.test:rules:${Version.rules}"

        override val components: List<String>
            get() = listOf(mockk, mockkAndroid, junit, runner, fragmentTesting, testExt,
                    espresso, rules)
    }
}
