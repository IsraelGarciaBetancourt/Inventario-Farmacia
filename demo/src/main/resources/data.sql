INSERT INTO  estudiante
 (id, nombre, apellido, codigo, fecha_nacimiento)
 VALUES (1, 'Roberto Geronimo','Zarate Mendoza','C28933', '1982-01-01');

INSERT INTO  estudiante
 (id, nombre, apellido, codigo, fecha_nacimiento)
 VALUES (2, 'Mercedes','Mendoza','C11111','1980-06-06');
INSERT INTO  estudiante
 (id, nombre, apellido, codigo, fecha_nacimiento)
 VALUES (3, 'Edgar','Mendoza','C22222','1952-02-19');

INSERT INTO  tipo_producto
 ( nombre, fechaCreacion)
 VALUES ('Detergentes en polvo','2025-02-02');

INSERT INTO  tipo_producto
 ( nombre, fechaCreacion)
 VALUES ('Detergentes liquidos','2025-02-04');

INSERT INTO  tipo_producto
 ( nombre, fechaCreacion)
 VALUES ('Detergentes enzimaticos ','2025-02-06');

INSERT INTO  producto
( nombre, fecha_creacion, id_tipo_producto)
 values ('Detergente en polvo Ariel','2025-02-02',1);

INSERT INTO  producto
( nombre, fecha_creacion, id_tipo_producto)
values ('Detergente en polvo Ace','2025-02-04',1);

INSERT INTO  producto
( nombre, fecha_creacion, id_tipo_producto)
 values ('Detergente en polvo Omo','2025-02-06',1);

INSERT INTO  producto
( nombre, fecha_creacion, id_tipo_producto)
 values ('Detergente liquido Ariel','2025-02-02',2);

INSERT INTO  producto
( nombre, fecha_creacion, id_tipo_producto )
values ('Detergente liquido Ace','2025-02-04',2);


-- ============================================
-- 👤 USUARIOS
-- ============================================

INSERT INTO usuarios
(username, password_hash, nombre_completo, rol, activo, created_at, updated_at)
VALUES ('admin', 'admin123', 'Administrador Principal', 'ADMIN', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO usuarios
(username, password_hash, nombre_completo, rol, activo, created_at, updated_at)
VALUES ('empleado1', 'empleado123', 'Empleado de Ventas', 'USER', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);


-- ============================================
-- 🗂️ CATEGORIAS
-- ============================================

INSERT INTO categorias
(nombre, activo, created_at, updated_at)
VALUES ('Analgésicos', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO categorias
(nombre, activo, created_at, updated_at)
VALUES ('Antibióticos', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO categorias
(nombre, activo, created_at, updated_at)
VALUES ('Vitaminas', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO categorias
(nombre, activo, created_at, updated_at)
VALUES ('Desinfectantes', FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);


-- ============================================
-- 💊 PRODUCTO_CATALOGO
-- ============================================

INSERT INTO producto_catalogo
(codigo, nombre, id_categoria, activo, created_at, updated_at)
VALUES ('P001', 'Paracetamol 500mg Tabletas', 1, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO producto_catalogo
(codigo, nombre, id_categoria, activo, created_at, updated_at)
VALUES ('P002', 'Amoxicilina 500mg Cápsulas', 2, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO producto_catalogo
(codigo, nombre, id_categoria, activo, created_at, updated_at)
VALUES ('P003', 'Vitamina C 1000mg Tabletas', 3, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO producto_catalogo
(codigo, nombre, id_categoria, activo, created_at, updated_at)
VALUES ('P004', 'Alcohol en Gel 70%', 4, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);


-- ============================================
-- 📦 PRODUCTO_PARQUE
-- ============================================

INSERT INTO producto_parque
(id_producto_catalogo, existencias, activo, created_at, updated_at)
VALUES (1, 150, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO producto_parque
(id_producto_catalogo, existencias, activo, created_at, updated_at)
VALUES (2, 120, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO producto_parque
(id_producto_catalogo, existencias, activo, created_at, updated_at)
VALUES (3, 80, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO producto_parque
(id_producto_catalogo, existencias, activo, created_at, updated_at)
VALUES (4, 60, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);


-- ============================================
-- 📑 DOCUMENTOS
-- ============================================

INSERT INTO documentos
(tipo_movimiento, numero_documento, fecha, id_usuario, observacion, activo, created_at, updated_at)
VALUES ('ENTRADA', 'DOC-001', '2025-02-01', 1, 'Compra de medicamentos', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO documentos
(tipo_movimiento, numero_documento, fecha, id_usuario, observacion, activo, created_at, updated_at)
VALUES ('SALIDA', 'DOC-002', '2025-02-02', 2, 'Venta al público', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);


-- ============================================
-- 🧾 DOCUMENTO_DETALLES
-- ============================================

INSERT INTO documento_detalles
(id_documento, id_producto_catalogo, cantidad, precio_unitario, subtotal, activo, created_at, updated_at)
VALUES (1, 1, 100, 0.50, 50.00, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO documento_detalles
(id_documento, id_producto_catalogo, cantidad, precio_unitario, subtotal, activo, created_at, updated_at)
VALUES (1, 2, 50, 1.00, 50.00, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO documento_detalles
(id_documento, id_producto_catalogo, cantidad, precio_unitario, subtotal, activo, created_at, updated_at)
VALUES (2, 3, 10, 2.50, 25.00, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
