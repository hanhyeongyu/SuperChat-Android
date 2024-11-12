plugins{
    alias(libs.plugins.superchat.android.library)
    alias(libs.plugins.superchat.hilt)
}

android{
    defaultConfig {
        consumerProguardFiles("consumer-proguard-rules.pro")
    }

    namespace = "com.example.superchat.core.datastore"
}

dependencies{
    implementation(projects.core.common)
    implementation(projects.core.datastoreProto)

    implementation(libs.androidx.dataStore)
}