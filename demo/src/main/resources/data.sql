-- ============================================
-- 👤 USUARIOS
-- ============================================
INSERT INTO usuarios
(username, password_hash, nombre_completo, rol, activo, created_at, updated_at)
VALUES
('admin', 'admin123', 'Administrador Principal', 'ADMIN', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('almacenero', 'almacenero123', 'Almacenero', 'USER', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- ============================================
-- 🗂️ CATEGORIAS
-- ============================================
INSERT INTO categorias (nombre, activo, created_at, updated_at)
VALUES
('Analgésicos', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Antibióticos', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Vitaminas', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Desinfectantes', FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- ============================================
-- 💊 PRODUCTO_CATALOGO
-- ============================================
INSERT INTO producto_catalogo
(codigo, nombre, id_categoria, precio_unitario, activo, created_at, updated_at)
VALUES
('P001', 'Paracetamol 500mg Tabletas', 1, 0.50, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('P002', 'Amoxicilina 500mg Cápsulas', 2, 1.00, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('P003', 'Vitamina C 1000mg Tabletas', 3, 2.50, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('P004', 'Alcohol en Gel 70%', 4, 3.00, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- ============================================
-- 📦 PRODUCTO_PARQUE
-- ============================================
INSERT INTO producto_parque
(id_producto_catalogo, existencias, activo, created_at, updated_at)
VALUES
(1, 150, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, 120, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(3, 80, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(4, 60, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- ============================================
-- 📑 DOCUMENTOS
-- ============================================
INSERT INTO documentos
(tipo_movimiento, numero_documento, fecha, id_usuario, observacion, activo, created_at, updated_at)
VALUES
('ENTRADA', 'DOC-001', '2025-02-01', 1, 'Compra de medicamentos', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('SALIDA', 'DOC-002', '2025-02-02', 2, 'Venta al público', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- ============================================
-- 🧾 DOCUMENTO_DETALLES
-- (El subtotal se calcula con cantidad * precio_unitario del catálogo)
-- ============================================
INSERT INTO documento_detalles
(id_documento, id_producto_catalogo, cantidad, subtotal, activo, created_at, updated_at)
VALUES
(1, 1, 100, 50.00, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(1, 2, 50, 50.00, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, 3, 10, 25.00, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
