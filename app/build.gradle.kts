import com.example.superchat.SuperChatBuildType


plugins {
    alias(libs.plugins.superchat.android.application)
    alias(libs.plugins.superchat.android.application.compose)
    alias(libs.plugins.superchat.android.application.firebase)
    alias(libs.plugins.superchat.android.application.flavors)

    alias(libs.plugins.superchat.hilt)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.example.superchat"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.superchat"
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug{
            applicationIdSuffix = SuperChatBuildType.DEBUG.applicationIdSuffix
        }

        release {
            isMinifyEnabled = true
            applicationIdSuffix = SuperChatBuildType.RELEASE.applicationIdSuffix


            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {
    implementation(projects.core.common)
    implementation(projects.core.log)
    implementation(projects.core.network)
    implementation(projects.core.composeutils)
    implementation(projects.core.database)
    implementation(projects.core.datastore)
    implementation(projects.core.datastoreProto)
    implementation(projects.core.notification)
    implementation(projects.core.socket)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)

    ksp(libs.hilt.compiler)
    kspTest(libs.hilt.compiler)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    testImplementation(libs.hilt.android.testing)


    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}