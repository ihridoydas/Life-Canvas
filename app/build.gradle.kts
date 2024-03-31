import java.net.InetAddress
import java.util.Properties

plugins {
    id(libs.plugins.android.application.get().pluginId)
    id(libs.plugins.ksp.get().pluginId)
    id(libs.plugins.kotlin.android.get().pluginId)
    id(libs.plugins.kotlinter.get().pluginId)
    alias(libs.plugins.paparazzi) apply false
    alias(libs.plugins.hilt)
    id(libs.plugins.sortDependencies.get().pluginId)
    id(libs.plugins.dokka.get().pluginId)
    id(libs.plugins.protobuf.get().pluginId)
}

android {
    compileSdk = libs.versions.compileSdk.get().toInt()
    namespace = "info.hridoydas.lifecanvas"

    defaultConfig {
        applicationId = "info.hridoydas.lifecanvas"
        versionCode = 1
        versionName = "1.0"
    }
    //Build Variant
    applicationVariants.configureEach {
        val appLabelMap = when (this.buildType.name) {
            "debug" -> mapOf("develop" to "${rootProject.name} devDebug",
            "staging" to "${rootProject.name} stgDebug",
            "production" to "${rootProject.name} proDebug")
            else -> mapOf("develop" to "${rootProject.name} Develop",
                "staging" to "${rootProject.name} Staging",
                "production" to rootProject.name)
        }
        val flavor = this.productFlavors[0]
        this.mergedFlavor.manifestPlaceholders["appLabel"] = "${appLabelMap[flavor.name]}"
    }

    /*
    //Set in your local.properties file for signing Configs
    //Path Location your keystore
    STORE_FILE = /Users/~/app/jks/template.keystore
    KEY_ALIAS = app_alias
    STORE_PASSWORD = template
    KEY_PASSWORD = template
    */

    /*signingConfigs {
        create("develop") {
            val keystoreProperties = Properties().apply{
                load(File("local.properties").reader())
            }
            keyAlias = keystoreProperties.getProperty("KEY_ALIAS")
            keyPassword = keystoreProperties.getProperty("KEY_PASSWORD")
            storeFile = File(keystoreProperties.getProperty("STORE_FILE"))
            storePassword = keystoreProperties.getProperty("STORE_PASSWORD")
        }
        create("staging") {
            val keystoreProperties = Properties().apply{
                load(File("local.properties").reader())
            }
            keyAlias = keystoreProperties.getProperty("KEY_ALIAS")
            keyPassword = keystoreProperties.getProperty("KEY_PASSWORD")
            storeFile = File(keystoreProperties.getProperty("STORE_FILE"))
            storePassword = keystoreProperties.getProperty("STORE_PASSWORD")
        }
        create("production") {
            val keystoreProperties = Properties().apply{
                load(File("local.properties").reader())
            }
            keyAlias = keystoreProperties.getProperty("KEY_ALIAS")
            keyPassword = keystoreProperties.getProperty("KEY_PASSWORD")
            storeFile = File(keystoreProperties.getProperty("STORE_FILE"))
            storePassword = keystoreProperties.getProperty("STORE_PASSWORD")
        }
    }*/

    // Specifies one flavor dimension.
    flavorDimensions += "version"
    productFlavors {
        create("develop") {
            dimension = "version"
            applicationIdSuffix = ".develop"
            versionNameSuffix = "-develop"
           // signingConfig = signingConfigs.getByName("develop")
        }
        create("staging") {
            initWith(getByName("staging"))
            dimension = "version"
            applicationIdSuffix = ".staging"
            versionNameSuffix = "-staging"
           // signingConfig = signingConfigs.getByName("staging")
        }
        create("production") {
            dimension = "version"
            //signingConfig = signingConfigs.getByName("production")
        }
    }
    buildTypes {
        val ipAddress = InetAddress.getLocalHost().hostAddress
        getByName("debug") {
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-debug"
            isDebuggable = true
            buildConfigField("String", "LifeCanvas_HOST", "\"192.168.1.8\"")
        }
        getByName("release") {
            isMinifyEnabled = true
            isDebuggable = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
            buildConfigField("String", "LifeCanvas_HOST", "\"not given\"")
        }
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {
    ksp(libs.androidx.room.compiler)
    ksp(libs.hilt.compiler)
    ksp(libs.square.moshi.kotlin.codegen)

    kspAndroidTest(libs.hilt.compiler)

    // Gradle
    implementation(platform(libs.compose.bom))
    // UI
    implementation(libs.accompanist.systemuicontroller)
    implementation(libs.android.material)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.appcompat)
    // Navigation
    implementation(libs.androidx.compose.navigation)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.androidx.hilt.compose.navigation)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.runtime.compose)
    // Network and Local
    implementation(libs.androidx.room.runtime)
    implementation(libs.compose.material3)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling)
    // Storage
    implementation(libs.datastore)
    // Hilt
    implementation(libs.hilt)
    implementation(libs.ktor.client.core)
    implementation(libs.protobuf.javaLite)
    implementation(libs.protobuf.kotlinLite)
    implementation(libs.square.moshi.kotlin)
    implementation(libs.square.retrofit)
    implementation(libs.square.retrofit.converter.moshi)
    implementation(libs.timber)
    //Module
    implementation(projects.common)
    implementation(projects.features.auth)
    implementation(projects.features.auth.data)
    implementation(projects.features.auth.domain)
    implementation(projects.navigation)
    implementation(projects.network)
    implementation(projects.storage)
    implementation(projects.theme)

    // Test
    debugImplementation(platform(libs.compose.bom))
    debugImplementation(libs.compose.ui.test.manifest)
    debugImplementation(libs.compose.ui.tooling)
    // Others
    debugImplementation(libs.square.leakcanary)

    testImplementation(libs.junit)

    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.androidx.test.espresso.core)
    androidTestImplementation(libs.androidx.test.junit)
    androidTestImplementation(libs.compose.ui.test.junit)
}

protobuf {
    protoc {
        artifact = libs.protobuf.protoc.get().toString()
    }
    generateProtoTasks {
        all().forEach { task ->
            task.builtins {
                create("kotlin") {
                    option("lite")
                }
            }
            task.builtins {
                create("java") {
                    option("lite")
                }
            }
        }
    }
}

