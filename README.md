# API Empeño - Valuación articulos

Servicio de valuación de artículos para ofrecer un préstamo estandarizado.

###  Requisitos

Para construir esta aplicación se requieren como versiones mínimas:

* Java 17
* Maven 3.2.0

El puerto de despliegue es el default

```
8080
```

### Variables de entorno

* DB_EMPENIO_ARTICULOS_URI: URI de conexión a Mongo DB (mongodb://localhost:27017)
* DB_EMPENIO_ARTICULOS_DATABASE: Nombe de la DB en mongo
* VALUACION_PORCENTAJE: Porcentaje de Valuación (Por defecto es 80)