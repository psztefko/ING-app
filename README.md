<!-- PROJECT SHIELD -->
[![Contributors][contributors-shield]][contributors-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]

<!-- TABLE OF CONTENTS -->
## Table of Contents

* [About the Project](#about-the-project)
  * [Built With](#built-with)
* [Getting Started](#getting-started)
  * [Prerequisites](#prerequisites)
  * [Installation](#installation)
* [Roadmap](#roadmap)
* [Adictional Info](#additional-info)

<!-- ABOUT THE PROJECT -->
## About The Project
Main goal of our app is to download, parse and display data from [JSONPlaceholderAPI](http://jsonplaceholder.typicode.com/).

This task may look simple, but we have already discovered that it isn’t.

Through making this app we want to learn programming in Kotlin language starting from basic parts like learning the syntax to more complicated stuff like following the optimal architecture rules of MVVM.


<img src="./Screenshots/app.gif" alt="Screenshot of application" width=200>

### Built With
This section provide information about frameworks and technologies that our project is currently using
* [Gradle](https://gradle.org/)
* [Retrofit](https://square.github.io/retrofit/)
* [Moshi](https://github.com/square/moshi)
* [Koin](https://insert-koin.io/)
* [Timber](https://github.com/JakeWharton/timber)
* [Material Icons](https://material.io/resources/icons/?style=baseline)
* [Picasso](https://square.github.io/picasso/)

<!-- GETTING STARTED -->
## Getting Started
Here you can find how to locally set our project.

### Prerequisites
To build and install that project you need gradle. We are using android studio to compile everything
* gradle
```sh
./gradlew build
```

### Installation

1. Clone the repo
```sh
git clone https://github.com/psztefko/ING-app.git
```
2. Build project with gradle
```sh
./gradlew build
```
3. Install apk on your phone or run it with avd

<!-- ROADMAP -->
## Roadmap
- [ ] Pagination for post
- [ ] Tests
- [ ] Implementing cache
- [ ] Quality of life improvments

## Additional Info
Project is realized in collaboration with [ING Bank Śląski](https://www.ing.pl/)

<!-- MARKDOWN LINKS & IMAGES -->
[contributors-shield]: https://img.shields.io/github/contributors/psztefko/ING-app?style=flat-square
[contributors-url]: https://github.com/psztefko/ING-app/graphs/contributors
[stars-shield]: https://img.shields.io/github/stars/psztefko/ING-app?style=flat-square
[stars-url]: https://github.com/psztefko/ING-app/stargazers
[issues-shield]: https://img.shields.io/github/issues/psztefko/ING-app?style=flat-square
[issues-url]: https://github.com/psztefko/ING-app/issues
