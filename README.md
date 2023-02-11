#  Выпускной проект OTUS #
### Приложение "Лента новостей" ###
## Приложение включает 3 экрана: ##
1. News list
2. Details
3. Favorites

## Public API ##
NewsAPI, <https://newsapi.org/>

## Stack ##
* Архитектура - **Clean Arch**
* Многомодульность - **по фичам (своя фиче на каждый экран)**
* DI - **Dagger2**
* Асинхронность - **Kotlin Coroutines**
* Сеть: **Retrofit + OkHttp**
* Локальное хранение - **Room**
* Организация Presentation слоя: **MVI + Jetpack Compose + Material3**
* Навигация - **Navigation Compose (androidx.navigation:navigation-compose:VERSION)**
* Дополнительно - **Dark Theme Support**

## Приблизительная схема модулей ##

![ModuleScheme](https://user-images.githubusercontent.com/104722036/218245059-2dd14a7a-c9f2-4831-90a1-5a7c15d85884.png)
