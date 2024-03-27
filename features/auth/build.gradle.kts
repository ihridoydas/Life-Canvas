plugins {
    id(libs.plugins.androidLibrary.get().pluginId)
    id(libs.plugins.kotlin.android.get().pluginId)
    id(libs.plugins.dokka.get().pluginId)
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.ksp) apply false
    kotlin("kapt")
}

android {
    namespace = "info.hridoydas.lifecanvas.features.auth"

    buildFeatures {
        compose = true
    }
}

dependencies {
    //Module
    implementation(projects.common)
    implementation(projects.navigation)
    implementation(projects.storage)
    implementation(projects.theme)
    // Gradle
    implementation(platform(libs.compose.bom))
    // UI
    implementation(libs.accompanist.systemuicontroller)
    implementation(libs.android.material)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.appcompat)
    implementation(libs.compose.material3)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.core.ktx)

    implementation(libs.androidx.hilt.compose.navigation)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.lifecycle.runtime)
    implementation(projects.features.auth.domain)
    implementation(projects.features.auth.data)

    // Test
    debugImplementation(platform(libs.compose.bom))
    debugImplementation(libs.compose.ui.test.manifest)
    debugImplementation(libs.compose.ui.tooling)
    // Others
    debugImplementation(libs.square.leakcanary)

    annotationProcessor(libs.androidx.room.compiler)
    // Hilt
    implementation(libs.hilt)
    kapt(libs.hilt.compiler)

    testImplementation(libs.junit)

    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.androidx.test.espresso.core)
    androidTestImplementation(libs.androidx.test.junit)
    androidTestImplementation(libs.compose.ui.test.junit)

}
