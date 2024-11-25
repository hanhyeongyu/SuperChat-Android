plugins {
    alias(libs.plugins.superchat.android.feature)
    alias(libs.plugins.superchat.android.library.compose)
    alias(libs.plugins.superchat.hilt)
}

android{
    namespace = "com.example.superchat.auth"
}

dependencies{
    implementation(projects.core.common)
    implementation(projects.core.network)
    implementation(projects.core.auth)
    implementation(projects.core.datastore)
}