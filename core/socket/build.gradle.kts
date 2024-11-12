plugins{
    alias(libs.plugins.superchat.android.library)
    alias(libs.plugins.superchat.hilt)
}

android{
    namespace = "com.example.superchat.core.socket"
}

dependencies{
    implementation(projects.core.common)

    implementation(platform(libs.okhttp.bom))
    implementation(libs.okhttp)
}

