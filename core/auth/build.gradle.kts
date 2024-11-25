plugins {
    alias(libs.plugins.superchat.android.library)
    alias(libs.plugins.superchat.hilt)

    id("kotlinx-serialization")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
}


android {
    buildFeatures {
        buildConfig = true
    }
    namespace = "com.example.superchat.core.auth"
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

secrets {
    defaultPropertiesFileName = "secrets.defaults.properties"
}

dependencies {
    implementation(projects.core.common)
    implementation(projects.core.datastore)
    implementation(projects.core.preference)
    implementation(projects.core.network)



    implementation(libs.kotlinx.serialization.json)
    implementation(libs.kotlinx.datetime)

    implementation(libs.okhttp.logging)
    api(libs.retrofit.core)
    api(libs.retrofit.kotlin.serialization)


    implementation(libs.coil.kt)
    implementation(libs.coil.kt.svg)
}
