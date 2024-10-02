
CREATE DATABASE pokemon_game;

USE pokemon_game;

CREATE TABLE Usuario
(
    id            INT AUTO_INCREMENT PRIMARY KEY,
    username      VARCHAR(50) UNIQUE NOT NULL,
    password      VARCHAR(255)       NOT NULL

);

CREATE TABLE Entrenador
(
    id            INT AUTO_INCREMENT PRIMARY KEY,
    nombre        NVARCHAR(100) NOT NULL,
    pueblo_origen NVARCHAR(100),
    usuario_id INT,
    FOREIGN KEY (usuario_id) REFERENCES Usuario (id)
);

-- Crear la tabla Tipo (usando autorreferencia si un tipo puede tener subtipos)
CREATE TABLE Tipo
(
    id            INT AUTO_INCREMENT PRIMARY KEY,
    nombre        VARCHAR(50) NOT NULL,
    tipo_padre_id INT DEFAULT NULL, -- Esto permite crear jerarquías de tipos
    FOREIGN KEY (tipo_padre_id) REFERENCES Tipo (id)
);

-- Crear la tabla Especie
CREATE TABLE Especie
(
    id      INT AUTO_INCREMENT PRIMARY KEY,
    nombre  VARCHAR(50) NOT NULL,
    tipo_id INT,
    FOREIGN KEY (tipo_id) REFERENCES Tipo (id)
);

-- Crear la tabla Pokemon
CREATE TABLE Pokemon
(
    id            INT AUTO_INCREMENT PRIMARY KEY,
    apodo         VARCHAR(50) DEFAULT NULL,                         -- Apodo opcional
    nivel         INT NOT NULL CHECK (nivel >= 1 AND nivel <= 100), -- Definir rango válido para el nivel
    salud         INT NOT NULL CHECK (salud > 0),                   -- Asegurar que la salud siempre sea positiva
    entrenador_id INT,
    especie_id    INT,
    FOREIGN KEY (especie_id) REFERENCES Especie (id)
);

-- Crear la tabla TipoHabilidad (tipos de habilidades como 'ataque', 'defensa', 'curación')
CREATE TABLE TipoHabilidad
(
    id     INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL
);

-- Crear la tabla Habilidad
CREATE TABLE Habilidad
(
    id                INT AUTO_INCREMENT PRIMARY KEY,
    nombre            VARCHAR(50) NOT NULL,
    tipo_habilidad_id INT,
    FOREIGN KEY (tipo_habilidad_id) REFERENCES TipoHabilidad (id)
);

-- Crear la tabla PokemonHabilidad (relación muchos a muchos entre Pokemon y Habilidad)
CREATE TABLE PokemonHabilidad
(
    pokemon_id   INT,
    habilidad_id INT,
    PRIMARY KEY (pokemon_id, habilidad_id),
    FOREIGN KEY (pokemon_id) REFERENCES Pokemon (id),
    FOREIGN KEY (habilidad_id) REFERENCES Habilidad (id)
);

-- Crear la tabla Evolucion (relaciona las especies en una evolución)
CREATE TABLE Evolucion
(
    id                 INT AUTO_INCREMENT PRIMARY KEY,
    especie_origen_id  INT,
    especie_destino_id INT,
    FOREIGN KEY (especie_origen_id) REFERENCES Especie (id),
    FOREIGN KEY (especie_destino_id) REFERENCES Especie (id)
);

-- Crear la tabla RegistroEvolucion
CREATE TABLE RegistroEvolucion
(
    id                 INT AUTO_INCREMENT PRIMARY KEY,
    pokemon_id         INT,
    especie_origen_id  INT,
    especie_destino_id INT,
    fecha_evolucion    DATE,
    FOREIGN KEY (pokemon_id) REFERENCES Pokemon (id),
    FOREIGN KEY (especie_origen_id) REFERENCES Especie (id),
    FOREIGN KEY (especie_destino_id) REFERENCES Especie (id)
);

DELETE FROM Usuario;
DELETE FROM Entrenador;

SELECT
    *
FROM Usuario usr
         INNER JOIN Entrenador ent ON usr.id = ent.usuario_id;

INSERT INTO Tipo (nombre) VALUES ('Planta');
INSERT INTO Tipo (nombre) VALUES ('Veneno');
INSERT INTO Tipo (nombre) VALUES ('Fuego');
INSERT INTO Tipo (nombre) VALUES ('Agua');
INSERT INTO Tipo (nombre) VALUES ('Eléctrico');
INSERT INTO Tipo (nombre) VALUES ('Normal');
INSERT INTO Tipo (nombre) VALUES ('Volador');
INSERT INTO Tipo (nombre) VALUES ('Bicho');
INSERT INTO Tipo (nombre) VALUES ('Hielo');
INSERT INTO Tipo (nombre) VALUES ('Lucha');
INSERT INTO Tipo (nombre) VALUES ('Psíquico');
INSERT INTO Tipo (nombre) VALUES ('Tierra');
INSERT INTO Tipo (nombre) VALUES ('Roca');
INSERT INTO Tipo (nombre) VALUES ('Fantasma');
INSERT INTO Tipo (nombre) VALUES ('Dragón');
INSERT INTO Tipo (nombre) VALUES ('Acero');
INSERT INTO Tipo (nombre) VALUES ('Siniestro');


INSERT INTO Especie (nombre, tipo_id) VALUES ('Bulbasaur', 1);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Ivysaur', 1);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Venusaur', 1);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Charmander', 3);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Charmeleon', 3);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Charizard', 3);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Squirtle', 4);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Wartortle', 4);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Blastoise', 4);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Pikachu', 5);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Raichu', 5);

SELECT * FROM pokemon;

INSERT INTO Pokemon (apodo, nivel, salud, entrenador_id, especie_id)
VALUES
    ('Bulbi', 5, 100, 1, 1),
    ('Charry', 8, 120, 1, 4),
    ('Squirty', 7, 110, 2, 7),
    ('Pika', 10, 130, 3, 25),
    ('Geo', 6, 90, 1, 74),
    ('Eevee', 15, 140, 2, 133),
    ('Drago', 12, 150, 3, 147);

ALTER TABLE Pokemon
    DROP COLUMN entrenador_id;
ALTER TABLE Pokemon
    DROP FOREIGN KEY pokemon_ibfk_1;

DROP DATABASE pokemon_game;
DESCRIBE Pokemon;
