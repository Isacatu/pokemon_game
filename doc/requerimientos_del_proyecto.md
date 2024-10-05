# Documentación del Proyecto: Juego de Pokémon

**Universidad Mariano Gálvez de Guatemala**  
**Centro Universitario de Portales**  
**Facultad de Ingeniería en Sistemas**  
**Curso: Programación 2**  
**Docente: Ing. Axel Aguilar**

---

## Descripción General

Este proyecto tiene como objetivo desarrollar un videojuego inspirado en el universo Pokémon, utilizando **Java** para la implementación del juego y **SQL** para gestionar los datos de los Pokémon, entrenadores y batallas. A través de este proyecto, se busca aplicar los conocimientos adquiridos en el curso, incluyendo herencia, polimorfismo, bases de datos relacionales y la lógica de combate en tiempo real.

---

## Objetivos del Proyecto

1. **Capturar Pokémon**: Permitir a los entrenadores capturar diferentes tipos de Pokémon.
2. **Entrenar y evolucionar Pokémon**: Los entrenadores podrán entrenar y evolucionar sus Pokémon según sus características.
3. **Sistema de combate**: Implementar un sistema de combate por turnos, respetando las reglas del juego Pokémon.
4. **Gestión de datos con SQL**: Crear una base de datos que almacene la información de los Pokémon, los entrenadores, la Pokédex y las batallas.

---

## Estructura del Proyecto

### Primera Parte: Investigación y Diseño

- **Tipos de Pokémon**: Se investigaron los tipos básicos de Pokémon, como Fuego, Agua, Planta, Eléctrico, entre otros. Cada tipo posee fortalezas y debilidades específicas.
- **Diagrama de clases**: Se diseñó un diagrama de clases utilizando herencia. La clase base `Pokemon` se extiende a subclases que representan los diferentes tipos de Pokémon (e.g., `PokemonFuego`, `PokemonAgua`).
- **Interacción entre clases**: Se definieron las relaciones entre clases, como la relación entre Pokémon y sus entrenadores.

### Segunda Parte: Desarrollo de Funcionalidades

1. **Clases principales**:
    - **Entrenador**: Clase que representa al entrenador Pokémon, que contiene un equipo de Pokémon y otros atributos como el nombre y las medallas obtenidas.
    - **Pokédex**: Clase que representa la Pokédex, que almacena información sobre todos los Pokémon capturados.
    - **Pokemon**: Clase base de los Pokémon, con atributos como nombre, tipo, puntos de vida, nivel y ataques.

2. **Evolución de Pokémon**:
    - Implementación de un sistema de evolución basado en la acumulación de experiencia o el uso de objetos como piedras evolutivas.

3. **Entrenamiento de Pokémon**:
    - Los entrenadores podrán entrenar a sus Pokémon para aumentar su nivel y mejorar sus estadísticas.

### Fase Final: Sistema de Combate

- **Reglas del duelo**:
    1. El combate es por turnos, donde cada Pokémon ataca una vez.
    2. Los puntos de vida (HP) del Pokémon se reducen con cada ataque según la potencia del mismo.
    3. El combate continúa hasta que uno de los Pokémon agote todos sus puntos de vida.
    4. El entrenador que obtenga dos de tres victorias gana el duelo.

---

## Diagrama de Clases

El diagrama de clases ilustra la estructura jerárquica y las relaciones entre las principales clases del juego. Se incluyen las clases `Pokemon`, `PokemonFuego`, `PokemonAgua`, `Entrenador`, `Batalla`, y `Pokedex`.

![Diagrama de Clases](URL)

---

## Base de Datos SQL

Se utilizó **MySQL** para almacenar y gestionar la información del juego.

### Tablas principales

- **Pokémon**: Tabla que almacena los atributos de cada Pokémon (ID, nombre, tipo, puntos de vida, nivel).
- **Entrenadores**: Tabla que almacena la información de los entrenadores (ID, nombre, medallas).
- **Batallas**: Tabla que registra el historial de batallas entre entrenadores.
- **Mochila**: Tabla que almacena los objetos que tiene cada entrenador (piedras evolutivas, pociones, etc.).

### Funcionalidades Implementadas
- **Captura de Pokémon**: Los entrenadores pueden capturar Pokémon salvajes y almacenarlos en su equipo. 
- **Entrenamiento y Evolución**: Se puede entrenar a los Pokémon para subir de nivel y evolucionar. 
- **Sistema de Batallas**: Implementación de un sistema de combate basado en turnos, con lógica de daño, tipos de ataque, y condiciones de victoria.

### Reglas del Combate

Cada Pokémon tiene un turno por ronda. Los ataques reducen los puntos de vida del Pokémon rival. 
La batalla termina cuando uno de los Pokémon queda sin puntos de vida. 
El primer entrenador en ganar dos de tres combates es declarado ganador.

### Conclusión

Este proyecto ha permitido el desarrollo de habilidades en programación orientada a objetos y manejo de bases de datos. 
Al implementar un sistema de batallas y gestión de Pokémon en Java, conectado con una base de datos SQL, hemos replicado la lógica central del juego Pokémon, ofreciendo a los jugadores una experiencia similar a la de los videojuegos originales.