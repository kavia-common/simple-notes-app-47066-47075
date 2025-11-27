androidApplication {
    namespace = "org.example.app"

    // Keep dependencies only; Declarative Android DSL currently does not support other configuration blocks here
    dependencies {
        // Core + Lifecycle
        implementation("androidx.core:core-ktx:1.13.1")
        implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.7")
        implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.7")
        implementation("androidx.lifecycle:lifecycle-runtime-compose:2.8.7")

        // Compose + Activity + Navigation
        implementation("androidx.activity:activity-compose:1.9.3")
        implementation("androidx.navigation:navigation-compose:2.8.3")

        // Compose UI and Material3 (pin versions explicitly; no BOM)
        implementation("androidx.compose.ui:ui:1.7.4")
        implementation("androidx.compose.ui:ui-tooling-preview:1.7.4")
        implementation("androidx.compose.ui:ui-tooling:1.7.4")
        implementation("androidx.compose.material3:material3:1.3.0")
        implementation("androidx.compose.material:material-icons-extended:1.7.4")

        // DataStore
        implementation("androidx.datastore:datastore-preferences:1.1.1")
    }
}
