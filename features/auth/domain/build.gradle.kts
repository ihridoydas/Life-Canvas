plugins {
    id("java-library")
    alias(libs.plugins.kotlin.jvm)
    id(libs.plugins.dokka.get().pluginId)
}

dependencies {
    implementation(projects.features.auth.data)
    implementation(projects.network)
    implementation(libs.javax.inject)
}
