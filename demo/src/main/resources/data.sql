-- ==========================================
-- DATA.SQL - COMPATIBLE CON H2 (SIN DATE_SUB)
-- ==========================================

-- USUARIOS
INSERT INTO usuarios (username, password_hash, nombre_completo, rol, activo) VALUES
('admin', '1234', 'Administrador General', 'ADMIN', TRUE),
('almacenero', '1234', 'Almacenero1', 'ALMACENERO', TRUE);

-- ==========================================
-- CATEGORÍAS
-- ==========================================
INSERT INTO categorias (nombre, activo) VALUES
('Analgésicos', TRUE),
('Antibióticos', TRUE),
('Vitaminas', TRUE),
('Antiinflamatorios', TRUE),
('Antigripales', TRUE);

-- ==========================================
-- CATÁLOGO DE PRODUCTOS
-- ==========================================
INSERT INTO producto_catalogo (codigo, nombre, id_categoria, activo) VALUES
('MED-001', 'Paracetamol 500 mg', 1, TRUE),
('MED-002', 'Ibuprofeno 400 mg', 4, TRUE),
('MED-003', 'Amoxicilina 500 mg', 2, TRUE),
('MED-004', 'Vitamina C 1g', 3, TRUE),
('MED-005', 'Diclofenaco 50 mg', 4, TRUE),
('MED-006', 'Cefalexina 500 mg', 2, TRUE),
('MED-007', 'Multivitamínico Adulto', 3, TRUE),
('MED-008', 'Naproxeno 500 mg', 4, TRUE),
('MED-009', 'Loratadina 10 mg', 5, TRUE),
('MED-010', 'Antigripal Forte', 5, TRUE);

-- ==========================================
-- INVENTARIO INICIAL
-- ==========================================
INSERT INTO producto_parque (id_producto_catalogo, existencias, valor_stock, activo) VALUES
(1, 200, 1000.00, TRUE),
(2, 180, 900.00, TRUE),
(3, 150, 750.00, TRUE),
(4, 120, 600.00, TRUE),
(5, 130, 650.00, TRUE),
(6, 110, 550.00, TRUE),
(7, 90, 450.00, TRUE),
(8, 160, 800.00, TRUE),
(9, 140, 700.00, TRUE),
(10, 100, 500.00, TRUE);

-- ==========================================
-- DOCUMENTOS DE INGRESO (fechas fijas)
-- ==========================================
INSERT INTO documentos (tipo_movimiento, numero_documento, fecha, id_usuario, observacion, total_productos, total_unidades, total_valor, activo) VALUES
('INGRESO', 'ING-0001', '2025-11-01', 2, 'Ingreso de stock por proveedor A', 3, 450, 2100.00, TRUE),
('INGRESO', 'ING-0002', '2025-10-20', 2, 'Ingreso de antibióticos por compra mayorista', 2, 300, 1600.00, TRUE),
('INGRESO', 'ING-0003', '2025-10-10', 2, 'Ingreso de vitaminas', 2, 200, 950.00, TRUE),
('INGRESO', 'ING-0004', '2025-09-25', 2, 'Ingreso general de analgésicos', 3, 500, 2500.00, TRUE),
('INGRESO', 'ING-0005', '2025-09-10', 2, 'Recepción de antiinflamatorios', 2, 220, 1150.00, TRUE),
('INGRESO', 'ING-0006', '2025-08-25', 2, 'Ingreso de medicamentos variados', 3, 330, 1650.00, TRUE),
('INGRESO', 'ING-0007', '2025-08-10', 2, 'Reposición de antibióticos', 2, 260, 1300.00, TRUE),
('INGRESO', 'ING-0008', '2025-07-25', 2, 'Compra general de vitaminas', 2, 240, 1200.00, TRUE),
('INGRESO', 'ING-0009', '2025-07-10', 2, 'Ingreso de antigripales', 2, 180, 900.00, TRUE),
('INGRESO', 'ING-0010', '2025-07-01', 2, 'Ingreso de stock mensual', 3, 310, 1550.00, TRUE);

-- ==========================================
-- DETALLES DE INGRESO
-- ==========================================
INSERT INTO documento_detalles (id_documento, id_producto_catalogo, cantidad, precio_unitario, subtotal, activo) VALUES
(1, 1, 150, 5.00, 750.00, TRUE),
(1, 2, 150, 4.50, 675.00, TRUE),
(1, 3, 150, 4.50, 675.00, TRUE),

(2, 3, 150, 5.00, 750.00, TRUE),
(2, 6, 150, 5.67, 850.00, TRUE),

