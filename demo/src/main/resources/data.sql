-- Usuarios
INSERT INTO usuarios (username, password_hash, nombre_completo, rol, activo) VALUES
('admin', '1234', 'Administrador General', 'ADMIN', TRUE),
('almacenero', '1234', 'Almacenero1', 'ALMACENERO', TRUE);

-- Categorías
INSERT INTO categorias (nombre, activo) VALUES
('Analgésicos', TRUE),
('Antibióticos', TRUE),
('Vitaminas', TRUE);

-- Catálogo de productos
INSERT INTO producto_catalogo (codigo, nombre, id_categoria, activo) VALUES
('MED-001', 'Paracetamol 500 mg', 1, TRUE),
('MED-002', 'Ibuprofeno 400 mg', 1, TRUE),
('MED-003', 'Amoxicilina 500 mg', 2, TRUE),
('MED-004', 'Vitamina C 1g', 3, TRUE);

-- Inventario inicial (producto_parque)
INSERT INTO producto_parque (id_producto_catalogo, existencias, valor_stock, activo) VALUES
(1, 100, 500.00, TRUE),
(2, 200, 900.00, TRUE),
(3, 80, 400.00, TRUE),
(4, 50, 250.00, TRUE);

-- Documento de prueba (INGRESO)
INSERT INTO documentos (tipo_movimiento, numero_documento, fecha, id_usuario, observacion, total_productos, total_unidades, total_valor, activo)
VALUES ('INGRESO', 'ING-0001', CURDATE(), 2, 'Carga inicial de inventario', 2, 300, 1400.00, TRUE);

-- Detalles del documento
INSERT INTO documento_detalles (id_documento, id_producto_catalogo, cantidad, precio_unitario, subtotal, activo)
VALUES
(1, 1, 100, 5.00, 500.00, TRUE),
(1, 2, 200, 4.50, 900.00, TRUE);
