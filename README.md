**Maven repository**

Accessing the Yuvital Life SDK requires credential configuration. Please don't share these credentials in version control.

We recommend adding your credentials to the **`gradle.properties`** file.

```kotlin
# Add this to ~/.gradle/gradle.properties on your local machine, or put it in your CI's secrets store
yuvitalLifeSdkPassword=YOUR_PASSWORD
```

**Add maven to your app's dependency resolution in `settings.gradle.kts`:**

```kotlin
 dependencyResolutionManagement {
     repositories {
        // Other repositories you use:
        google()
        mavenCentral()

        // Add JitPack
        maven(url = "https://jitpack.io")

        // Yuvital Life SDK (Cloudsmith)
        maven {
            url = uri("https://dl.cloudsmith.io/basic/yuvital/yuvital-life-sdk-production/maven/")
            credentials {
              username = "token"
              password = providers.gradleProperty("yuvitalLifeSdkPassword").get()
            }
        }
     }
 }
```

