plugins {
    alias(libs.plugins.superchat.android.feature)
    alias(libs.plugins.superchat.android.library.compose)
    alias(libs.plugins.superchat.hilt)
}

android{
    namespace = "com.example.superchat.settings"
}

dependencies{
    implementation(projects.core.datastore)

    implementation(libs.androidx.appcompat)
    implementation(libs.google.oss.licenses)
}