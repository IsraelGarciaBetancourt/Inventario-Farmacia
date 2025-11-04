-- ======================================================================
-- DATOS EXTENDIDOS Y REALISTAS PARA PROBAR DASHBOARD (D7 / W4 / M4)
-- H2: usamos DATEADD para fechas relativas y que SIEMPRE caigan en rango
-- ======================================================================

-- ==============================
-- 🧾 Últimos 7 días (D7)  → agrupado por DÍA
-- Se cubren los 7 días: -6, -5, -4, -3, -2, -1, 0 (hoy)
-- ==============================

-- Día -6 (entrada fuerte + 2 detalles)
INSERT INTO documentos (tipo_movimiento, numero_documento, fecha, id_usuario, observacion, activo, created_at, updated_at)
VALUES ('ENTRADA', 'E-D7-01', DATEADD('DAY', -6, CURRENT_DATE), 1, 'Compra de stock semanal', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO documento_detalles (id_documento, id_producto_catalogo, cantidad, precio_unitario, subtotal, activo, created_at, updated_at)
VALUES ((SELECT MAX(id) FROM documentos), 1, 80, 0.50, 40.00, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO documento_detalles (id_documento, id_producto_catalogo, cantidad, precio_unitario, subtotal, activo, created_at, updated_at)
VALUES ((SELECT MAX(id) FROM documentos), 2, 40, 1.00, 40.00, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Día -5 (salida mixta)
INSERT INTO documentos (tipo_movimiento, numero_documento, fecha, id_usuario, observacion, activo, created_at, updated_at)
VALUES ('SALIDA', 'S-D7-01', DATEADD('DAY', -5, CURRENT_DATE), 2, 'Venta mostrador mañana', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO documento_detalles (id_documento, id_producto_catalogo, cantidad, precio_unitario, subtotal, activo, created_at, updated_at)
VALUES ((SELECT MAX(id) FROM documentos), 3, 12, 2.00, 24.00, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO documentos (tipo_movimiento, numero_documento, fecha, id_usuario, observacion, activo, created_at, updated_at)
VALUES ('SALIDA', 'S-D7-02', DATEADD('DAY', -5, CURRENT_DATE), 2, 'Venta mostrador tarde', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO documento_detalles (id_documento, id_producto_catalogo, cantidad, precio_unitario, subtotal, activo, created_at, updated_at)
VALUES ((SELECT MAX(id) FROM documentos), 1, 8, 0.80, 6.40, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Día -4 (salida + entrada pequeña)
INSERT INTO documentos (tipo_movimiento, numero_documento, fecha, id_usuario, observacion, activo, created_at, updated_at)
VALUES ('SALIDA', 'S-D7-03', DATEADD('DAY', -4, CURRENT_DATE), 2, 'Venta mostrador', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO documento_detalles (id_documento, id_producto_catalogo, cantidad, precio_unitario, subtotal, activo, created_at, updated_at)
VALUES ((SELECT MAX(id) FROM documentos), 2, 10, 1.10, 11.00, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO documentos (tipo_movimiento, numero_documento, fecha, id_usuario, observacion, activo, created_at, updated_at)
VALUES ('ENTRADA', 'E-D7-02', DATEADD('DAY', -4, CURRENT_DATE), 1, 'Reposición puntual', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO documento_detalles (id_documento, id_producto_catalogo, cantidad, precio_unitario, subtotal, activo, created_at, updated_at)
VALUES ((SELECT MAX(id) FROM documentos), 4, 20, 0.70, 14.00, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Día -3 (entrada media)
INSERT INTO documentos (tipo_movimiento, numero_documento, fecha, id_usuario, observacion, activo, created_at, updated_at)
VALUES ('ENTRADA', 'E-D7-03', DATEADD('DAY', -3, CURRENT_DATE), 1, 'Reposición diaria', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO documento_detalles (id_documento, id_producto_catalogo, cantidad, precio_unitario, subtotal, activo, created_at, updated_at)
VALUES ((SELECT MAX(id) FROM documentos), 4, 50, 0.70, 35.00, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Día -2 (salida intensa + producto extra)
INSERT INTO documentos (tipo_movimiento, numero_documento, fecha, id_usuario, observacion, activo, created_at, updated_at)
VALUES ('SALIDA', 'S-D7-04', DATEADD('DAY', -2, CURRENT_DATE), 2, 'Venta express', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO documento_detalles (id_documento, id_producto_catalogo, cantidad, precio_unitario, subtotal, activo, created_at, updated_at)
VALUES ((SELECT MAX(id) FROM documentos), 1, 25, 0.80, 20.00, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO documento_detalles (id_documento, id_producto_catalogo, cantidad, precio_unitario, subtotal, activo, created_at, updated_at)
VALUES ((SELECT MAX(id) FROM documentos), 3, 6, 2.40, 14.40, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Día -1 (dos ventas)
INSERT INTO documentos (tipo_movimiento, numero_documento, fecha, id_usuario, observacion, activo, created_at, updated_at)
VALUES ('SALIDA', 'S-D7-05', DATEADD('DAY', -1, CURRENT_DATE), 2, 'Venta fin de semana', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO documento_detalles (id_documento, id_producto_catalogo, cantidad, precio_unitario, subtotal, activo, created_at, updated_at)
VALUES ((SELECT MAX(id) FROM documentos), 2, 30, 1.00, 30.00, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO documentos (tipo_movimiento, numero_documento, fecha, id_usuario, observacion, activo, created_at, updated_at)
VALUES ('SALIDA', 'S-D7-06', DATEADD('DAY', -1, CURRENT_DATE), 2, 'Venta adicional', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO documento_detalles (id_documento, id_producto_catalogo, cantidad, precio_unitario, subtotal, activo, created_at, updated_at)
VALUES ((SELECT MAX(id) FROM documentos), 4, 9, 0.95, 8.55, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Hoy (entrada y salida para cerrar el ciclo)
INSERT INTO documentos (tipo_movimiento, numero_documento, fecha, id_usuario, observacion, activo, created_at, updated_at)
VALUES ('ENTRADA', 'E-D7-04', CURRENT_DATE, 1, 'Compra rápida del día', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO documento_detalles (id_documento, id_producto_catalogo, cantidad, precio_unitario, subtotal, activo, created_at, updated_at)
VALUES ((SELECT MAX(id) FROM documentos), 3, 60, 2.50, 150.00, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO documentos (tipo_movimiento, numero_documento, fecha, id_usuario, observacion, activo, created_at, updated_at)
VALUES ('SALIDA', 'S-D7-07', CURRENT_DATE, 2, 'Venta del día', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO documento_detalles (id_documento, id_producto_catalogo, cantidad, precio_unitario, subtotal, activo, created_at, updated_at)
VALUES ((SELECT MAX(id) FROM documentos), 1, 18, 0.85, 15.30, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);


-- ==============================
-- 📅 Últimas 4 semanas (W4)  → agrupado por SEMANA ISO
-- Refuerza semanas previas (fuera del rango diario más reciente)
-- Fechas aproximadas: -10, -17, -24, -27
-- ==============================

-- Semana -1 aprox. (día -10)
INSERT INTO documentos (tipo_movimiento, numero_documento, fecha, id_usuario, observacion, activo, created_at, updated_at)
VALUES ('SALIDA', 'S-W4-01', DATEADD('DAY', -10, CURRENT_DATE), 2, 'Venta campaña 1', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO documento_detalles (id_documento, id_producto_catalogo, cantidad, precio_unitario, subtotal, activo, created_at, updated_at)
VALUES ((SELECT MAX(id) FROM documentos), 4, 15, 0.90, 13.50, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Semana -2 aprox. (día -17)
INSERT INTO documentos (tipo_movimiento, numero_documento, fecha, id_usuario, observacion, activo, created_at, updated_at)
VALUES ('ENTRADA', 'E-W4-01', DATEADD('DAY', -17, CURRENT_DATE), 1, 'Reposición semanal', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO documento_detalles (id_documento, id_producto_catalogo, cantidad, precio_unitario, subtotal, activo, created_at, updated_at)
VALUES ((SELECT MAX(id) FROM documentos), 1, 100, 0.45, 45.00, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Semana -3 aprox. (día -24)
INSERT INTO documentos (tipo_movimiento, numero_documento, fecha, id_usuario, observacion, activo, created_at, updated_at)
VALUES ('SALIDA', 'S-W4-02', DATEADD('DAY', -24, CURRENT_DATE), 2, 'Venta campaña 2', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO documento_detalles (id_documento, id_producto_catalogo, cantidad, precio_unitario, subtotal, activo, created_at, updated_at)
VALUES ((SELECT MAX(id) FROM documentos), 2, 20, 1.00, 20.00, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Semana -4 aprox. (día -27)
INSERT INTO documentos (tipo_movimiento, numero_documento, fecha, id_usuario, observacion, activo, created_at, updated_at)
VALUES ('ENTRADA', 'E-W4-02', DATEADD('DAY', -27, CURRENT_DATE), 1, 'Compra grande mensual', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO documento_detalles (id_documento, id_producto_catalogo, cantidad, precio_unitario, subtotal, activo, created_at, updated_at)
VALUES ((SELECT MAX(id) FROM documentos), 3, 200, 2.30, 460.00, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);


-- ==============================
-- 🗓️ Últimos 4 meses (M4) → agrupado por MES
-- Inserta 1–2 movimientos en cada mes para ver barras claras
-- ==============================

-- Hace 1 mes
INSERT INTO documentos (tipo_movimiento, numero_documento, fecha, id_usuario, observacion, activo, created_at, updated_at)
VALUES ('ENTRADA', 'E-M4-01', DATEADD('MONTH', -1, CURRENT_DATE), 1, 'Compra mensual', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO documento_detalles (id_documento, id_producto_catalogo, cantidad, precio_unitario, subtotal, activo, created_at, updated_at)
VALUES ((SELECT MAX(id) FROM documentos), 1, 90, 0.55, 49.50, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Hace 2 meses
INSERT INTO documentos (tipo_movimiento, numero_documento, fecha, id_usuario, observacion, activo, created_at, updated_at)
VALUES ('SALIDA', 'S-M4-01', DATEADD('MONTH', -2, CURRENT_DATE), 2, 'Venta mensual', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO documento_detalles (id_documento, id_producto_catalogo, cantidad, precio_unitario, subtotal, activo, created_at, updated_at)
VALUES ((SELECT MAX(id) FROM documentos), 2, 50, 1.00, 50.00, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Hace 3 meses
INSERT INTO documentos (tipo_movimiento, numero_documento, fecha, id_usuario, observacion, activo, created_at, updated_at)
VALUES ('ENTRADA', 'E-M4-02', DATEADD('MONTH', -3, CURRENT_DATE), 1, 'Compra anterior', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO documento_detalles (id_documento, id_producto_catalogo, cantidad, precio_unitario, subtotal, activo, created_at, updated_at)
VALUES ((SELECT MAX(id) FROM documentos), 3, 120, 2.10, 252.00, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Hace 4 meses
INSERT INTO documentos (tipo_movimiento, numero_documento, fecha, id_usuario, observacion, activo, created_at, updated_at)
VALUES ('SALIDA', 'S-M4-02', DATEADD('MONTH', -4, CURRENT_DATE), 2, 'Venta trimestral', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO documento_detalles (id_documento, id_producto_catalogo, cantidad, precio_unitario, subtotal, activo, created_at, updated_at)
VALUES ((SELECT MAX(id) FROM documentos), 4, 40, 0.90, 36.00, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
