# Application Description

## Multi-module, clean architecture, MVVM
## Libraries:
- [Jetpack Navigation](https://developer.android.com/guide/navigation);
- [Lifecycle](https://developer.android.google.cn/jetpack/androidx/releases/lifecycle);
- [Dagger](https://github.com/google/dagger);
- [Glide](https://github.com/bumptech/glide);
- [Retrofit](https://github.com/square/retrofit);
- [Paging](https://developer.android.com/topic/libraries/architecture/paging);
- [Gson](https://github.com/google/gson);
- [Room](https://developer.android.com/training/data-storage/room).


# [app module](../app) overview

## app module - entry point at the application.

## Responsible for:

# CHECK URL

- Providing dependencies for the feature modules;
- Navigation between feature modules
  using [NavigationUtil](../core/src/main/java/com/example/kinopoiskCinemaApp/core/ext/NavigationUtil.kt);
- Setting splash screen.

## Description:

- ### [di package](../app/src/main/java/com/example/kinopoiskCinemaApp/di) (contains classes responsible for dependency injection):
    - [AppComponent](../app/src/main/java/com/example/kinopoiskCinemaApp/di/AppComponent.kt) -
      main application component that create a graph of the dependencies;
    - [DataModule](../app/src/main/java/com/example/kinopoiskCinemaApp/di/DataModule.kt) -
      the part of the dependency graph that provides data sources;
    - [DomainModule](../app/src/main/java/com/example/kinopoiskCinemaApp/di/DomainModule.kt) -
      the part of the dependency graph that provides use cases;
- ### [presentation package](../app/src/main/java/com/example/kinopoiskCinemaApp/presentation) (contains classes responsible for application ui):
    - [KinopoiskApplication](../app/src/main/java/com/example/kinopoiskCinemaApp/presentation/KinopoiskApplication.kt) -
      the base class for maintaining global application state. This class creates dependency graph
      and
      provides features components;
    - ### [flows package](../app/src/main/java/com/example/kinopoiskCinemaApp/presentation/flows) (contains fragments that perform the activity function):
        - [HomeFlowFragment](../app/src/main/java/com/example/kinopoiskCinemaApp/presentation/flows/HomeFlowFragment.kt),
          [MoreFlowFragment](../app/src/main/java/com/example/kinopoiskCinemaApp/presentation/flows/MoreFlowFragment.kt) - flow fragments;
    - ### [ui package](../app/src/main/java/com/example/kinopoiskCinemaApp/presentation/ui) (contains classes responsible for ui configuration and interaction with user):
        - [MainActivity](../app/src/main/java/com/example/kinopoiskCinemaApp/presentation/ui/MainActivity.kt) -
          the entry point at the application. This class configures splash screen, navigation
          between features and
          setting up navigation ui;
    - ### [view package](../app/src/main/java/com/example/kinopoiskCinemaApp/presentation/view) (contains classes with custom views specific to the app module only):
        - [CustomBottomNavigationView](../app/src/main/java/com/example/kinopoiskCinemaApp/presentation/view/CustomBottomNavigationView.kt) -
          custom bottom navigation bar with items underline;
    - ### [viewmodel package](../app/src/main/java/com/example/kinopoiskCinemaApp/presentation/viewmodel) (contains view models for mvvm pattern)
        - [MainActivityViewModel](../app/src/main/java/com/example/kinopoiskCinemaApp/presentation/viewmodel/MainActivityViewModel.kt) -
          [MainActivity](../app/src/main/java/com/example/kinopoiskCinemaApp/presentation/ui/MainActivity.kt)
          viewmodel


### Description of UI-test scenarios



# Unit Testing
- [MockWebServer](https://github.com/square/okhttp/tree/master/mockwebserver) A scriptable web server for testing HTTP clients
- [Mockito](https://site.mockito.org/) Mocking framework for unit tests
- [Truth](https://truth.dev/) is a library for performing assertions in tests:
- [Turbine](https://github.com/cashapp/turbine) is a small testing library for kotlinx.coroutines Flow.