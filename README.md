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
* [Usage](#usage)
* [Roadmap](#roadmap)
  * [6 Stages of Fixing code](#6-Stages-of-Fixing-code)
* [Questions](#questions)
* [Adictional Info](#additional-info)

<!-- ABOUT THE PROJECT -->
## About The Project
<!-- TODO: Add screenshot -->
There are many usefull android apps that really suit everyone needs, we are begginers at android so, we know that it's really hard to make good app. This is simple app that parses [JSONPlaceholderAPI](http://jsonplaceholder.typicode.com/). Creating apps according to every style guide and design patterns for android is pretty hard especially when we didn't have experience with mobile apps but we are giving our best to create good easy to extend app.

We just took approach that prioritize good architecture to spend less time when we implement stuff. It will save us a lot of time late.

### Built With
This section provide information about frameworks and technologies that our project is currently using
* [Gradle](https://gradle.org/)
* [Retrofit](https://square.github.io/retrofit/)
* [Moshi](https://github.com/square/moshi)
* [Koin](https://insert-koin.io/)
* [Timber](https://github.com/JakeWharton/timber)

<!-- GETTING STARTED -->
## Getting Started
This is instructions how to set our project locally.

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
- [x] Basic networking
- [x] Basic DI
- [x] Basic Databinding
- [x] Basic Structure of project
- [x] RecyclerView Adapter
- [ ] Showing post in RecyclerView <-- temporary downgrade
- [ ] Pagination for post
- [ ] Showing user info
- [ ] Showing Comments from user post
- [ ] Showing Photos
- [ ] Navigation Intent

## 6 Stages of Fixing code
- [x] Denial and Isolation
- [x] Anger
- [x] Barging
- [x] Depression
- [x] Acceptance
- [x] Fixed Bug

## Questions


## Additional Info
Project is realized in collaboration with [ING Bank Śląski](https://www.ing.pl/)

<!-- MARKDOWN LINKS & IMAGES -->
[contributors-shield]: https://img.shields.io/github/contributors/psztefko/ING-app?style=flat-square
[contributors-url]: https://github.com/psztefko/ING-app/graphs/contributors
[stars-shield]: https://img.shields.io/github/stars/psztefko/ING-app?style=flat-square
[stars-url]: https://github.com/psztefko/ING-app/stargazers
[issues-shield]: https://img.shields.io/github/issues/psztefko/ING-app?style=flat-square
[issues-url]: https://github.com/psztefko/ING-app/issues