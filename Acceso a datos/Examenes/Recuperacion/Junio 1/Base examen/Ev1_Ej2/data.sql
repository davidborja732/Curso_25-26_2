-- Datos de prueba para la Liga de Futbol
-- Las fechas LocalDate usan formato: 'YYYY-MM-DD'
-- Las fechas LocalDateTime usan formato: 'YYYY-MM-DD HH:MM:SS'

INSERT INTO entrenadores (nombre, apellidos, fecha_nacimiento, nacionalidad, email) VALUES
('Pep',    'Guardiola',  '1971-01-18', 'Española',   'pep.guardiola@mcfc.com'),
('Carlo',  'Ancelotti',  '1959-06-10', 'Italiana',   'carlo.ancelotti@realmadrid.com'),
('Diego',  'Simeone',    '1970-04-28', 'Argentina',  'diego.simeone@atletico.com'),
('Xavi',   'Hernandez',  '1980-01-25', 'Española',   'xavi.hernandez@fcbarcelona.com');

INSERT INTO equipos (nombre, ciudad, estadio, presupuesto, fecha_fundacion, entrenador_id) VALUES
('Manchester City',    'Manchester', 'Etihad Stadium',    650.0, '1880-05-16', 1),
('Real Madrid',        'Madrid',     'Santiago Bernabeu', 800.0, '1902-03-06', 2),
('Atletico de Madrid', 'Madrid',     'Metropolitano',     350.0, '1903-04-26', 3),
('FC Barcelona',       'Barcelona',  'Spotify Camp Nou',  600.0, '1899-11-29', 4);

INSERT INTO jugadores (nombre, apellidos, fecha_nacimiento, fecha_fichaje, altura, peso, posicion, dorsal, salario, pais, equipo_id) VALUES
('Erling',  'Haaland',      '2000-07-21', '2022-07-01 00:00:00', 1.94, 88.0, 'DELANTERO',     9,  45000.0, 'Noruega',   1),
('Kevin',   'De Bruyne',    '1991-06-28', '2015-08-30 09:00:00', 1.81, 70.0, 'CENTROCAMPISTA',17, 35000.0, 'Belgica',   1),
('Vinicius','Junior',       '2000-07-12', '2018-07-20 10:30:00', 1.76, 73.0, 'DELANTERO',     7,  40000.0, 'Brasil',    2),
('Luka',    'Modric',       '1985-09-09', '2012-08-27 11:00:00', 1.72, 65.0, 'CENTROCAMPISTA',10, 25000.0, 'Croacia',   2),
('Kylian',  'Mbappe',       '1998-12-20', '2024-07-01 00:00:00', 1.78, 73.0, 'DELANTERO',     9,  65000.0, 'Francia',   2),
('Thibaut', 'Courtois',     '1992-05-11', '2018-07-03 09:00:00', 1.99, 96.0, 'PORTERO',       1,  20000.0, 'Belgica',   2),
('Antoine', 'Griezmann',    '1991-03-21', '2019-07-12 09:00:00', 1.76, 73.0, 'DELANTERO',     7,  30000.0, 'Francia',   3),
('Jan',     'Oblak',        '1993-01-07', '2014-07-01 00:00:00', 1.88, 87.0, 'PORTERO',       13, 20000.0, 'Eslovenia', 3),
('Pedri',   'Gonzalez',     '2002-11-25', '2020-07-01 00:00:00', 1.74, 60.0, 'CENTROCAMPISTA',8,  15000.0, 'España',    4),
('Robert',  'Lewandowski',  '1988-08-21', '2022-08-04 10:00:00', 1.85, 79.0, 'DELANTERO',     9,  32000.0, 'Polonia',   4),
-- Agente libre (sin equipo)
('Fernando','Torres',       '1984-03-20', '2011-01-31 14:00:00', 1.83, 80.0, 'DELANTERO',     9,  10000.0, 'España',    NULL);
