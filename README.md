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

## Preguntas
* En qué consiste el principio de responsabilidad única? Cuál es su propósito?
  - Respuesta: De todo el alcance de funciones y tareas que pueda poseer un software, solamente se debe asignar una de las posibles           responsabilidades a una clase ó módulo del sistema.
* Qué características tiene, según su opinión, un “buen” código o código limpio?
  - Respuesta: 
  1) Escrito de un humano para un humano, se escribe código limpio no pensando en la computadora, si no, en la persona que mantendrá a      futuro el código.
  2) Contiene poca o nula ausencia de código repetido
  3) Se asignan responsabilidades únicas a métodos y clases cortos, objetivos y enfocados en una tarea.
  4) Contiene una buena identación
  5) Sus nombres son acordes a la funcionalidad a realizar
  6) Intuitivo, es posible de donde viene y hacia donde va
