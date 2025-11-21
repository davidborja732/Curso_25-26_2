-- Datos de prueba para demostrar el problema N+1
-- Insertamos autores
INSERT INTO autores (nombre, apellido, nacionalidad) VALUES ('Gabriel', 'Garcia Marquez', 'Colombiana'),('Isabel', 'Allende', 'Chilena'),('Mario', 'Vargas Llosa', 'Peruana'),('Jorge Luis', 'Borges', 'Argentina'),('Julio', 'Cortazar', 'Argentina');

-- Insertamos libros para cada autor
-- Libros de Gabriel Garcia Marquez (id: 1)
INSERT INTO libros (titulo, isbn, precio, anio_publicacion, autor_id) VALUES ('Cien años de soledad', '978-0-06-088328-7', 15.99, 1967, 1),('El amor en los tiempos del colera', '978-0-307-38973-4', 14.50, 1985, 1),('Cronica de una muerte anunciada', '978-1-4000-3494-9', 12.99, 1981, 1);

-- Libros de Isabel Allende (id: 2)
INSERT INTO libros (titulo, isbn, precio, anio_publicacion, autor_id) VALUES ('La casa de los espiritus', '978-0-553-38371-4', 13.95, 1982, 2),('De amor y de sombra', '978-0-553-27391-4', 12.50, 1984, 2),('Eva Luna', '978-0-553-28253-4', 11.99, 1987, 2);

-- Libros de Mario Vargas Llosa (id: 3)
INSERT INTO libros (titulo, isbn, precio, anio_publicacion, autor_id) VALUES ('La ciudad y los perros', '978-84-376-0494-7', 16.50, 1963, 3),('Conversacion en La Catedral', '978-84-376-0495-4', 18.99, 1969, 3),('La tia Julia y el escribidor', '978-84-376-0496-1', 14.75, 1977, 3);

-- Libros de Jorge Luis Borges (id: 4)
INSERT INTO libros (titulo, isbn, precio, anio_publicacion, autor_id) VALUES ('Ficciones', '978-0-8021-3037-8', 13.50, 1944, 4),('El Aleph', '978-0-525-43448-6', 12.25, 1949, 4);

-- Libros de Julio Cortazar (id: 5)
INSERT INTO libros (titulo, isbn, precio, anio_publicacion, autor_id) VALUES ('Rayuela', '978-84-376-0497-8', 17.99, 1963, 5),('Bestiario', '978-84-376-0498-5', 11.50, 1951, 5),('Final del juego', '978-84-376-0499-2', 10.99, 1956, 5);
