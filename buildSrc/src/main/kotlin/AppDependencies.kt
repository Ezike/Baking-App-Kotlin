const val kotlinAndroid: String = "android"
const val kotlinKapt: String = "kapt"
const val ktLintVersion: String = "0.37.2"

object Config {
    object Version {
        const val minSdkVersion: Int = 21
        const val compileSdkVersion: Int = 29
        const val targetSdkVersion: Int = 29
        const val versionName: String = "1.0"
        const val versionCode: Int = 1
    }

    const val isMultiDexEnabled: Boolean = true

    object Android {
        const val applicationId: String = "com.example.eziketobenna.bakingapp"
        const val testInstrumentationRunner: String = "androidx.test.runner.AndroidJUnitRunner"
    }
}

interface Libraries {
    val components: List<String>
}

object Dependencies {
    object AndroidX : Libraries {
        object Version {
            const val coreKtx: String = "1.3.2"
            const val navigation: String = "2.3.1"
            const val multidex: String = "2.0.1"
            const val lifeCycle: String = "2.3.0-beta01"
            const val activity: String = "1.2.0-beta01"
        }

        const val coreKtx: String = "androidx.core:core-ktx:${Version.coreKtx}"
        const val navigationFragmentKtx: String =
                "androidx.navigation:navigation-fragment-ktx:${Version.navigation}"
        const val navigationUiKtx: String =
                "androidx.navigation:navigation-ui-ktx:${Version.navigation}"
        const val navigationDFM: String =
                "androidx.navigation:navigation-dynamic-features-fragment:${Version.navigation}"
        const val multiDex: String = "androidx.multidex:multidex:${Version.multidex}"
        const val activity: String = "androidx.activity:activity:${Version.activity}"
        const val lifeCycleCommon: String =
                "androidx.lifecycle:lifecycle-common-java8:${Version.lifeCycle}"
        const val viewModel: String =
                "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.lifeCycle}"

        override val components: List<String>
            get() = listOf(
                    coreKtx, navigationFragmentKtx, navigationUiKtx, multiDex, activity,
                    lifeCycleCommon, viewModel
            )
    }

    object View : Libraries {
        object Version {
            const val materialComponent: String = "1.2.0-alpha04"
            const val shimmerLayout: String = "0.5.0"
            const val appCompat: String = "1.2.0-rc01"
            const val constraintLayout: String = "2.0.0-beta6"
            const val fragment: String = "1.2.4"
            const val cardView: String = "1.0.0"
            const val recyclerView: String = "1.1.0"
            const val exoPlayer: String = "2.10.5"
            const val coil: String = "0.11.0"
            const val swipeRefreshLayout: String = "1.1.0-rc01"
        }

        const val appCompat: String = "androidx.appcompat:appcompat:${Version.appCompat}"
        const val fragment: String = "androidx.fragment:fragment-ktx:${Version.fragment}"
        const val cardView: String = "androidx.cardview:cardview:${Version.cardView}"
        const val materialComponent: String =
                "com.google.android.material:material:${Version.materialComponent}"
        const val shimmerLayout: String =
                "com.facebook.shimmer:shimmer:${Version.shimmerLayout}"
        const val constraintLayout: String =
                "androidx.constraintlayout:constraintlayout:${Version.constraintLayout}"
        const val recyclerView: String =
                "androidx.recyclerview:recyclerview:${Version.recyclerView}"
        const val exoPlayerCore: String =
                "com.google.android.exoplayer:exoplayer-core:${Version.exoPlayer}"
        const val exoPlayerUI: String =
                "com.google.android.exoplayer:exoplayer-ui:${Version.exoPlayer}"
        const val coil: String = "io.coil-kt:coil:${Version.coil}"
        const val swipeRefreshLayout: String =
                "androidx.swiperefreshlayout:swiperefreshlayout:${Version.swipeRefreshLayout}"

        override val components: List<String> = listOf(
                appCompat, fragment, cardView,
                materialComponent, constraintLayout
        )
    }

