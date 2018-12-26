# MovieDbClient

Android client for themoviedb api

## Getting Started

A moviedb Api Key is required in order to run the application, get it here: https://developers.themoviedb.org

## Built With

* [EventBus] (https://github.com/greenrobot/EventBus) - Communication between Activities, Fragments, Threads, Services
* [Retrofit] (https://square.github.io/retrofit/) - Http Client
* [Butterknife] (http://jakewharton.github.io/butterknife/) - View injection
* [Glide] (https://github.com/bumptech/glide) - Image handling

## Architecture
* api - Includes all the files requiered to make http call with Retrofit
* entities - Includes business logic, model objects
* eventbus - Includes eventbus events 
* images.ui.adapters - Includes recycler view adapter and view pager adapter
* images.ui - Includes image interfaces
* lib - Includes Glide implementation
* views - Includes activities and fragments

## Authors

* **William Giraldo** - [WilliamAGRincon](https://github.com/WilliamAGRincon)

## Next Steps
* Add Room persistence library to add offline functionality
* Replace Event Bus library with RxJava to add better code separation and mantainability
* Add Dagger 2 to avoid object instances inside classes


