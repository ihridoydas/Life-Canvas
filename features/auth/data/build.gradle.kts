plugins {
    id("java-library")
}

dependencies {
    implementation(projects.network)
    implementation(libs.ktor.serialization)
    implementation(libs.javax.inject)
}
