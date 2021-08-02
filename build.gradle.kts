plugins {
    java
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    testCompile("junit", "junit", "4.12")
    implementation("org.eclipse.paho:org.eclipse.paho.client.mqttv3:1.0.2")
    implementation("org.bouncycastle:bcprov-jdk15on:1.68")
    implementation("org.bouncycastle:bcpkix-jdk15on:1.68")
}