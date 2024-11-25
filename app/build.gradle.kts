import com.example.superchat.SuperChatBuildType


plugins {
    alias(libs.plugins.superchat.android.application)
    alias(libs.plugins.superchat.android.application.compose)
    alias(libs.plugins.superchat.android.application.firebase)
    alias(libs.plugins.superchat.android.application.flavors)
    id("com.google.android.gms.oss-licenses-plugin")

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
    implementation(projects.core.auth)
    implementation(projects.core.ui)
    implementation(projects.core.database)
    implementation(projects.core.datastore)
    implementation(projects.core.datastoreProto)
    implementation(projects.core.designsystem)
    implementation(projects.core.notification)

    implementation(projects.user)
    implementation(projects.settings)
    implementation(projects.profile)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.material3.adaptive)
//    implementation(libs.androidx.compose.material3.adaptive.layout)
//    implementation(libs.androidx.compose.material3.adaptive.navigation)

    implementation(libs.androidx.navigation.compose)

    implementation(libs.androidx.core.splashscreen)


    ksp(libs.hilt.compiler)
    kspTest(libs.hilt.compiler)


    implementation(libs.kotlinx.datetime)
    implementation(libs.kotlinx.serialization.json)


    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    testImplementation(libs.hilt.android.testing)


    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}