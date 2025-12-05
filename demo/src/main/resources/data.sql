-----------------------------------
-- USUARIOS
-----------------------------------
INSERT INTO usuarios (username, password, nombre_completo, rol, activo)
VALUES
('admin', 'admin123', 'Administrador General', 'ADMIN', TRUE),
('empleado', '1234', 'Empleado Regular', 'USER', TRUE);

-----------------------------------
-- CATEGORÍAS
-----------------------------------
INSERT INTO categorias (nombre, activo)
VALUES
('Analgesicos', TRUE),
('Antibioticos', TRUE),
('Vitaminas', TRUE);

-----------------------------------
-- PRODUCTOS CATALOGO
-----------------------------------
INSERT INTO producto_catalogo (codigo, nombre, id_categoria, activo)
VALUES
('P-001', 'Paracetamol 500mg', 1, TRUE),
('P-002', 'Ibuprofeno 400mg', 1, TRUE),
('P-003', 'Amoxicilina 500mg', 2, TRUE),
('P-004', 'Vitamina C 1g', 3, TRUE);

-----------------------------------
-- STOCK INICIAL
-----------------------------------
INSERT INTO producto_parque (id_producto_catalogo, existencias, activo)
VALUES
(1, 50, TRUE),
(2, 30, TRUE),
(3, 20, TRUE),
(4, 100, TRUE);

-----------------------------------
-- DOCUMENTOS DE INGRESO (10 registros)
-----------------------------------
INSERT INTO documentos (id, tipo_movimiento, numero_documento, fecha, id_usuario, observacion, activo)
VALUES
(1, 'INGRESO', 'ING-0001', '2025-10-01', 1, 'Ingreso mensual', TRUE),
(2, 'INGRESO', 'ING-0002', '2025-10-05', 1, 'Reposición rápida', TRUE),
(3, 'INGRESO', 'ING-0003', '2025-10-12', 1, 'Ingreso proveedor A', TRUE),
(4, 'INGRESO', 'ING-0004', '2025-10-22', 1, 'Ingreso parcial', TRUE),
(5, 'INGRESO', 'ING-0005', '2025-11-01', 1, 'Ingreso mensual', TRUE),
(6, 'INGRESO', 'ING-0006', '2025-11-10', 1, 'Ingreso preventivo', TRUE),
(7, 'INGRESO', 'ING-0007', '2025-11-18', 1, 'Compra adicional', TRUE),
(8, 'INGRESO', 'ING-0008', '2025-12-01', 1, 'Ingreso mensual', TRUE),
(9, 'INGRESO', 'ING-0009', '2025-12-10', 1, 'Ingreso especial', TRUE),
(10,'INGRESO', 'ING-0010', '2025-12-20', 1, 'Reposición fin de año', TRUE);

-----------------------------------
-- DETALLES DE INGRESO
-----------------------------------
INSERT INTO documento_detalles (id_documento, id_producto_catalogo, cantidad, activo)
VALUES
-- ING-0001
(1,1,30,TRUE),(1,2,20,TRUE),(1,4,50,TRUE),

-- ING-0002
(2,1,20,TRUE),(2,3,10,TRUE),

-- ING-0003
(3,4,40,TRUE),(3,2,10,TRUE),

-- ING-0004
(4,3,20,TRUE),(4,1,15,TRUE),

-- ING-0005
(5,4,60,TRUE),

-- ING-0006
(6,1,25,TRUE),(6,2,20,TRUE),

-- ING-0007
(7,3,30,TRUE),

-- ING-0008
(8,1,40,TRUE),(8,4,70,TRUE),

-- ING-0009
(9,2,25,TRUE),(9,3,20,TRUE),

-- ING-0010
(10,4,80,TRUE),(10,1,20,TRUE);

-----------------------------------
-- DOCUMENTOS DE SALIDA (10 registros)
-----------------------------------
INSERT INTO documentos (id, tipo_movimiento, numero_documento, fecha, id_usuario, observacion, activo)
VALUES
(11, 'SALIDA', 'SAL-0001', '2025-10-03', 1, 'Venta diaria', TRUE),
(12, 'SALIDA', 'SAL-0002', '2025-10-07', 1, 'Venta diaria', TRUE),
(13, 'SALIDA', 'SAL-0003', '2025-10-15', 1, 'Consumo interno', TRUE),
(14, 'SALIDA', 'SAL-0004', '2025-10-25', 1, 'Venta especial', TRUE),
(15, 'SALIDA', 'SAL-0005', '2025-11-03', 1, 'Venta diaria', TRUE),
(16, 'SALIDA', 'SAL-0006', '2025-11-12', 1, 'Promoción', TRUE),
(17, 'SALIDA', 'SAL-0007', '2025-11-21', 1, 'Venta diaria', TRUE),
(18, 'SALIDA', 'SAL-0008', '2025-12-05', 1, 'Venta fuerte', TRUE),
(19, 'SALIDA', 'SAL-0009', '2025-12-12', 1, 'Venta diaria', TRUE),
(20, 'SALIDA', 'SAL-0010', '2025-12-22', 1, 'Venta pre-navidad', TRUE);

-----------------------------------
-- DETALLES DE SALIDA
-----------------------------------
INSERT INTO documento_detalles (id_documento, id_producto_catalogo, cantidad, activo)
VALUES
-- SAL-0001
(11,1,10,TRUE),(11,4,15,TRUE),

-- SAL-0002
(12,2,5,TRUE),(12,1,8,TRUE),

-- SAL-0003
(13,3,4,TRUE),

-- SAL-0004
(14,4,20,TRUE),

-- SAL-0005
(15,1,12,TRUE),(15,2,6,TRUE),

-- SAL-0006
(16,4,18,TRUE),

-- SAL-0007
(17,2,10,TRUE),(17,3,5,TRUE),

-- SAL-0008
(18,4,25,TRUE),

-- SAL-0009
(19,1,15,TRUE),

-- SAL-0010
(20,3,7,TRUE),(20,4,20,TRUE);


ALTER TABLE documentos ALTER COLUMN id RESTART WITH 1000;