    object FlowBinding {
        private const val flowBindingVersion: String = "1.0.0-beta02"
        const val android: String =
                "io.github.reactivecircus.flowbinding:flowbinding-android:$flowBindingVersion"
        const val swipeRefresh: String =
                "io.github.reactivecircus.flowbinding:flowbinding-swiperefreshlayout:$flowBindingVersion"
        const val lifecycle: String =
                "io.github.reactivecircus.flowbinding:flowbinding-lifecycle:$flowBindingVersion"
    }

    object Network : Libraries {
        object Version {
            const val okhttp: String = "4.7.2"
            const val retrofit: String = "2.9.0"
            const val moshi: String = "1.9.2"
        }

        object AnnotationProcessor {
            const val moshi: String = "com.squareup.moshi:moshi-kotlin-codegen:${Version.moshi}"
        }

        private const val okhttp: String = "com.squareup.okhttp3:okhttp:${Version.okhttp}"
        private const val loggingInterceptor: String =
                "com.squareup.okhttp3:logging-interceptor:${Version.okhttp}"
        private const val retrofit: String = "com.squareup.retrofit2:retrofit:${Version.retrofit}"
        private const val retrofitMoshi: String =
                "com.squareup.retrofit2:converter-moshi:${Version.retrofit}"
        const val moshi: String = "com.squareup.moshi:moshi-kotlin:${Version.moshi}"

        override val components: List<String> = listOf(
                okhttp, loggingInterceptor, retrofit,
                retrofitMoshi, moshi
        )
    }

    object DI {
        object Version {
            const val javaxInject: String = "1"
            const val daggerHiltAndroid: String = "2.29.1-alpha"
        }

        object AnnotationProcessor {
            const val daggerHiltAndroid: String =
                    "com.google.dagger:hilt-android-compiler:${Version.daggerHiltAndroid}"
        }

        const val javaxInject: String = "javax.inject:javax.inject:${Version.javaxInject}"
        const val daggerHiltAndroid: String =
                "com.google.dagger:hilt-android:${Version.daggerHiltAndroid}"
    }

    object Coroutines : Libraries {
        object Version {
            const val coroutines: String = "1.4.1"
        }

        const val core: String =
                "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.coroutines}"
        const val android: String =
                "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.coroutines}"

        override val components: List<String> = listOf(core, android)
    }

    object Test {
        object Version {
            const val junit: String = "4.13"
            const val runner: String = "1.1.0"
            const val rules: String = "1.3.0"
            const val testExt: String = "1.1.1"
            const val espresso: String = "3.2.0"
            const val fragment: String = "1.1.0-rc04"
            const val truth: String = "1.0.1"
            const val mockWebServer: String = "4.7.2"
            const val turbine: String = "0.3.0"
        }

        const val junit: String = "junit:junit:${Version.junit}"
        const val runner: String = "androidx.test:runner:${Version.runner}"
        const val fragmentTesting: String = "androidx.fragment:fragment-testing:${Version.fragment}"
        const val testExt: String = "androidx.test.ext:junit:${Version.testExt}"
        const val espresso: String = "androidx.test.espresso:espresso-core:${Version.espresso}"
        const val rules: String = "androidx.test:rules:${Version.rules}"
        const val truth: String = "com.google.truth:truth:${Version.truth}"
        const val mockWebServer: String =
                "com.squareup.okhttp3:mockwebserver:${Version.mockWebServer}"
        const val coroutinesTest: String =
                "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Coroutines.Version.coroutines}"

        const val turbine: String = "app.cash.turbine:turbine:${Version.turbine}"
    }
}

object ProjectLib {
    const val app: String = ":app"
    const val core: String = ":core"
    const val presentation: String = ":presentation"
    const val domain: String = ":libraries:domain"
    const val data: String = ":libraries:data"
    const val remote: String = ":libraries:remote"
    const val recipe: String = ":features:recipes:recipe"
    const val recipeDetail: String = ":features:recipes:recipeDetail"
    const val stepDetail: String = ":features:recipes:stepDetail"
    const val recipeModel: String = ":features:recipes:model"
    const val views: String = ":common:views"
    const val videoPlayer: String = ":features:videoPlayer"
    const val commonTest: String = ":common:common-test"
}
