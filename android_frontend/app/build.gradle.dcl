androidApplication {
    // Declarative DSL: keep only supported constructs (namespace and dependencies are allowed)
    namespace = "org.example.app"

    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        // Core + Lifecycle (explicit stable versions)
        implementation("androidx.core:core-ktx:1.13.1")
        implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.7")
        implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.7")
        implementation("androidx.lifecycle:lifecycle-runtime-compose:2.8.7")

        // Compose + Activity + Navigation (explicit versions; no BOM for compatibility)
        implementation("androidx.activity:activity-compose:1.9.3")
        implementation("androidx.navigation:navigation-compose:2.8.3")

        // Compose UI and Material3 (pin versions explicitly)
        implementation("androidx.compose.ui:ui:1.7.4")
        implementation("androidx.compose.ui:ui-tooling-preview:1.7.4")
        implementation("androidx.compose.ui:ui-tooling:1.7.4")
        implementation("androidx.compose.material3:material3:1.3.0")
        implementation("androidx.compose.material:material-icons-extended:1.7.4")

        // DataStore
        implementation("androidx.datastore:datastore-preferences:1.1.1")
    }
}
