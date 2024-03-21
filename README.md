# Conexa App

Este proyecto ha sido creado siguiendo el enfoque de Clean Architecture y los principios SOLID, utilizando una estructura multi-modular, es decir, que utilizando el principio de Inversión de Dependencias los módulos de más alto nivel no dependen directamente de los módulos de bajo nivel, sino que dependen de abstracciones y de inyecciones de dependencias.

También he aplicado algunos patrones de diseño como son Factory, para los repositorios y sus use case, Singleton, para los utils, y Observer para poder trabajar con coroutines.

He realizado pruebas unitarias al 100% en los UseCases y en los ViewModels.

Algunas de las dependencias que uso son:

- Retrofit
- Dagger Hilt
- Firebase Crashlytics
- Mockk
- entre otros

La parte de UI la he realizado siguiendo las buenas prácticas recomendadas por material.io, utilizando también sus componentes y toda la librería de iconos que Material brinda.
