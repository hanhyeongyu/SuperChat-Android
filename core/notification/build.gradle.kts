plugins {
    alias(libs.plugins.superchat.android.library)
    alias(libs.plugins.superchat.hilt)
}

android{
    namespace = "com.example.superchat.core.notification"
}

dependencies{
    implementation(projects.core.common)

    compileOnly(platform(libs.androidx.compose.bom))
}