plugins {
    id("java-library")
    // Optional: Include Kotlin JVM if you want to use Coroutines here instead of Java Futures
    id("org.jetbrains.kotlin.jvm") 
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    // Networking for News and LLM API
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.9.0")
}
