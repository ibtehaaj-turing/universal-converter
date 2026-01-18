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
│                    Presentation Layer                   │
│  ┌─────────────┐  ┌─────────────┐  ┌─────────────────┐  │
│  │   Screens   │  │  ViewModels │  │   Components    │  │
│  └─────────────┘  └─────────────┘  └─────────────────┘  │
├─────────────────────────────────────────────────────────┤
│                      Domain Layer                       │
│  ┌─────────────┐  ┌─────────────┐  ┌─────────────────┐  │
│  │   Models    │  │  Use Cases  │  │   Repository    │  │
│  │             │  │             │  │   (Interface)   │  │
│  └─────────────┘  └─────────────┘  └─────────────────┘  │
├─────────────────────────────────────────────────────────┤
│                       Data Layer                        │
│  ┌─────────────────────────────────────────────────┐    │
│  │              Repository Implementation          │    │
│  └─────────────────────────────────────────────────┘    │
└─────────────────────────────────────────────────────────┘
```

## Project Structure

```
app/src/main/java/com/example/converter/
├── data/
│   └── repository/
│       └── ConversionRepositoryImpl.kt
├── di/
│   └── AppModule.kt
├── domain/
│   ├── model/
│   │   ├── ConversionResult.kt
│   │   ├── ConversionUnit.kt
│   │   └── ConverterCategory.kt
│   ├── repository/
│   │   └── ConversionRepository.kt
│   └── usecase/
│       ├── ConvertValueUseCase.kt
│       └── GetUnitsForCategoryUseCase.kt
├── presentation/
│   ├── components/
│   │   ├── CategoryCard.kt
│   │   ├── ConversionInputField.kt
│   │   ├── SwapButton.kt
│   │   └── UnitDropdown.kt
│   ├── converter/
│   │   ├── ConverterScreen.kt
│   │   ├── ConverterUiState.kt
│   │   └── ConverterViewModel.kt
│   ├── home/
│   │   ├── HomeScreen.kt
│   │   ├── HomeUiState.kt
│   │   └── HomeViewModel.kt
│   ├── navigation/
│   │   └── ConverterNavGraph.kt
│   └── theme/
│       ├── Color.kt
│       ├── Theme.kt
│       └── Type.kt
├── ConverterApplication.kt
└── MainActivity.kt
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
