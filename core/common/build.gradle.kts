plugins{
    alias(libs.plugins.superchat.jvm.library)
    alias(libs.plugins.superchat.hilt)
}

dependencies{
    implementation(libs.kotlinx.coroutines.core)
    testImplementation(libs.kotlinx.coroutines.test)
}