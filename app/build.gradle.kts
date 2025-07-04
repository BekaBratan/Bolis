import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    kotlin("plugin.serialization") version "2.0.21"
    id("androidx.navigation.safeargs.kotlin")
}

val localProperties = Properties().apply {
    val localFile = rootProject.file("local.properties")
    if (localFile.exists()) {
        load(localFile.inputStream())
    }
}

val togetherApiKey = localProperties.getProperty("TOGETHER_API_KEY") ?: "\"\""
val chatGptApiKey = localProperties.getProperty("CHAT_GPT_API_KEY") ?: "\"\""
val deepSeekApiKey = localProperties.getProperty("DEEP_SEEK_API_KEY") ?: "\"\""

android {
    namespace = "com.example.bolis"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.bolis"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            buildConfigField("String", "TOGETHER_API_KEY", "\"$togetherApiKey\"")
            buildConfigField("String", "CHAT_GPT_API_KEY", "\"$chatGptApiKey\"")
            buildConfigField("String", "DEEP_SEEK_API_KEY", "\"$deepSeekApiKey\"")
        }
        release {
            buildConfigField("String", "TOGETHER_API_KEY", "\"$togetherApiKey\"")
            buildConfigField("String", "CHAT_GPT_API_KEY", "\"$chatGptApiKey\"")
            buildConfigField("String", "DEEP_SEEK_API_KEY", "\"$deepSeekApiKey\"")
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.ui)
    implementation(libs.material3)
    implementation(libs.androidx.foundation)
    implementation(libs.androidx.foundation.layout)
    implementation(libs.accompanist.pager)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation("com.google.accompanist:accompanist-pager-indicators:0.30.1")
    implementation("com.google.accompanist:accompanist-permissions:0.37.3")

    val navVersion = "2.8.8"

    // Jetpack Compose integration
    implementation("androidx.navigation:navigation-compose:$navVersion")

    // Views/Fragments integration
    implementation("androidx.navigation:navigation-fragment:$navVersion")
    implementation("androidx.navigation:navigation-ui:$navVersion")

    // Feature module support for Fragments
    implementation("androidx.navigation:navigation-dynamic-features-fragment:$navVersion")

    // Testing Navigation
    androidTestImplementation("androidx.navigation:navigation-testing:$navVersion")

    // Gson
    implementation("com.google.code.gson:gson:2.11.0")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")
    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:1.0.0")
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

    // Glide
    implementation("com.github.bumptech.glide:glide:4.16.0")

    // Coil
    implementation("io.coil-kt.coil3:coil-compose:3.1.0")
    implementation("io.coil-kt.coil3:coil-network-okhttp:3.1.0")

    // CameraX
    implementation("androidx.camera:camera-camera2:1.0.2")
    implementation("androidx.camera:camera-lifecycle:1.0.2")
    implementation("androidx.camera:camera-view:1.0.0-alpha31")

    // Zxing
    implementation("com.google.zxing:core:3.3.3")

    // YandexMap
    implementation("com.yandex.android:maps.mobile:4.14.0-full")

    // GPS Location
    implementation("com.google.android.gms:play-services-location:21.0.1")

    //WebSocket
    implementation("org.java-websocket:Java-WebSocket:1.5.3")

    // ML Kit Barcode Scanning
    implementation ("com.google.mlkit:barcode-scanning:17.3.0")
    //camera
    implementation ("androidx.camera:camera-core:1.3.4")
    implementation ("androidx.camera:camera-camera2:1.3.4")
    implementation ("androidx.camera:camera-lifecycle:1.3.4")
    implementation ("androidx.camera:camera-view:1.3.4")

    //viewModel lifeCycle
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")


    //json Serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")
}

