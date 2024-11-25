plugins {
    alias(libs.plugins.superchat.android.feature)
    alias(libs.plugins.superchat.android.library.compose)
    alias(libs.plugins.superchat.hilt)
}

android{
    namespace = "com.example.superchat.profile"
}

dependencies{
    implementation(projects.core.common)
    implementation(projects.core.network)
    implementation(projects.core.log)
    implementation(projects.core.datastore)
}