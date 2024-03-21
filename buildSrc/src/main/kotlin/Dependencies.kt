import org.gradle.api.JavaVersion

object Config {
    const val compileSdk = 33
    const val targetSdk = 33
    const val minSdk = 24
    val javaVersion = JavaVersion.VERSION_1_8
}

object Versions {
    const val gradle = "7.2.2"
    const val core_ktx = "1.9.0"
    const val app_compat = "1.6.1"
    const val google_android_material = "1.9.0"
    const val androidx_constraint_layout = "2.1.4"
    const val androidx_junit_test = "1.1.5"
    const val junit = "4.13.2"
    const val androidx_espresso_core = "3.5.1"
    const val retrofit2 = "2.9.0"
    const val okhttp3 = "4.4.1"
    const val google_maps = "18.2.0"
    const val firebase_bom = "32.2.0"
    const val mapstruct = "1.5.5.Final"
    const val mapstruct_procesor = "1.5.5.Final"
    const val nav_version = "2.3.5"
    const val activity_version = "1.5.1"
    const val fragment_version = "1.5.1"
    const val coroutines_version = "1.6.1"
    const val recyclerview = "1.2.1"
    const val viewmodel = "2.5.0"
    const val livedate = "2.5.0"
    const val glide = "4.9.0"
    const val google_services = "4.3.15"
    const val kotlin = "1.8.21"
    const val mockk = "1.12.4"
    const val core_testing = "2.1.0"
    const val firebase_crashlytics_gradle = "2.9.5"
    const val dagger = "2.48"
//    const val room = "2.5.1"
}

object Deps {
    const val androidx_core_ktx = "androidx.core:core-ktx:${Versions.core_ktx}"
    const val androidx_appcompat = "androidx.appcompat:appcompat:${Versions.app_compat}"
    const val androidx_constraint_layout = "androidx.constraintlayout:constraintlayout:${Versions.androidx_constraint_layout}"
    const val activity_ktx =  "androidx.activity:activity-ktx:${Versions.activity_version}"
    const val fragment_ktx = "androidx.fragment:fragment-ktx:${Versions.fragment_version}"
    const val recyclerview = "androidx.recyclerview:recyclerview:${Versions.recyclerview}"
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val dagger= "com.google.dagger:hilt-android:${Versions.dagger}"
    const val dagger_compiler = "com.google.dagger:hilt-compiler:${Versions.dagger}"

    const val google_android_material = "com.google.android.material:material:${Versions.google_android_material}"
    const val google_maps = "com.google.android.gms:play-services-maps:${Versions.google_maps}"
    const val crashlytics = "com.google.firebase:firebase-crashlytics"
    const val firebase_bom = "com.google.firebase:firebase-bom:${Versions.firebase_bom}"
    const val firebase_analytics = "com.google.firebase:firebase-analytics"

    const val retrofit2 = "com.squareup.retrofit2:retrofit:${Versions.retrofit2}"
    const val okhttp3 = "com.squareup.okhttp3:okhttp:${Versions.okhttp3}"
    const val retrofitGsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit2}"
    const val mapstruct = "org.mapstruct:mapstruct:${Versions.mapstruct}"
    const val mapstruct_procesor = "org.mapstruct:mapstruct-processor:${Versions.mapstruct_procesor}"

    const val navigation = ("androidx.navigation:navigation-fragment-ktx:${Versions.nav_version}")
    const val navigation_ui = ("androidx.navigation:navigation-ui-ktx:${Versions.nav_version}")

    const val viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.viewmodel}"
    const val livedate = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.livedate}"

    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines_version}"
    const val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines_version}"
    const val coroutinesPlayService = "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:${Versions.coroutines_version}"

    const val androidx_junit_test = "androidx.test.ext:junit:${Versions.androidx_junit_test}"
    const val androidx_espresso_core = "androidx.test.espresso:espresso-core:${Versions.androidx_espresso_core}"
    const val junit = "junit:junit:${Versions.junit}"
    const val mockk = "io.mockk:mockk:${Versions.mockk}"
    const val core_testing = "androidx.arch.core:core-testing:${Versions.core_testing}"

    const val core = ":core"
    const val conexanetwork = ":conexanetwork"
    const val conexamodel = ":conexamodel"
    const val conexarepositories = ":conexarepositories"
    const val conexacomponents = ":conexacomponents"
}