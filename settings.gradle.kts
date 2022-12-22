rootProject.name = "ProtonPass"

fun getProtosUrl(): String {
    val isCI = System.getenv("CI").toBoolean()
    if (isCI) {
        val username = "gitlab-ci-token"
        val token = System.getenv("CI_JOB_TOKEN")
        return "https://${username}:${token}@gitlab.protontech.ch/proton/clients/pass/contents-proto-definition.git"
    } else {
        val username = "AndroidDeployToken"
        val token = "glpat-Poxe3LKtUJSqKXKgusac"
        return "https://${username}:${token}@gitlab.protontech.ch/proton/clients/pass/contents-proto-definition.git"
    }
}

pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

plugins {
    id("me.proton.core.gradle-plugins.include-core-build") version "1.1.1"
}

includeCoreBuild {
    branch.set("main")
    includeBuild("gopenpgp")

    includeRepo("contents-proto-definition") {
        uri.set(getProtosUrl())
        branch.set("master")
        checkoutDirectory.set(file("./pass/protos/contents-proto-definition"))
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include(":app")
include(":pass:app-config:api")
include(":pass:autofill:api")
include(":pass:autofill:demo")
include(":pass:autofill:fakes")
include(":pass:autofill:impl")
include(":pass:biometry:api")
include(":pass:biometry:fakes")
include(":pass:biometry:impl")
include(":pass:clipboard:api")
include(":pass:clipboard:fakes")
include(":pass:clipboard:impl")
include(":pass:common-ui:api")
include(":pass:common:api")
include(":pass:data:api")
include(":pass:data:fakes")
include(":pass:data:impl")
include(":pass:domain")
include(":pass:log")
include(":pass:navigation:api")
include(":pass:network:api")
include(":pass:network:impl")
include(":pass:notifications:api")
include(":pass:notifications:fakes")
include(":pass:notifications:impl")
include(":pass:preferences:api")
include(":pass:preferences:fakes")
include(":pass:preferences:impl")
include(":pass:presentation")
include(":pass:protos")
include(":pass:screenshot-tests")
include(":pass:test")

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
