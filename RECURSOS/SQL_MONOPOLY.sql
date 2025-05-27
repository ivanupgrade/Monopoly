DROP DATABASE IF EXISTS MONOPOLY;
CREATE DATABASE MONOPOLY;
USE MONOPOLY;

CREATE TABLE IF NOT EXISTS cartas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    tipo ENUM ('pagar', 'cobrar', 'veA'),
    valor INT,
    descripcion VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS partidas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    fecha DATE,
    turno INT
);

CREATE TABLE IF NOT EXISTS jugadores (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    p_ganadas INT
);


CREATE TABLE IF NOT EXISTS casillas (
    posicion INT PRIMARY KEY
);

CREATE TABLE IF NOT EXISTS suerte (
    posicion INT PRIMARY KEY
);

CREATE TABLE IF NOT EXISTS esquinas (
    tipo VARCHAR(50),
    posicion INT PRIMARY KEY
);

CREATE TABLE IF NOT EXISTS calles (
    posicion INT PRIMARY KEY,
    precio INT,
    alquiler INT,
    nombre VARCHAR(50),
    tipo ENUM ('normal', 'estacion'),
    color VARCHAR(50),
    p_edificio INT,
    casa_1 INT,
    casa_2 INT,
    casa_3 INT,
    casa_4 INT,
    hotel INT
);

CREATE TABLE IF NOT EXISTS cartas_partidas (
    carta_id INT,
    partida_id INT,
    baraja ENUM ('0', '1'),
    PRIMARY KEY (carta_id, partida_id)
);

CREATE TABLE IF NOT EXISTS partidas_jugadores (
    jugador_id INT,
    partida_id INT,
    encarcelado ENUM ('false', 'true'),
    dinero INT,
    posicion INT,
    PRIMARY KEY (jugador_id, partida_id)
);

CREATE TABLE IF NOT EXISTS partidas_casillas (
    id_partida INT,
    posicion INT,
    PRIMARY KEY (id_partida, posicion)
);

CREATE TABLE IF NOT EXISTS partidas_jugadores_calles (
    id_jugador INT,
    id_partida INT,
    posicion INT,
    n_casas INT,
    alquiler_mod INT,
    PRIMARY KEY (id_jugador, id_partida, posicion)
);

ALTER TABLE cartas_partidas ADD FOREIGN KEY (carta_id) REFERENCES cartas(id);
ALTER TABLE cartas_partidas ADD FOREIGN KEY (partida_id) REFERENCES partidas(id);

ALTER TABLE suerte ADD FOREIGN KEY (posicion) REFERENCES casillas(posicion);
ALTER TABLE esquinas ADD FOREIGN KEY (posicion) REFERENCES casillas(posicion);
ALTER TABLE calles ADD FOREIGN KEY (posicion) REFERENCES casillas(posicion);

ALTER TABLE partidas_jugadores ADD FOREIGN KEY (jugador_id) REFERENCES jugadores(id);
ALTER TABLE partidas_jugadores ADD FOREIGN KEY (partida_id) REFERENCES partidas(id);
ALTER TABLE partidas_casillas ADD FOREIGN KEY (id_partida) REFERENCES partidas(id);
ALTER TABLE partidas_casillas ADD FOREIGN KEY (posicion) REFERENCES casillas(posicion);

ALTER TABLE partidas_jugadores_calles ADD FOREIGN KEY (id_jugador) REFERENCES jugadores(id);
ALTER TABLE partidas_jugadores_calles ADD FOREIGN KEY (id_partida) REFERENCES partidas(id);
ALTER TABLE partidas_jugadores_calles ADD FOREIGN KEY (posicion) REFERENCES calles(posicion);

INSERT INTO casillas VALUES
(0), (1), (2), (3), (4), (5), (6),
(7), (8), (9), (10), (11), (12), (13),
(14), (15), (16), (17), (18), (19), (20),
(21), (22), (23), (24), (25), (26), (27);

INSERT INTO calles VALUES
(1, 60, 2, 'Ronda de Vicente', 'normal', 'marrón', 50, 10, 30, 90, 160, 250),
(2, 60, 4, 'Calle de Bravo Murillo', 'normal', 'marrón', 50, 20, 60, 180, 320, 450),

(5, 100, 6, 'Glorieta de Cuatro Caminos', 'normal', 'celeste', 50, 30, 90, 270, 400, 550),
(6, 100, 6, 'Avenida de Reina Victoria', 'normal', 'celeste', 50, 30, 90, 270, 400, 550),

(8, 140, 10, 'Calle de Fuencarral', 'normal', 'rosa', 100, 50, 150, 450, 625, 750),
(9, 160, 12, 'Calle de Velázquez', 'normal', 'rosa', 100, 60, 180, 500, 700, 900),

