plugins {
    id("com.android.dynamic-feature")
    id("org.jetbrains.kotlin.android")
}
apply {
    from("../shared_dependencies.gradle")
}
android {
    namespace = "com.tegar.sedekah.favorite"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
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
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures{
        viewBinding=true
    }
}

dependencies {
    implementation(project(":core"))
    implementation(project(":app"))

}