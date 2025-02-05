buildscript {
    val mpaas_baseline by extra("10.1.68-53")
    val mpaas_artifact by extra("mpaas-baseline")
    dependencies {
        classpath(libs.easyconfig)
    }
}
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
}