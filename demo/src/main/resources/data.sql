-----------------------------------
-- USUARIOS
-----------------------------------
INSERT INTO usuarios (username, password, nombre_completo, rol, activo)
VALUES
('admin', 'admin123', 'Administrador General', 'ADMIN', TRUE),
('empleado', '1234', 'Empleado Regular', 'USER', TRUE);

-----------------------------------
-- CATEGORÍAS DE PRODUCTOS
-----------------------------------
INSERT INTO categorias (nombre, activo)
VALUES
('Analgesicos', TRUE),
('Antibioticos', TRUE),
('Vitaminas', TRUE);

-----------------------------------
-- PRODUCTOS EN CATALOGO
-----------------------------------
INSERT INTO producto_catalogo (codigo, nombre, id_categoria, activo)
VALUES
('P-001', 'Paracetamol 500mg', 1, TRUE),
('P-002', 'Ibuprofeno 400mg', 1, TRUE),
('P-003', 'Amoxicilina 500mg', 2, TRUE),
('P-004', 'Vitamina C 1g', 3, TRUE);

-----------------------------------
-- STOCK INICIAL (PRODUCTO_PARQUE)
-----------------------------------
INSERT INTO producto_parque (id_producto_catalogo, existencias, activo)
VALUES
(1, 50, TRUE),
(2, 30, TRUE),
(3, 20, TRUE),
(4, 100, TRUE);

-----------------------------------
-- DOCUMENTO DE INGRESO (MOVIMIENTO)
-----------------------------------
INSERT INTO documentos (tipo_movimiento, numero_documento, fecha, id_usuario, observacion, activo)
VALUES
('INGRESO', 'ING-0001', CURRENT_DATE, 1, 'Carga inicial de inventario', TRUE);

-----------------------------------
-- DETALLES DEL DOCUMENTO DE INGRESO
-----------------------------------
INSERT INTO documento_detalles (id_documento, id_producto_catalogo, cantidad, activo)
VALUES
(1, 1, 50, TRUE),
(1, 2, 30, TRUE),
(1, 3, 20, TRUE),
(1, 4, 100, TRUE);
