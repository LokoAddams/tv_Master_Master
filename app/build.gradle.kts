import org.gradle.internal.declarativedsl.project.projectEvaluationSchema

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)

    //For Hilt
    alias(libs.plugins.hilt) // ✅ Plugin de Hilt
    alias(libs.plugins.kapt)

}

android {
    namespace = "com.example.tvmaster"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.tvmaster"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation.runtime.android)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation (libs.androidx.navigation.compose)
    implementation(project(":usecases"))
    implementation(project(":framework"))
    implementation(project(":domain"))
    implementation(project(":data"))

    implementation(libs.material3) // Usa la última versión disponible

    implementation(libs.androidx.material.icons.extended)


    implementation(libs.connect.sdk.android){
        exclude(group = "com.android.support")
        exclude(group = "androidx.appcompat")
        exclude(group = "androidx.core")
        exclude(group = "com.google.android.material")
    }

    //for hilt
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.hilt.android) // Usa la última versión disponible
    kapt(libs.hilt.compiler)

    implementation("androidx.compose.animation:animation-graphics:1.6.0")
    implementation("androidx.compose.ui:ui-text-google-fonts:1.8.1")
}

kapt {
    correctErrorTypes = true
}