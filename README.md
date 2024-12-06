# Android App Documentation

## Environment

- **JDK Version**: 21  
- **Kotlin Version**: 1.9.21 (Updated for compatibility with Android Studio Ladybug and Gradle 8.9 but not fully updated for compatibility with kapt)  
- **Target Android Version**: 15  
- **Used version Catalog for dependncy management**
- **Migrated build.gradle files from groovy to kotlin DSL**

---

## Libraries Used

1. **Coil (Image Loading)**  
   - **Description**: Coil is an image loading library for Android that’s built on Kotlin and Coroutines.  
   - **Benefits**:  
     - Lightweight and fast, ensuring efficient resource usage.  
     - Natively supports modern Android features like Jetpack Compose and ImageView.  
     - Provides seamless image loading with automatic caching and memory management.  
     - Offers extensibility with custom transformations and additional image processing capabilities.  

2. **Dagger Hilt (Dependency Injection)**  
   - **Description**: Dagger Hilt simplifies dependency injection in Android applications.  
   - **Benefits**:  
     - Reduces boilerplate code by automating common DI tasks.  
     - Offers built-in integration with Android components like Activities, Fragments, and ViewModels.  
     - Improves code scalability and testability.  
     - Ensures runtime safety with compile-time dependency graph validation.  

3. **Lottie (Animations)**  
   - **Description**: Lottie is a library for rendering animations created in Adobe After Effects as JSON files.  
   - **Benefits**:  
     - Enables the use of high-quality vector animations with minimal impact on app performance.  
     - Provides a smooth and modern user experience.   
     - Easy to integrate and customize within the app.  

4. **Timber (Logging)**  
   - **Description**: Timber is a lightweight and extensible logging library for Android.  
   - **Benefits**:  
     - Simplifies logging by reducing boilerplate code compared to Android's default `Log` class.  
     - Allows for easy integration of custom logging behavior (e.g., logging to a file or remote server).  
     - Automatically detects when logging is being called from the wrong thread.  
     - Can be disabled or configured differently for release builds to prevent sensitive log output.  

5. **Jetpack Navigation**  
   - **Description**: Jetpack Navigation simplifies the navigation between different screens (fragments, activities) within an Android app.  
   - **Benefits**:  
     - Provides a clear, consistent, and easy-to-use navigation model.  
     - Reduces boilerplate code by automating fragment transactions and back stack management.  
     - Integrates with ViewModel and LiveData for handling navigation state changes.  
     - Allows for deep linking, safe args, and simpler navigation between fragments and activities.

6. **Some additional libraries eg de.hdodenhof:circleimageview, com.github.Dimezis:BlurView, okhttp and retrofit**

---

## Pending Tasks

- **Splash Screen**:  
  The splash screen was not implemented beyond the default recommendation of a simple app icon centered on a colored background.  
  - **Reason**: Currently per the developer documentation the splash screen is standard as an app icon on screen with a colored background (android 12 and above), there may be alternative ways of achieving a full screen image without a duplicate splash screen, however i wasn't able to achieve this in time.

---

## Future Considerations (If Additional Time Was Available)

1. **Custom Dialogs**:  
   - Enhance the user experience by further customizing dialogs and incorporating different Lottie animations for success, failure, and other states.

2. **Migration to KSP**:  
   - Replace `kapt` with `ksp` to improve build performance and compatibility with modern libraries.

3. **Rewrite in Jetpack Compose**:  
   - Transition the app's UI framework to Jetpack Compose for better maintainability and modern design patterns.

4. **Enhanced Navigation Handling**:  
   - Implement additional features of Jetpack Navigation like conditional navigation, shared element transitions, and deep linking for a more fluid user experience.

5. **Fully migrate to the latest kotlin version**:  

6. **Fully migrate to material3 and fix all theming issues**:

7. **Write some UI tests with espresso**:

---

