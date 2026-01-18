# Universal Converter

A modern Android unit converter application built with Kotlin and Jetpack Compose, following Clean Architecture principles and MVVM pattern.

## Features

- **Length Conversion**: Convert between 8 different units
  - Metric: Millimeter, Centimeter, Meter, Kilometer
  - Imperial: Inch, Foot, Yard, Mile
- **Real-time Conversion**: Results update instantly as you type
- **Swap Units**: Quickly swap between source and target units
- **Material Design 3**: Modern UI with Material You theming
- **Clean Architecture**: Maintainable and testable codebase

## Screenshots

| Home Screen | Converter Screen |
|-------------|------------------|
| Category selection | Unit conversion interface |

## Tech Stack

| Technology | Version | Purpose |
|------------|---------|---------|
| Kotlin | 2.0.0 | Programming language |
| Jetpack Compose | BOM 2024.12.01 | Modern UI toolkit |
| Material 3 | Latest | Design system |
| Hilt | 2.54 | Dependency injection |
| Navigation Compose | 2.8.5 | Screen navigation |
| Lifecycle ViewModel | 2.8.7 | State management |
| KSP | 2.0.0-1.0.24 | Kotlin Symbol Processing |

### Testing

| Library | Purpose |
|---------|---------|
| JUnit 4 | Unit testing framework |
| MockK | Kotlin mocking library |
| Turbine | Flow testing |
| Espresso | Android UI testing |
| Compose Testing | Compose UI tests |

## Architecture

The app follows **Clean Architecture** with three main layers:

```
┌─────────────────────────────────────────────────────────┐
│                    Presentation Layer                    │
│  ┌─────────────┐  ┌─────────────┐  ┌─────────────────┐  │
│  │   Screens   │  │  ViewModels │  │   Components    │  │
│  └─────────────┘  └─────────────┘  └─────────────────┘  │
├─────────────────────────────────────────────────────────┤
│                      Domain Layer                        │
│  ┌─────────────┐  ┌─────────────┐  ┌─────────────────┐  │
│  │   Models    │  │  Use Cases  │  │   Repository    │  │
│  │             │  │             │  │   (Interface)   │  │
│  └─────────────┘  └─────────────┘  └─────────────────┘  │
├─────────────────────────────────────────────────────────┤
│                       Data Layer                         │
│  ┌─────────────────────────────────────────────────┐    │
│  │              Repository Implementation           │    │
│  └─────────────────────────────────────────────────┘    │
└─────────────────────────────────────────────────────────┘
```

## Project Structure

```
app/src/main/java/com/example/converter/
├── data/
│   └── repository/
│       └── ConversionRepositoryImpl.kt    # Repository implementation
├── di/
│   └── AppModule.kt                       # Hilt dependency injection
├── domain/
│   ├── model/
│   │   ├── ConversionResult.kt            # Conversion result model
│   │   ├── ConversionUnit.kt              # Unit model
│   │   └── ConverterCategory.kt           # Category enum
│   ├── repository/
│   │   └── ConversionRepository.kt        # Repository interface
│   └── usecase/
│       ├── ConvertValueUseCase.kt         # Conversion logic
│       └── GetUnitsForCategoryUseCase.kt  # Get units for category
├── presentation/
│   ├── components/
│   │   ├── CategoryCard.kt                # Category selection card
│   │   ├── ConversionInputField.kt        # Numeric input field
│   │   ├── SwapButton.kt                  # Swap units button
│   │   └── UnitDropdown.kt                # Unit selection dropdown
│   ├── converter/
│   │   ├── ConverterScreen.kt             # Converter UI
│   │   ├── ConverterUiState.kt            # Converter state
│   │   └── ConverterViewModel.kt          # Converter logic
│   ├── home/
│   │   ├── HomeScreen.kt                  # Home UI
│   │   ├── HomeUiState.kt                 # Home state
│   │   └── HomeViewModel.kt               # Home logic
│   ├── navigation/
│   │   └── ConverterNavGraph.kt           # Navigation setup
│   └── theme/
│       ├── Color.kt                       # Color definitions
│       ├── Theme.kt                       # App theme
│       └── Type.kt                        # Typography
├── ConverterApplication.kt                # Application class
└── MainActivity.kt                        # Main activity
```

## Conversion Factors

All length conversions use **Meter** as the base unit:

| Unit | Symbol | To Base Factor |
|------|--------|----------------|
| Millimeter | mm | 0.001 |
| Centimeter | cm | 0.01 |
| Meter | m | 1.0 |
| Kilometer | km | 1000.0 |
| Inch | in | 0.0254 |
| Foot | ft | 0.3048 |
| Yard | yd | 0.9144 |
| Mile | mi | 1609.344 |

## Getting Started

### Prerequisites

- Android Studio Hedgehog (2023.1.1) or newer
- JDK 17 or higher
- Android SDK 36

### Build & Run

1. Clone the repository:
   ```bash
   git clone https://github.com/ibtehaaj-turing/universal-converter.git
   cd universal-converter
   ```

2. Open in Android Studio or build from command line:
   ```bash
   ./gradlew assembleDebug
   ```

3. Install on device/emulator:
   ```bash
   ./gradlew installDebug
   ```

## Testing

The project includes comprehensive unit and UI tests.

### Run Unit Tests

```bash
./gradlew test
```

### Run Instrumented Tests

```bash
./gradlew connectedAndroidTest
```

### Test Coverage

| Layer | Tests | Coverage |
|-------|-------|----------|
| Domain Models | 15 | Data classes, enums |
| Domain Use Cases | 7 | Conversion logic |
| Data Repository | 27 | All unit conversions |
| Presentation ViewModels | 23 | State management |
| UI Components | 21 | Compose components |
| UI Screens | 15 | Screen integration |
| **Total** | **108** | Full coverage |

## Configuration

### SDK Versions

```kotlin
compileSdk = 36
minSdk = 24
targetSdk = 36
```

### Gradle Properties

To suppress the SDK 36 warning, add to `gradle.properties`:
```properties
android.suppressUnsupportedCompileSdk=36
```

## License

This project is available under the MIT License.

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request
