-- Datos para la tabla Cliente
INSERT INTO cliente (id, nombre, email, telefono) VALUES (1, 'Juan Perez', 'juan.perez@example.com', '123456789');
INSERT INTO cliente (id, nombre, email, telefono) VALUES (2, 'Maria Lopez', 'maria.lopez@example.com', '987654321');
INSERT INTO cliente (id, nombre, email, telefono) VALUES (3, 'Carlos Garcia', 'carlos.g@example.com', '123123123');

-- Datos para la tabla Editorial
INSERT INTO editorial (id, nombre, direccion) VALUES (1, 'Editorial Planeta', 'Calle Falsa 123');
INSERT INTO editorial (id, nombre, direccion) VALUES (2, 'Editorial Santillana', 'Avenida Siempre Viva 456');
INSERT INTO editorial (id, nombre, direccion) VALUES (3, 'Editorial Anaya', 'Calle de la Piruleta 789');

-- Datos para la tabla Libro
INSERT INTO libro (id, titulo, precio, isbn, editorial_id) VALUES (1, 'El Quijote', 19.99, '123-4567890123', 1);
INSERT INTO libro (id, titulo, precio, isbn, editorial_id) VALUES (2, 'Cien Años de Soledad', 24.99, '123-4567890124', 2);
INSERT INTO libro (id, titulo, precio, isbn, editorial_id) VALUES (3, 'Don Juan Tenorio', 14.99, '123-4567890125', 3);
INSERT INTO libro (id, titulo, precio, isbn, editorial_id) VALUES (4, 'El Señor de los Anillos', 29.99, '123-4567890126', 1);
INSERT INTO libro (id, titulo, precio, isbn, editorial_id) VALUES (5, 'El Perfume', 9.99, '123-4567890127', 2);


-- Datos para la tabla Pedido
INSERT INTO pedido (id, fecha, total, cliente_id) VALUES (1, '2023-01-01', 44.98, 1);
INSERT INTO pedido (id, fecha, total, cliente_id) VALUES (2, '2023-02-01', 24.99, 2);
INSERT INTO pedido (id, fecha, total, cliente_id) VALUES (3, '2023-03-01', 9.99, 3);
INSERT INTO pedido (id, fecha, total, cliente_id) VALUES (4, '2023-04-01', 29.99, 1);
INSERT INTO pedido (id, fecha, total, cliente_id) VALUES (5, '2023-05-01', 14.99, 2);

-- Datos para la tabla pedido_libro (relación ManyToMany entre Pedido y Libro)
INSERT INTO pedido_libro (pedido_id, libro_id) VALUES (1, 1);
INSERT INTO pedido_libro (pedido_id, libro_id) VALUES (1, 2);
INSERT INTO pedido_libro (pedido_id, libro_id) VALUES (2, 2);
INSERT INTO pedido_libro (pedido_id, libro_id) VALUES (3, 3);
INSERT INTO pedido_libro (pedido_id, libro_id) VALUES (4, 4);
INSERT INTO pedido_libro (pedido_id, libro_id) VALUES (5, 5);