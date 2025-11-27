androidLibrary {
    namespace = "org.gradle.experimental.android.utilities"

    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        api(project(":list"))
    }
}
