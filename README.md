-------------------
# Base Android Project using MVVM + Clean Architecture

- In this repository, i want to talk about how to build a base project using MVVM + Clean Architecture in Android. Although there are many ways to build the base project, i hope my approach can help you. Besides, i also hope you contribute your opinion to make this repository better.
- For those who don't know what is the Clean Architecture, let read this article: [Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)

-------------------

# Project structure

The project is divided 4 main modules:

- Core module: Include BaseActivity, BaseFragment, BaseViewModel, BaseRecyclerView, extension functions and some utils such as: ErrorUtils, LocaleHelper,...
- Data module: Corresponds to Data layer. It include local data source and remote data source
- Domain module: Corresponds to Domain layer. It include some folders such as: mapper, domain model, repository and use case
- App module: Corresponds to Presentation layer. It include features of application. Each Activity/Fragment will inherit BaseActivity/BaseFragment class, Viewmodel inherit BaseViewModel

-------------------

# Library

- Language: [Kotlin](https://kotlinlang.org/)
- Dependency Injection: [Dagger Hilt](https://developer.android.com/training/dependency-injection/hilt-android)
- Executes Asynchronously: [Kotlin Coroutines](https://developer.android.com/kotlin/coroutines?gclid=CjwKCAiA4KaRBhBdEiwAZi1zzrfte38ccz4Cv6bj_OGNL4KpQMa9HyaiZhW7z-StHJ7DQjK1OjDnHhoCLYwQAvD_BwE&gclsrc=aw.ds)
- Make network call: [Retrofit](https://square.github.io/retrofit/) 
- Local database: [Room](https://developer.android.com/training/data-storage/room) 
- Gradle: [Kotlin DSL](https://developer.android.com/studio/build/migrate-to-kts)

-------------------

# API

- The project use [Oxforddictionaries API](https://developer.oxforddictionaries.com/) to search words

-------------------

# Example

I built a simple app to implement this base project. My application has 2 main features: search language in dictionary and change language between Vietnamese and English

-------------------

# Conclusion

One more time, thank you for viewing my repository. If you have any ideas, leave it in the comments section.


##### Best regards.
