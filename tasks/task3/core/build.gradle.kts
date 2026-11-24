plugins {
    kotlin("multiplatform") version "2.4.21"
}

kotlin {
    jvm()
    iosSimulatorArm64()

    sourceSets {
        commonMain.dependencies {
            implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.7.1")
        }
        commonTest.dependencies {
            implementation(kotlin("test"))
        }
    }
}
