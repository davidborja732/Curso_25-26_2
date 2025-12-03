plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-parcelize")
    id("com.google.gms.google-services")
}

android {
    namespace = "org.practica_6.david"
    compileSdk = 36

    defaultConfig {
        applicationId = "org.practica_6.david"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
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
        viewBinding = true
    }
}

dependencies {

// Firebase
    implementation(platform("com.google.firebase:firebase-bom:34.5.0"))
    // Firebase Analytics
    implementation("com.google.firebase:firebase-analytics")
    // Firebase Auth con Email y contraseña
    implementation("com.google.firebase:firebase-auth")
    // Firebase Auth con Google
    implementation("androidx.credentials:credentials:1.3.0")
    implementation("androidx.credentials:credentials-play-services-auth:1.3.0")
    implementation("com.google.android.libraries.identity.googleid:googleid:1.1.1")
    // Firebase Cloud Messaging
    implementation("com.google.firebase:firebase-messaging")
    // Firebase Remote Config
    implementation("com.google.firebase:firebase-config")
    // Firebase Firestore
    implementation("com.google.firebase:firebase-firestore")


    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    // Picasso
    implementation(libs.picasso)

    // Corrutinas
    implementation(libs.kotlinx.coroutines.android)

    // DataStore
    implementation(libs.androidx.datastore.preferences)

    // SplashScreen
    implementation(libs.androidx.core.splashscreen)

    // Mapbox
    implementation(libs.android.ndk27)

    // AndroidX y Material
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.recyclerview)

    // Tests
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
