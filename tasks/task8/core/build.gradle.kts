import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask

plugins {
    kotlin("multiplatform") version "2.4.21"
}

kotlin {
    jvm()
    iosSimulatorArm64()

    sourceSets {
        commonTest.dependencies {
            implementation(kotlin("test"))
        }
    }
}

// Keep strict explicit API checking scoped to the JVM main compilation.
tasks.withType<KotlinCompilationTask<*>>().configureEach {
    if (name == "compileKotlinJvm") {
        compilerOptions {
            freeCompilerArgs.add("-Xexplicit-api=strict")
        }
    }
}
