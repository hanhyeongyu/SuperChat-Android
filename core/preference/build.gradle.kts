plugins{
    alias(libs.plugins.superchat.android.library)
    alias(libs.plugins.superchat.hilt)
    alias(libs.plugins.kotlin.serialization)

}

android{
    defaultConfig {
        consumerProguardFiles("consumer-proguard-rules.pro")
    }

    namespace = "com.example.superchat.core.preference"
}

dependencies{
    implementation(projects.core.common)
    implementation(libs.androidx.security.crypto)
    implementation(libs.kotlinx.serialization.json)
}