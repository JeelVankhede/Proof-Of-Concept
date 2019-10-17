# About App
**Proof of concept** is an Android app consumes a REST service and displays photos with headings and description.

# Task Specifications

- [x] Ingests a json feed from [https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/facts.json](https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/facts.json)

    • The feed contains a title and a list of rows.
     
    • Use of third party json parser to parse this if desired.

- [x] Display the content *(including image, title and description)* in a RecyclerView

    • The title in the ActionBar should be updated from the json data.
 
    • Each row should be dynamically sized to the right height to display its content - no clipping, no extraneous white-space etc. This means some rows will be larger than others.

- [x] Loads the images lazily *(does not download them all at once, but only as needed)*.

- [x] Implement a refresh function allowing the data & view to be updated

    • Use of pull down to refresh.

- [x] Does not block UI when loading the data from the json feed.

# Screen

###### Portrait Mode

![Portrait Mode](https://github.com/JeelVankhede/Proof-Of-Concept/blob/master/screens/output.png)

###### Landscape Mode

![Landscape Mode](https://github.com/JeelVankhede/Proof-Of-Concept/blob/master/screens/output-land.png)

# Coding patterns

 - **Kotlin** for programming language
 - **MVVM** Architecture with **DataBinding** support
 - **Repository** pattern for handling business logic
 - **ROOM** Persistence for local SQLite database connection
 - **Binder** pattern used for UI data logic
 - **Builder pattern** used for RecyclerView Adapter
 - Kotlin DSL implementation
 - Kotlin Extensions functions

# Open Source Library credits

1. [AppCompat](https://developer.android.com/jetpack/androidx/releases/appcompat) : Support library provided by Google for backward compatibility 

2. [Core-Ktx](https://developer.android.com/jetpack/androidx/releases/core) : Core library with Kotlin extension support used in this project

3. [ConstraintLayout](https://developer.android.com/jetpack/androidx/releases/constraintlayout) : Constraint layout for UI screen designing to reduce nested hierarchy
    
4. [Material](https://github.com/material-components/material-components-android) : Material design library

5. [Lifecycle-Extensions](https://developer.android.com/jetpack/androidx/releases/lifecycle) : Used for ViewModel & LiveData related dependencies

6. [Glide](https://github.com/bumptech/glide/blob/master/README.md) : Lazy loading image processing library to load list of images from network

7. [Retrofit](https://square.github.io/retrofit/) : HTTP client library for REST API calls

8. [ROOM](https://developer.android.com/jetpack/androidx/releases/room) : ORM mapping persistence library for local database connection support

9. [SwipeRefreshLayout](https://developer.android.com/jetpack/androidx/releases/swiperefreshlayout) : Provides ability to pull down to refresh UI content

# Output

Download Apk from **[here](https://github.com/JeelVankhede/Proof-Of-Concept/blob/master/apk/Proof_Of_Concept-1.0-[17-Oct-2019]-debug.apk)**