(12, 180, 14, 'Avenida de América', 'normal', 'naranja', 100, 70, 200, 550, 750, 950),
(13, 200, 16, 'Calle de Cea Bermúdez', 'normal', 'naranja', 100, 80, 220, 600, 800, 1000),

(15, 220, 18, 'Avenida de Felipe II', 'normal', 'rojo', 150, 90, 250, 700, 875, 1050),
(16, 240, 20, 'Calle de José Ortega y Gasset', 'normal', 'rojo', 150, 100, 300, 750, 925, 1100),

(19, 260, 22, 'Avenida de América', 'normal', 'amarillo', 150, 110, 330, 800, 975, 1150),
(20, 280, 24, 'Calle de Serrano', 'normal', 'amarillo', 150, 120, 360, 850, 1025, 1200),

(22, 300, 26, 'Gran Vía', 'normal', 'verde', 200, 130, 390, 900, 1100, 1275),
(23, 320, 28, 'Paseo del Prado', 'normal', 'verde', 200, 150, 450, 1000, 1200, 1400),

(26, 350, 35, 'Paseo de la Castellana', 'normal', 'azul', 200, 175, 500, 1100, 1300, 1500),
(27, 400, 50, 'Paseo del Rey', 'normal', 'azul', 200, 200, 600, 1400, 1700, 2000);

INSERT INTO calles (posicion, precio, alquiler, nombre, tipo) VALUES
(3, 200, 25, 'Estación del Norte', 'estacion'),
(11, 200, 25, 'Estación de Goya', 'estacion'),
(17, 200, 25, 'Estación de Delicias', 'estacion'),
(25, 200, 25, 'Estación de Mediodía', 'estacion');

INSERT INTO esquinas VALUES
('salida', 0),
('carcel', 7),
('parking', 14),
('ve_carcel', 21);

INSERT INTO suerte VALUES
(4),
(10),
(18),
(24);

INSERT INTO cartas (tipo, valor, descripcion) VALUES
('cobrar', 100, 'Recibes un reembolso de Hacienda. Cobra 100€.'),
('pagar', 50, 'Vas al médico. Paga 50€.'),
('cobrar', 100, 'Recoges un premio de fotografía en “Calle de Fuencarral”. Cobra 100€.'),
('pagar', 150, 'Paga la matrícula de la universidad. 150€.'),
('cobrar', 25, 'Cobra 25€ de cada jugador por ayudarles con sus impuestos.'),
('cobrar', 10, 'Tu cumpleaños. Cada jugador te da 10€.'),
('veA', 1, 'Ve directamente a “Ronda de Vicente”. Si pasas por la salida, cobra 200€.'),
('cobrar', 45, 'Te devuelven dinero por exceso en tu tarifa de luz. Cobra 45€.'),
('pagar', 10, 'Multa por tirar basura en la calle. Paga 10€.'),
('cobrar', 200, 'Ganas una beca de estudios. Cobra 200€.'),
('pagar', 50, 'Paga 50€ por tratamiento dental.'),
('pagar', 100, 'Tu coche necesita reparación. Paga 100€.'),
('cobrar', 20, 'Fuiste voluntario en un evento. Cobra 20€.'),
('pagar', 50, 'Has perdido la cartera. Pierdes 50€.'),
('cobrar', 200, '¡Has ganado la lotería! Cobra 200€ del banco.'),
('pagar', 50, 'Te multan por exceso de velocidad en Gran Vía. Paga 50€.'),
('veA', 17, 'Ve directamente a “Estación de Delicias”. Si pasas por la salida, cobra 200€.'),
('cobrar', 150, 'La ciudad te paga por obras en Paseo del Prado. Recibe 150€.'),
('pagar', 100, 'Has sido elegido presidente de la comunidad. Paga 100€ a cada jugador.'),
('veA', 27, 'Avanza hasta la calle “Paseo del Rey”. Si pasas por la salida, cobra 200€.'),
('veA', -3, 'Retrocede tres casillas.'),
('veA', 21, 'Vas a la cárcel por hacer botellón. Ve directamente sin pasar por la salida.'),
('veA', 5, 'Avanza hasta “Glorieta de Cuatro Caminos”. Si pasas por la salida, cobra 200€.'),
('pagar', 15, 'Multa por aparcar en doble fila. Paga 15€.'),
('cobrar', 100, 'Recibes una herencia. Cobra 100€.'),
('cobrar', 75, 'Tus acciones suben de valor. Cobra 75€.'),
('veA', 11, 'Ve a “Estación de Goya”. Si pasas por la salida, cobra 200€.');
