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

![ModuleScheme](https://user-images.githubusercontent.com/104722036/218265937-9c8f243a-5627-40c6-9d37-c96e8bc5906d.png)


