plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    // 5 - Añadimos el plugin parzelize
    id("kotlin-parcelize")
}

android {
    namespace = "org.iesch.a03_menu_principal"
    compileSdk = 36

    defaultConfig {
        applicationId = "org.iesch.a03_menu_principal"
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

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.recyclerview)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}