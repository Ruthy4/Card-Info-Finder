# Card-Info-Finder

# Card-Info-Finder

# Description

Card Info finder is an app that takes in a card number and displays the card details that corresponds to the number. This project takes advantage of best practices, many popular libraries and tools in the Android ecosystem. Most of the libraries are in the stable version unless there is a good reason to use non-stable dependency.

![Screenshot_20211112-195226](https://user-images.githubusercontent.com/33810711/141525099-50840261-e355-42fd-8cac-f3584d298a9c.jpg)

![Screenshot_20211112-195237](https://user-images.githubusercontent.com/33810711/141525111-ba88befe-8298-4053-8872-9677d59942d4.jpg)

# Features

- Dependency injection (with Hilt)

- Google Material Design library

- Android architecture components to share ViewModels during configuration changes

- Edge To Edge Configuration

- Resource defaults

- colors.xml - colors for the entire project

- styles.xml - widget styles

- styles-text.xml - text appearances

- ViewModel - stores and manages UI-related data in a lifecycle conscious way

- Lifecycle- it performs an action when the life cycle state changes

- Retrofit - For networking

- ViewBinding - binding data to views

- [100% Kotlin](https://kotlinlang.org/) + [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - perform background operations


- [Android Architecture components](https://developer.android.com/topic/libraries/architecture) ([ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel), [LiveData](https://developer.android.com/topic/libraries/architecture/livedata), [Navigation](https://developer.android.com/jetpack/androidx/releases/navigation))

- `Network` module - this is the main module. It contains code that wires multiple modules together like dependency injection setup and fundamental application configuration like retrofit configuration

- Testing
    * [Unit Tests](https://en.wikipedia.org/wiki/Unit_testing) ([JUnit 5](https://junit.org/junit5/) via
    [android-junit5](https://github.com/mannodermaus/android-junit5))
    * [UT Tests](https://en.wikipedia.org/wiki/Graphical_user_interface_testing) ([Espresso](https://developer.android.com/training/testing/espresso))
    * [Mockk](https://mockk.io/) - mocking framework

- RetrofitService** - defines a set of API endpoints.
