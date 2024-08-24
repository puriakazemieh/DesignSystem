@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    id("maven-publish")
}

android {
    namespace = "com.kazemieh.designsystem.libdesign"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        dataBinding = true
        viewBinding = true
    }
}


val coreAarFile = file("$buildDir/outputs/aar/libDesign-release.aar")

configurations.maybeCreate("default")
val sourcesJar by tasks.creating(Jar::class) {
    archiveClassifier="sources"
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            groupId = "com.kazemieh.designsystem.libdesign"
            artifactId = "system"
            version = "0.0.1"

            artifact(sourcesJar)
            artifact(coreAarFile)

            pom {
                withXml {
                    asNode().appendNode("dependencies").let {
                        for (dependency in configurations["api"].dependencies) {
                            it.appendNode("dependency").apply {
                                appendNode("groupId", dependency.group)
                                appendNode("artifactId", dependency.name)
                                appendNode("version", dependency.version)
                            }
                        }
                    }
                }
            }
        }
    }
}



dependencies {

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation(libs.spinKit)
}