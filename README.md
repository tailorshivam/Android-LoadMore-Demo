# **Android Load More Demo**
[![Platform](https://img.shields.io/badge/Platform-Android-brightgreen.svg)](https://developer.android.com/) ![Version](https://img.shields.io/badge/Version-1.0-blue.svg) ![Language](https://img.shields.io/badge/Language-Kotlin-orange.svg)

This repository contains a demo Android application that demonstrates the implementation of **infinite scrolling** using `RecyclerView`, `Retrofit`, and the `MVVM` architecture with Kotlin. The application efficiently handles paginated data loading from a REST API, displaying it in a dynamically updating list with an integrated loading indicator for improved user experience.

## **Key Features**

- **MVVM Architecture:** Ensures a clean separation of concerns by decoupling the UI layer from business logic and data management.
- **Retrofit for Networking:** Utilizes `Retrofit` to make network requests and handle API responses seamlessly.
- **RecyclerView with Pagination:** Implements infinite scrolling using `RecyclerView` and detects when to load additional data as the user reaches the end of the list.
- **View Binding:** Leverages `View Binding` to efficiently interact with UI components while minimizing boilerplate code.
- **Asynchronous Data Handling with Coroutines:** Uses Kotlin Coroutines for asynchronous operations, ensuring that network calls and data processing are handled off the main thread.
- **Loading Indicator:** Displays a visual indicator at the bottom of the list while additional data is being fetched, ensuring smooth user interaction

## **Technology Stack**

- **Language:** Kotlin
- **Architecture:** MVVM (Model-View-ViewModel)
- **Networking:** Retrofit for HTTP requests and REST API integration
- **Concurrency:** Kotlin Coroutines for efficient background task management
- **UI Components:** RecyclerView, View Binding for optimized UI updates
- **Dependency Injection (Optional):** Dagger/Hilt (if added for modular dependency management)

## **Project Setup**
### **Installation**
1. Clone the repository using Git:
   ```bash
   git clone https://github.com/tailorshivam/Android-LoadMore-Demo.git
3. Open the project in **Android Studio.**
4. Sync the project with Gradle and ensure all dependencies are resolved.
5. Build and run the application on an Android emulator or physical device.

### **Application Workflow**
- On launch, the application fetches an initial set of items from a mock API using Retrofit.
- The items are displayed in a RecyclerView. As the user scrolls to the bottom, additional items are loaded from the API.
- The loading indicator appears at the bottom of the list while new data is being retrieved from the server.
- The user can continue to scroll and load more data until no more items are available.

## **How It Works**

- **Networking with Retrofit:** `Retrofit` is used to handle the HTTP requests for fetching paginated data from a mock API.
- **RecyclerView Adapter:** The `RecyclerView` adapter manages the display of list items and dynamically appends new data as it becomes available.
- **Scroll Listener for Pagination:** A scroll listener is attached to the `RecyclerView` to detect when the user reaches the end of the list, triggering an additional API call to load more items.
- **ViewModel and LiveData:** The `ViewModel` layer handles data fetching and maintains the state of the list, using `LiveData` to notify the UI of updates.
- **Loading Indicator:** A loading spinner is shown at the bottom of the list while new data is being fetched, enhancing the user experience during data loading.

## Download APK
You can download the latest APK from the releases section: [Download APK](https://github.com/tailorshivam/Android-LoadMore-Demo/releases/latest)

