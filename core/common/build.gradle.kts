plugins{
    alias(libs.plugins.superchat.jvm.library)
    alias(libs.plugins.superchat.hilt)
    alias(libs.plugins.kotlin.serialization)
}

dependencies{
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.serialization.json)
    testImplementation(libs.kotlinx.coroutines.test)
}