plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "org.example.app"
    compileSdk = 34

    defaultConfig {
        applicationId = "org.gradle.experimental.android.app"
        minSdk = 30
        targetSdk = 34
        versionCode = 1
        versionName = "0.1"
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
        debug {
            // No debug-only dependencies here; we keep deps under implementation to avoid DSL issues
        }
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.14"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

repositories {
    google()
    mavenCentral()
}

dependencies {
    // Core + Lifecycle
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.7")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.7")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.8.7")

    // Compose + Activity + Navigation
    implementation("androidx.activity:activity-compose:1.9.3")
    implementation("androidx.navigation:navigation-compose:2.8.3")

    // Compose UI and Material3 (explicit versions; no BOM)
    implementation("androidx.compose.ui:ui:1.7.4")
    implementation("androidx.compose.ui:ui-tooling-preview:1.7.4")
    debugImplementation("androidx.compose.ui:ui-tooling:1.7.4")
    // Material3 Compose
    implementation("androidx.compose.material3:material3:1.3.0")
    // Material3 AppCompat themes (needed for Theme.Material3.* in XML)
    implementation("androidx.compose.material3:material3-window-size-class:1.3.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("androidx.compose.material:material-icons-extended:1.7.4")

    // DataStore
    implementation("androidx.datastore:datastore-preferences:1.1.1")

    // Kotlin coroutines (compose runtime depends transitively, but add core if needed)
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.1")

    // Unit test (optional basic)
    testImplementation("junit:junit:4.13.2")
}
