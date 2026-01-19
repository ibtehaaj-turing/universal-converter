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
