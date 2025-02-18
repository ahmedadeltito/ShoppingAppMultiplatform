# ✨ ShoppingAppMultiplatform

<img src="https://github.com/ahmedadeltito/ShoppingAppMultiplatform/blob/main/screenshots/android-screenshot.png" alt="android-screenshot" width="400"/>   <img src="https://github.com/ahmedadeltito/ShoppingAppMultiplatform/blob/main/screenshots/ios-screenshot.png" alt="ios-screenshot" width="400"/>
<img src="https://github.com/ahmedadeltito/ShoppingAppMultiplatform/blob/main/screenshots/dekstop-screenshot.png" alt="dekstop-screenshot" width="400"/>

🚀 **A Proof of Concept (PoC) for Kotlin Multiplatform (KMP) and Compose Multiplatform (CMP)**

---

## 📌 What is Kotlin Multiplatform (KMP) and Compose Multiplatform (CMP)?
Kotlin Multiplatform (KMP) allows **sharing business logic** across multiple platforms (Android, iOS, Desktop, Web) while keeping platform-specific flexibility.  
Compose Multiplatform (CMP) extends **Jetpack Compose** beyond Android, enabling **declarative UI** across multiple platforms with a **single codebase**.

With KMP & CMP, we achieve:

✅ **Code reusability** (shared logic & UI)  
✅ **Native performance** (no JS bridges)  
✅ **Maintainability** (single source of truth)  

---

## 🛍️ What is ShoppingAppMultiplatform?
**ShoppingAppMultiplatform** is a **Proof of Concept (PoC) app** demonstrating how to build a **scalable, cross-platform mobile application** using **KMP & CMP**.

### 💡 Why is this a great PoC for KMP & CMP?
- Runs seamlessly on **Android, iOS, and Desktop** 🌟  
- Implements **modern mobile architecture (Clean Architecture + UDF)**  
- Uses **shared business logic** while keeping platform-specific integrations  
- Demonstrates **expect/actual pattern** for platform-specific features  

---

## 🛠 Project Structure

The project is divided into the following modules:

### 📂 Root Modules:
- **`androidApp`** → Android-specific entry point (uses Compose UI).
- **`iosApp`** → iOS-specific entry point (integrates with SwiftUI).
- **`desktopApp`** → Desktop-specific entry point (uses Compose UI).
- **`composeApp`** → The shared **UI layer** built with Compose Multiplatform (CMP).

---

### 🔍 Exploring `composeApp`

The `composeApp` module contains the **shared UI and business logic** for **Android, iOS, and Desktop.**

#### 📌 Inside `composeApp` Submodules:

| **Module**      | **Purpose** |
|----------------|------------|
| `androidMain`  | Android-specific UI & integrations |
| `iosMain`      | iOS-specific UI & SwiftUI integration |
| `desktopMain`  | Desktop-specific UI & configurations |
| `commonMain`   | **The heart of the app – shared business logic, UI, and architecture!** |

---

## 🛡 Inside `commonMain` – The Core Architecture

We follow a **simplified Clean Architecture** with **Unidirectional Data Flow (UDF)**:

📌 **Layers Used:**
- **🔹 Data Layer:** Repository pattern to fetch data.  
- **🔹 Presentation Layer:** Uses **UDF (Unidirectional Data Flow)** for state management.  
- **❌ No Domain Layer (for simplicity)**  

📌 **Why UDF?**
- Makes **state management predictable**  
- **Decouples business logic from UI**  
- Enables **easier testing**  

---

## ⚡ Expect/Actual Pattern in Action!

KMP allows writing **shared logic**, but platform-specific features need **actual implementations.**  
This is where the **`expect/actual` mechanism** comes in! 🚀  

### 🔍 Examples in This Project
| **Feature** | **expect (Shared in commonMain)** | **actual (Platform-Specific Implementation)** |
|------------|--------------------------------|--------------------------------|
| **Dispatchers** | `expect val shoppingAppDispatchers: ShoppingAppDispatcher` | `actual fun shoppingAppDispatchers()` → Uses `Dispatchers.IO` on Android, `Dispatchers.Default` on iOS |
| **Networking (HTTP Factory)** | `expect fun createPlatformHttpClient(): HttpClient` | `actual fun createPlatformHttpClient()` → Uses `OkHttp` for Android, `Darwin` for iOS |

🔍 **This approach allows seamless platform-specific integrations while keeping most of the logic shared!**  

---

## 📦 Dependencies Used in `composeApp`

### **UI & Components**
- **Material3:** `androidx.compose.material3:material3`
- **Image Loader:** `io.github.qdsfdhvh:image-loader`
- **Compose Foundation:** `androidx.compose.foundation:foundation-android`

### **Coroutines & Concurrency**
- **Coroutines Core:** `org.jetbrains.kotlinx:kotlinx-coroutines-core`
- **Coroutines Swing:** `org.jetbrains.kotlinx:kotlinx-coroutines-swing`

### **Navigation & Lifecycle**
- **ViewModel for Compose:** `org.jetbrains.androidx.lifecycle:lifecycle-viewmodel-compose`
- **Navigation for Compose:** `org.jetbrains.androidx.navigation:navigation-compose`

### **Koin for Dependency Injection**
- **Koin Core, Android, and Compose:** `io.insert-koin:koin-core`, `koin-android`, `koin-androidx-compose`, `koin-compose`, `koin-compose-viewmodel`

### **Serialization & Networking**
- **Kotlinx Serialization:** `org.jetbrains.kotlinx:kotlinx-serialization-json`
- **Ktor Client:** `io.ktor:ktor-client-core`, `ktor-client-android`, `ktor-client-logging`, `ktor-client-content-negotiation`, `ktor-serialization-kotlinx-json`, `ktor-client-darwin`, `ktor-client-java`

---

## 🚀 How to Run the App?

### 📱 Android
1. Open the project in **Android Studio**  
2. Run `androidApp`  

### 🍏 iOS
1. Open `iosApp` in **Xcode**  
2. Run on a **simulator or real device**  

### 💻 Desktop
1. Run `./gradlew run` on **IntelliJ IDEA**  

---

## 🌍 Future Enhancements
✅ Adding **Jetpack DataStore & SharedPreferences** for local storage  
✅ Extending **offline-first architecture**  
✅ Implementing **push notifications**  

---

## 🙌 Contributing & Feedback
Feel free to **open an issue, suggest improvements, or contribute!**  
Drop a ⭐ if you find this project useful!  

