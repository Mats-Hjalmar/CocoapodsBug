import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlinCocoapods)
}

kotlin {
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    cocoapods {
        ios.deploymentTarget = "15.2"
        framework {
            baseName = "CocoapodsBug"
        }
        noPodspec()
        pod("FirebaseCrashlytics") {
            version = "11.0.0"
            extraOpts += listOf("-compiler-option", "-fmodules")
        }
        pod("FirebaseAnalytics") {
            version = "11.0.0"
            extraOpts += listOf("-compiler-option", "-fmodules")
        }
        pod("FirebaseCore") {
            version = "11.0.0"
        }
    }
    
    sourceSets {
        
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtime.compose)
        }
    }
}


