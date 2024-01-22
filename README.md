
# Bitcoin Price Tracker

Bitcoin Price Tracker is an Android project with Hilt, Coroutines, Flow, Jetpack(Room, ViewModel) on MVVM architecture..

![Logo](https://user-images.githubusercontent.com/123986081/219872952-5d656879-62f2-4bf6-8f1c-3ea7dfd00357.jpg)

## Download
Go to the [Release](https://github.com/uargunn/cointracker-android/raw/release/202302181725/v1.0release.apk) to download the latest APK.

## Tech Stack

* Minimum SDK level 24
* MVVM Architecture
* Coil: Loading images from network.
* Hilt for Dependency Injection
* Retrofit: Construct the REST APIs.
* Jetpack Compose: Declarative UI
* Firebase
  - Firestore
  - Authentication

## Architecture

Bitcoin Price Tracker is based on the MVVM architecture and the Repository pattern, which follows the [Google's official architecture guidance](https://developer.android.com/topic/architecture).

![Logo](https://user-images.githubusercontent.com/123986081/219874094-a44aa193-c7a6-4e12-a28a-4127527b03fc.png)

## CoinGecko API

Bitcoin Price Tracker is using the [CoinGeckoAPI](https://www.coingecko.com/) for constructing RESTful API.
