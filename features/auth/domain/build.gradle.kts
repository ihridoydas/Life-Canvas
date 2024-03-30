plugins {
    id("java-library")
    alias(libs.plugins.kotlin.jvm)
    id(libs.plugins.dokka.get().pluginId)
    alias(libs.plugins.kotlin.serialization)
}

dependencies {
    implementation(projects.features.auth.data)
    implementation(projects.network)
    implementation(libs.ktor.serialization)
    implementation(libs.javax.inject)
}
