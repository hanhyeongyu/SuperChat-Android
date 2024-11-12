plugins{
    alias(libs.plugins.superchat.android.library)
    alias(libs.plugins.superchat.room)
    alias(libs.plugins.superchat.hilt)
}

android{
    namespace = "com.example.superchat.core.database"
}


dependencies {
    implementation(libs.kotlinx.datetime)
}
