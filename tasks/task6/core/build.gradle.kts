plugins {
    kotlin("multiplatform") version "2.4.21"
}

kotlin {
    explicitApi()

    jvm()
    iosSimulatorArm64()

    sourceSets {
        commonTest.dependencies {
            implementation(kotlin("test"))
        }
    }
}
