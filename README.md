# WeatherApp 🌦️

A simple weather application built with Jetpack Compose, Hilt, Retrofit, and DataStore for seamless dependency injection, API integration, and data persistence.

---

## Features

- Fetch weather information for any city using the [WeatherAPI](https://www.weatherapi.com/).
- Modern Android development with **Jetpack Compose** for UI, **Hilt** for DI, and **Retrofit** for API calls.
- Uses **DataStore** for local data persistence.
- Clean architecture with separation of concerns.

---

## Prerequisites

Make sure you have the following installed:

1. **Android Studio Flamingo or higher**
    - Minimum version: `Android Studio Flamingo | 2023.2.1`
2. **JDK 11** (or higher)
3. **Gradle version 8.0+**
4. An API key from [WeatherAPI](https://www.weatherapi.com/).

---

## Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/your-repo/weatherapp.git
cd weatherapp
```

### 2. Configure the API Key

1. Obtain your free API key from [WeatherAPI](https://www.weatherapi.com/).
2. Open the `app/build.gradle.kts` file.
3. Replace the placeholder API key in the `buildConfigField` definition:

   ```kotlin
   buildConfigField("String", "WEATHER_API_KEY", "\"your_api_key_here\"")
   ```

### 3. Set the Application Class in the Manifest

Ensure that the `android:name` attribute in the `AndroidManifest.xml` points to the `WeatherApplication` class:

```bash
<application
    android:name=".WeatherApplication"
    ... >
</application>
```

### 4. Build and Run

1. Open the project in Android Studio.
2. Sync the Gradle files: **File > Sync Project with Gradle Files**.
3. Select a device or emulator to run the app.
4. Click **Run** or use the shortcut `Shift + F10`.

---

## Project Structure

- **`MainActivity`**: Entry point of the app, annotated with `@AndroidEntryPoint`.
- **`WeatherApplication`**: Custom Application class with `@HiltAndroidApp`.
- **`AppModule`**: Hilt module providing dependencies like `WeatherApiService` and `DataStore`.
- **`HomeViewModel`**: ViewModel managing weather data.
- **`HomeScreen`**: Composable UI screen displaying weather details.
- **`WeatherApiService`**: Retrofit interface for API calls.
- **`WeatherRepository`**: Repository for managing API and local data operations.

---

## Tech Stack

- **Kotlin**: 100% Kotlin-based code.
- **Jetpack Compose**: Modern, declarative UI toolkit.
- **Hilt**: Dependency Injection framework.
- **Retrofit**: HTTP client for API calls.
- **DataStore**: SharedPreferences replacement for data persistence.
- **Material3**: Modern material design UI components.

---

## Troubleshooting

### Common Issues

1. **Hilt-related crashes:**
    - Ensure the `WeatherApplication` class is specified in the `AndroidManifest.xml`.
    - Ensure all `@HiltAndroidApp` and `@AndroidEntryPoint` annotations are correctly added.

2. **Kotlin/Compose compatibility issues:**
    - Verify Kotlin and Compose versions in your `build.gradle.kts`.
    - Check the compatibility map: [Compose-Kotlin Compatibility Map](https://developer.android.com/jetpack/androidx/releases/compose-kotlin).

3. **DataStore file extension mismatch:**
    - Ensure the preferences file has the `.preferences_pb` extension.

### Need More Help?

Feel free to open an issue in this repository if you encounter problems.

---

## License

This project is licensed under the MIT License. See the `LICENSE` file for details.

---

## Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository.
2. Create a new branch: `git checkout -b feature-name`.
3. Commit your changes: `git commit -m "Add new feature"`.
4. Push the branch: `git push origin feature-name`.
5. Open a Pull Request.

---

## Acknowledgments

- [WeatherAPI](https://www.weatherapi.com/) for the free weather API.
- Jetpack Compose, Hilt, and Retrofit teams for providing awesome libraries.






