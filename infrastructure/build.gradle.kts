plugins {
    id("buildlogic.kotlin-application-conventions")
}

dependencies {
    implementation("software.amazon.awscdk:aws-cdk-lib:2.184.1")
}
application{
    mainClass = "AppKt"
}