(3, 4, 100, 4.50, 450.00, TRUE),
(3, 7, 100, 5.00, 500.00, TRUE),

(4, 1, 200, 5.00, 1000.00, TRUE),
(4, 2, 150, 5.00, 750.00, TRUE),
(4, 5, 150, 5.00, 750.00, TRUE),

(5, 5, 120, 5.00, 600.00, TRUE),
(5, 8, 100, 5.50, 550.00, TRUE),

(6, 9, 130, 5.00, 650.00, TRUE),
(6, 10, 100, 5.00, 500.00, TRUE),
(6, 1, 100, 5.00, 500.00, TRUE),

(7, 3, 160, 5.00, 800.00, TRUE),
(7, 6, 100, 5.00, 500.00, TRUE),

(8, 4, 120, 5.00, 600.00, TRUE),
(8, 7, 120, 5.00, 600.00, TRUE),

(9, 9, 100, 5.00, 500.00, TRUE),
(9, 10, 80, 5.00, 400.00, TRUE),

(10, 2, 150, 5.00, 750.00, TRUE),
(10, 5, 100, 5.00, 500.00, TRUE),
(10, 8, 60, 5.00, 300.00, TRUE);

-- ==========================================
-- DOCUMENTOS DE SALIDA
-- ==========================================
INSERT INTO documentos (tipo_movimiento, numero_documento, fecha, id_usuario, observacion, total_productos, total_unidades, total_valor, activo) VALUES
('SALIDA', 'SAL-0001', '2025-11-03', 2, 'Despacho a farmacia A', 2, 200, 950.00, TRUE),
('SALIDA', 'SAL-0002', '2025-10-18', 2, 'Consumo interno', 3, 150, 700.00, TRUE),
('SALIDA', 'SAL-0003', '2025-10-05', 2, 'Distribución regional', 2, 220, 1100.00, TRUE),
('SALIDA', 'SAL-0004', '2025-09-22', 2, 'Despacho a centro médico B', 3, 300, 1450.00, TRUE),
('SALIDA', 'SAL-0005', '2025-09-08', 2, 'Salida a hospital C', 2, 250, 1250.00, TRUE),
('SALIDA', 'SAL-0006', '2025-08-20', 2, 'Vencimiento de lote', 2, 180, 850.00, TRUE),
('SALIDA', 'SAL-0007', '2025-08-05', 2, 'Redistribución interna', 3, 260, 1275.00, TRUE),
('SALIDA', 'SAL-0008', '2025-07-22', 2, 'Despacho a sucursal B', 2, 240, 1150.00, TRUE),
('SALIDA', 'SAL-0009', '2025-07-08', 2, 'Salida por promoción', 2, 180, 875.00, TRUE),
('SALIDA', 'SAL-0010', '2025-07-02', 2, 'Entrega a clínica privada', 2, 270, 1300.00, TRUE);

-- ==========================================
-- DETALLES DE SALIDA
-- ==========================================
INSERT INTO documento_detalles (id_documento, id_producto_catalogo, cantidad, precio_unitario, subtotal, activo) VALUES
(11, 1, 120, 5.00, 600.00, TRUE),
(11, 2, 80, 4.40, 350.00, TRUE),

(12, 4, 50, 5.00, 250.00, TRUE),
(12, 5, 50, 4.50, 225.00, TRUE),
(12, 7, 50, 4.50, 225.00, TRUE),

(13, 3, 120, 5.00, 600.00, TRUE),
(13, 6, 100, 5.00, 500.00, TRUE),

(14, 1, 100, 5.00, 500.00, TRUE),
(14, 2, 100, 4.75, 475.00, TRUE),
(14, 5, 100, 4.75, 475.00, TRUE),

(15, 8, 120, 5.00, 600.00, TRUE),
(15, 9, 130, 5.00, 650.00, TRUE),

(16, 10, 80, 5.00, 400.00, TRUE),
(16, 3, 100, 4.50, 450.00, TRUE),

(17, 2, 100, 4.75, 475.00, TRUE),
(17, 5, 100, 4.00, 400.00, TRUE),
(17, 6, 60, 4.67, 280.00, TRUE),

(18, 1, 120, 5.00, 600.00, TRUE),
(18, 10, 120, 4.60, 550.00, TRUE),

(19, 9, 100, 5.00, 500.00, TRUE),
(19, 10, 80, 4.69, 375.00, TRUE),

(20, 3, 120, 5.00, 600.00, TRUE),
(20, 7, 150, 4.67, 700.00, TRUE);

-- ==========================================
-- FIN DE DATA.SQL
-- ==========================================
