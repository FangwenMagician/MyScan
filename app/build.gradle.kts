plugins {
    alias(libs.plugins.android.application)
    id("com.alipay.apollo.baseline.config")
}

android {
    namespace = "top.fangwenmagician.myscan"
    compileSdk = 35

    defaultConfig {
        applicationId = "top.fangwenmagician.myscan"
        minSdk = 23
        targetSdk = 29
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
}

dependencies {

    implementation(platform("com.mpaas.android:${rootProject.ext["mpaas_artifact"]}:${rootProject.ext["mpaas_baseline"]}"))
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.scan)
    implementation(libs.antui)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}