plugins{
    alias(libs.plugins.superchat.android.library)
    alias(libs.plugins.superchat.android.library.compose)
    alias(libs.plugins.superchat.hilt)
}

android{
    namespace = "com.example.superchat.core.log"

    buildFeatures {
        buildConfig = true
    }
}


dependencies{
    implementation(libs.androidx.compose.runtime)
    api(libs.timber)

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.crashlytics)
}
